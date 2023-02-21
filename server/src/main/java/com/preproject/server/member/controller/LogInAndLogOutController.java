package com.preproject.server.member.controller;

import com.preproject.server.member.dta.LoginDto;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class LogInAndLogOutController {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginDto loginDto, HttpServletResponse response) {


    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void login() {

    }
}
