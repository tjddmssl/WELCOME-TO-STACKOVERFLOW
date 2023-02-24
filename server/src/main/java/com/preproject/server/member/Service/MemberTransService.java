package com.preproject.server.member.Service;

import com.preproject.server.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberTransService {
    private final MemberMapper memberMapper;
    private final MemberService service;


}
