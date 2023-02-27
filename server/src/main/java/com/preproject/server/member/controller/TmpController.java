package com.preproject.server.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TmpController {
    @RequestMapping("/receive-token")
    public String tmp(HttpServletRequest request) {
        String authentication = request.getHeader("Authentication");

        System.out.println("####authentication = " + authentication);
        return "야 성공했냐?";
    }
}
