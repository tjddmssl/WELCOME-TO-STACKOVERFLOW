package com.preproject.server.member.Controller;

import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.dto.MemberDto;
import com.preproject.server.member.dto.MemberPostDto;
import com.preproject.server.member.dto.ResponseDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    /*
     * 회원가입 컨트롤
     * */
    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberPostDto post, HttpServletRequest request) {
        Locale locale = request.getLocale();
        log.info("locale = {} ", locale);
        Member member = mapper.postDtoToMember(post);
        member.setLocation(locale.getCountry());
        log.info("member = {}", member.getId());
        Member findMember = memberService.createMember(member);

        //dto 변환 로직
        MemberDto memberDto = mapper.memberToMemberDto(findMember);


        return new ResponseEntity(new ResponseDto(memberDto), HttpStatus.CREATED);
    }

    /*
     * 유저 상세 조회
     * */
    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable Long id) {
        Member member = memberService.getMember(id);

        return new ResponseEntity(new ResponseDto(), HttpStatus.OK);
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
