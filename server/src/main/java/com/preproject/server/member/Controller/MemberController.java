package com.preproject.server.member.Controller;

import com.preproject.server.member.Service.MemberServiceImpl;
import com.preproject.server.member.dto.ResponseDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.MemberMapper;
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
    private final MemberServiceImpl memberService;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity createMember(@RequestBody com.preproject.server.member.dto.MemberPostDto post) {

        Member member = mapper.postDtoToMember(post);
        log.info("member = {}", member.getId());
        Member findMember = memberService.createMember(member);
        log.info("findMember = {}", findMember.getId());
        return new ResponseEntity(new ResponseDto(findMember), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins ="*", allowedHeaders = "*")
    public ResponseEntity getMember(@PathVariable Long id ) {
        Member member = memberService.getMember(id);

        return new ResponseEntity(new ResponseDto(member), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity getMemberList() {
        List<Member> memberList = memberService.getMemberList();
        return new ResponseEntity(new ResponseDto(memberList), HttpStatus.OK);
    }
}
