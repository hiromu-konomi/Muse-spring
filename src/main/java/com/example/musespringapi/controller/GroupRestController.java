package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.musespringapi.domain.FollowUserGrpSts;
import com.example.musespringapi.domain.Group;
import com.example.musespringapi.domain.GroupMember;
import com.example.musespringapi.domain.JoinGroup;
import com.example.musespringapi.domain.OwnerGroup;
import com.example.musespringapi.response.FollowUsersGrpStsResponse;
import com.example.musespringapi.response.GroupResponse;
import com.example.musespringapi.response.JoinGroupResponse;
import com.example.musespringapi.response.OwnerGroupResponse;
import com.example.musespringapi.service.GroupMemberService;
import com.example.musespringapi.service.GroupService;
import com.example.musespringapi.service.RelationService;
import com.example.musespringapi.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupRestController {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;
    private final RelationService relationService;
    private final UserService userService;

    // グループを新規作成した際に GET されるメソッド
    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public GroupResponse createGroup(String userNum, String groupName, String[] inviteUsers) {

        // groups テーブルに INSERT
        Group insertGroup = new Group();
        insertGroup.setGroupName(groupName);
        insertGroup.setOwnerUserId(userNum);
        groupService.save(insertGroup);

        // group_id を取得して group_member テーブルに INSERT(作成者のみ)
        Long groupId = groupService.newGroupId(userNum);
        GroupMember ownerMember = new GroupMember();
        ownerMember.setGroupId(groupId);
        ownerMember.setUserNum(userNum);
        ownerMember.setJoinStatus(1);
        groupMemberService.save(ownerMember);

        // ユーザーを招待していた場合、そのユーザー達も group_member に INSERT
        try {
            for (String inviteUser : inviteUsers) {
                GroupMember joinMember = new GroupMember();
                joinMember.setGroupId(groupId);
                joinMember.setUserNum(inviteUser);
                joinMember.setJoinStatus(2);
                groupMemberService.save(joinMember);
            }
        } catch (java.lang.NullPointerException ex) {
        }

        // グループ詳細画面に表示するグループデータを取得して返す
        GroupResponse response = new GroupResponse();
        response.setGroupId(groupId);
        response.setGroupName(groupName);
        response.setOwnerId(userNum);
        response.setJoinStatus(1);
        return response;
    }

    // 「管理しているグループ」のコンポーネントが表示される際に GET されるメソッド
    @RequestMapping(value = "/showOwnerGroupList", method = RequestMethod.GET)
    public ResponseEntity<OwnerGroupResponse> showOwnerGroupList(String userNum) {

        List<OwnerGroup> ownerGroups = new ArrayList<>();

        List<Group> groupList = groupService.ownerGroupList(userNum);

        for (int j = 0; j < groupList.size(); j++) {
            OwnerGroup group = new OwnerGroup();
            Long groupId = groupList.get(j).getGroupId();
            Integer countMember = groupMemberService.countMember(groupId);
            String groupName = groupList.get(j).getGroupName();
            group.setGroupId(groupId);
            group.setCountMember(countMember);
            group.setGroupName(groupName);
            ownerGroups.add(group);
        }

        OwnerGroupResponse response = OwnerGroupResponse.builder().ownerGroups(ownerGroups).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 「参加しているグループ」のコンポーネントが表示される際に GET されるメソッド
    @RequestMapping(value = "/showJoinGroupList", method = RequestMethod.GET)
    public ResponseEntity<JoinGroupResponse> showJoinGroupList(String userNum) {
        
        List<JoinGroup> joinGroups = new ArrayList<>();

        List<Long> groupIds = groupMemberService.findByUserNum(userNum);

        for (Long groupId : groupIds) {
            Group group = groupService.findByGroupId(groupId);
            if (Objects.isNull(group)) {
                continue;
            }
            String ownerNum = group.getOwnerUserId();
            //自分が管理しているグループを表示させない為の条件分岐
            if (ownerNum.equals(userNum)) {
                continue;
            }
            String ownerName = userService.userNameFindByUserNum(group.getOwnerUserId());
            JoinGroup joinGroup = new JoinGroup();
            joinGroup.setGroupName(group.getGroupName());
            joinGroup.setOwnerName(ownerName);
            joinGroup.setGroupId(groupId);
            joinGroups.add(joinGroup);
        }

        JoinGroupResponse response = JoinGroupResponse.builder().joinGroups(joinGroups).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 「管理しているグループ」が検索された際に GET されるメソッド
    @RequestMapping(value = "/showOwnGrpsBySearch", method = RequestMethod.GET)
    public ResponseEntity<OwnerGroupResponse> showOwnGrpsBySearch(String userNum, String searchWord) {

        List<OwnerGroup> ownerGroups = new ArrayList<>();

        List<Group> groupList = groupService.ownerGroupListByWord(userNum, searchWord);

        for (int j = 0; j < groupList.size(); j++) {
            OwnerGroup group = new OwnerGroup();
            Long groupId = groupList.get(j).getGroupId();
            String groupName = groupList.get(j).getGroupName();
            group.setGroupId(groupId);
            group.setGroupName(groupName);
            ownerGroups.add(group);
        }

        OwnerGroupResponse response = OwnerGroupResponse.builder().ownerGroups(ownerGroups).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 「参加しているグループ」が検索された際に GET されるメソッド
    @RequestMapping(value = "/showJoinGrpsBySearch", method = RequestMethod.GET)
    public ResponseEntity<JoinGroupResponse> showJoinGrpsBySearch(String userNum, String searchWord) {

        List<JoinGroup> joinGroups = new ArrayList<>();

        List<Long> groupIds = groupMemberService.findByUserNum(userNum);

        for (Long groupId : groupIds) {
            Group group = groupService.findByIdAndSearchWord(groupId, searchWord);
            if (Objects.isNull(group)) {
                continue;
            }
            String ownerNum = group.getOwnerUserId();
            //自分が管理しているグループを表示させない為の条件分岐
            if (ownerNum.equals(userNum)) {
                continue;
            }
            JoinGroup joinGroup = new JoinGroup();
            joinGroup.setGroupName(group.getGroupName());
            joinGroup.setGroupId(groupId);
            joinGroups.add(joinGroup);
        }

        JoinGroupResponse response = JoinGroupResponse.builder().joinGroups(joinGroups).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // グループに招待するユーザーを選択するダイアログが表示される際に GET されるメソッド
    @RequestMapping(value = "/flwUserListAndJoinSts", method = RequestMethod.GET)
    public ResponseEntity<FollowUsersGrpStsResponse> flwUserListAndJoinSts (String userNum, Long groupId) {

        List<FollowUserGrpSts> followUsers = new ArrayList<>();

        List<String> followerUserList = relationService.getFollowerUserNum(userNum);

        for (String flwUserNum : followerUserList) {
            FollowUserGrpSts user = new FollowUserGrpSts();
            String userName = userService.userNameFindByUserNum(flwUserNum);
            Integer joinStatus = groupMemberService.getJoinStatus(flwUserNum, groupId);
            user.setUserNum(flwUserNum);
            user.setUserName(userName);
            user.setJoinStatus(joinStatus);
            followUsers.add(user);
        }

        FollowUsersGrpStsResponse response = FollowUsersGrpStsResponse.builder()
        .followUsers(followUsers)
        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 「参加」ボタンが押下された際に GET されるメソッド
    @RequestMapping(value = "/setJoinStatus", method = RequestMethod.GET)
    public Integer setJoinStatus(String userNum, Long groupId) {

        GroupMember inviteMember = groupMemberService.findByUserNumAndGroupId(userNum, groupId);
        // 既に招待されていたらUPDATE、されていなければINSERT        
        if (Objects.isNull(inviteMember)) {
            // 招待されてないユーザーの場合( 新たにINSERT )
            GroupMember insertMember = new GroupMember();
            insertMember.setGroupId(groupId);
            insertMember.setUserNum(userNum);
            insertMember.setJoinStatus(1);
            groupMemberService.save(insertMember);
        } else {
            // 招待されているユーザーの場合( 既存のレコードをUPDATE )
            GroupMember updateMember = new GroupMember();
            updateMember.setGroupMemberId(inviteMember.getGroupMemberId());
            updateMember.setGroupId(groupId);
            updateMember.setUserNum(userNum);
            updateMember.setJoinStatus(1);
        }
        // 参加済の画面表示にするため、JoinStatus（１）をreturn
        return 1;
    }

    // グループの説明文が編集された際に GET されるメソッド
    @RequestMapping(value = "/setGrpDes")
    public String setGrpDes(String groupDescription, Long groupId) {

        // groupsテーブルからgroup_idをもとにレコード検索
        Group group = groupService.findByGroupId(groupId);

        // groupsテーブルの既存レコードのgroup_descriptionを更新
        Group updateGroup = new Group();
        updateGroup.setGroupId(groupId);
        updateGroup.setGroupName(group.getGroupName());
        updateGroup.setGroupDescription(groupDescription);
        updateGroup.setOwnerUserId(group.getOwnerUserId());
        groupService.save(updateGroup);

        return groupDescription;
    }
}