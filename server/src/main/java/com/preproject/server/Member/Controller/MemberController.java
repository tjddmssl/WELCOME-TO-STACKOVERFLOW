package com.preproject.server.Member.Controller;

import com.preproject.server.Member.Service.MemberService;
import com.preproject.server.Member.dto.MemberPostDto;
import com.preproject.server.Member.entity.Member;
import com.preproject.server.Member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    @PostMapping("/join")
    @CrossOrigin("*")
    public ResponseEntity createMember(@RequestBody MemberPostDto post) {

        Member member = mapper.postDtoToMember(post);
        Member findMember = memberService.createMember(member);
        return new ResponseEntity(findMember, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
//    @CrossOrigin("/*")
    public ResponseEntity getMember(@PathVariable Long id) {
        Member member = memberService.getMember(id);
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @CrossOrigin("/*")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
//    @CrossOrigin("/*")
    public ResponseEntity getMemberList() {
        List<Member> memberList = memberService.getMemberList();
        return new ResponseEntity(memberList, HttpStatus.OK);
    }
}
