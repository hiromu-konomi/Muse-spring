package com.example.musespringapi.controller;

import com.example.musespringapi.domain.Group;
import com.example.musespringapi.domain.GroupMember;
import com.example.musespringapi.response.GroupResponse;
import com.example.musespringapi.service.GroupMemberService;
import com.example.musespringapi.service.GroupService;
import com.example.musespringapi.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupRestController {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;
    private final UserService userService;

    // グループを新規作成した際に GET されるメソッド
    @RequestMapping(value = "/showGroup", method = RequestMethod.GET)
    public GroupResponse showGroup (String groupName, String userNum) {

        // groups テーブルに INSERT
        Group insertGroup = new Group();
        insertGroup.setGroupName(groupName);
        insertGroup.setOwnerUserId(userNum);
        groupService.save(insertGroup);

        // group_id を取得して group_member テーブルに INSERT(管理者のみ)
        Long groupId = groupService.newGroupId(userNum);
        GroupMember insertMember = new GroupMember();
        insertMember.setGroupId(groupId);
        insertMember.setUserNum(userNum);
        groupMemberService.save(insertMember);

        // グループ詳細画面に表示するグループデータを取得して返す
        GroupResponse response = new GroupResponse();
        response.setGroupId(groupId);
        response.setGroupName(groupName);
        String userName = userService.userNameFindByUserNum(userNum);
        response.setOwnerName(userName);
        return response;
    }
}
