package com.preproject.server.Member.Controller;

import com.preproject.server.Member.Service.MemberService;
import com.preproject.server.Member.dto.MemberPostDto;
import com.preproject.server.Member.dto.ResponseDto;
import com.preproject.server.Member.entity.Member;
import com.preproject.server.Member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    @PostMapping
//    @CrossOrigin("*")
    public ResponseEntity createMember(@RequestBody MemberPostDto post) {

        Member member = mapper.postDtoToMember(post);
        log.info("member = {}", member.getId());
        Member findMember = memberService.createMember(member);
        log.info("findMember = {}", findMember.getId());
        return new ResponseEntity(new ResponseDto(findMember), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
//    @CrossOrigin("*")
    public ResponseEntity getMember(@PathVariable Long id ) {
        Member member = memberService.getMember(id);

        return new ResponseEntity(new ResponseDto(member), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @CrossOrigin("*")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
//    @CrossOrigin("*")
    public ResponseEntity getMemberList() {
        List<Member> memberList = memberService.getMemberList();
        return new ResponseEntity(new ResponseDto(memberList), HttpStatus.OK);
    }
}
