package com.preproject.server.member.controller;

import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.dto.*;
import com.preproject.server.member.dtotmp.VoteDto;
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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    /*
     * 회원가입 컨트롤
     * */
    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberPostDto post, HttpServletRequest request) {
        Locale locale = request.getLocale();
        log.info("locale = {} ", locale);
        Member member = memberMapper.postDtoToMember(post);
        member.setLocation(locale.getCountry());
        log.info("member = {}", member.getId());
        Member findMember = memberService.createMember(member);

        //dto 변환 로직
        MemberDto memberDto = memberMapper.memberToMemberDto(findMember);

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

    /*
    * 회원 삭제 기능 but 비밀 번호 암호화 시 추가 변경 필요
    * */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id,@RequestBody String password) {
        memberService.deleteMember(id, password);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /*
    * 회원 정보 수정
    * */
    @PatchMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberPatchDto patchDto) {
        Member member = memberMapper.patchDtoToMember(patchDto);

        //TODO 리펙토링 patchDto.getTag() -> List<TagMember> 변환 과정 만들기
        Member updatedMember = memberService.updatedMember(member, patchDto.getTag());


        MemberPatchResponseDto responseDto = memberMapper.patchResponseDtoToMember(updatedMember);

        //TODO 리펙토링
        List<String> collect = updatedMember.getTagMembers().stream()
                .map(tag -> tag.getTag().getName()).collect(Collectors.toList());
        responseDto.setTag(collect);

        return new ResponseEntity(new ResponseDto<MemberPatchResponseDto>(responseDto),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMemberList() {
        List<Member> memberList = memberService.getMemberList();
        return new ResponseEntity(new ResponseDto(memberList), HttpStatus.OK);
    }


}
