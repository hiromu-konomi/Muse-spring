package com.example.musespringapi.controller;

import com.example.musespringapi.domain.Group;
import com.example.musespringapi.request.GroupRequest;
import com.example.musespringapi.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupRestController {
    
    @Autowired
    private final GroupService groupService;

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public void createGroup(@RequestBody GroupRequest request) {
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        groupService.save(group);
    }
}
