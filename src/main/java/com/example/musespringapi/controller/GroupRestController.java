package com.example.musespringapi.controller;

import com.example.musespringapi.domain.Group;
import com.example.musespringapi.domain.GroupMember;
import com.example.musespringapi.request.GroupRequest;
import com.example.musespringapi.service.GroupMemberService;
import com.example.musespringapi.service.GroupService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupRestController {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public void createGroup(@RequestBody GroupRequest request) {
        
        // groups テーブルに INSERT
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setOwnerUserId(request.getFirebaseId());
        groupService.save(group);

        // group_id を取得して group_member テーブルに INSERT(管理者のみ)
        Long groupId = groupService.newGroupId();
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(groupId);
        groupMember.setUserNum(request.getFirebaseId());
        groupMemberService.save(groupMember);
    }
}
