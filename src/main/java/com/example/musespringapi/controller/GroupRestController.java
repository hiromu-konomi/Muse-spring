package com.example.musespringapi.controller;

import com.example.musespringapi.request.GroupRequest;
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

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public void createGroup(@RequestBody GroupRequest request) {
        groupService.save(request.getGroupName());
    }
}
