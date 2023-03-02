package com.preproject.server.member.controller;

import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.Service.MemberTransService;
import com.preproject.server.member.dto.MemberListDto;
import com.preproject.server.member.dto.MemberPatchDto;
import com.preproject.server.member.dto.MemberPostDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final MemberTransService memberTransService;


    /*
     * 회원가입 컨트롤
     * */
    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberPostDto post, HttpServletRequest request) {
        log.info("#####POST MEMBER #####");
        log.info("####post = {}", post);
        Locale locale = request.getLocale();
        Member member = memberMapper.postDtoToMember(post);
        member.setLocation(locale.getCountry());

        URI location = memberTransService.createMember(member);


        return ResponseEntity.created(location).build();


    }


    /*
     * 회원 삭제 기능 but 비밀 번호 암호화 시 추가 변경 필요
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        log.info("##### DELETE MEMBER #####");
        log.trace("### MEMBER ID = {}",id);
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /*
     * 회원 정보 수정
     * */
    @PatchMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberPatchDto patchDto) {
        log.info("##### UPDATE MEMBER #####");
        patchDto.setId(id);
        Member member = memberMapper.patchDtoToMember(patchDto);    //상태 변환
        log.info("#### member = {}",member);

        URI location = memberTransService.updateMember(member, patchDto.getTag());
        log.info("###URI = {}", location);
        return ResponseEntity.ok(location);
    }

    /*
     * 회원들 정보 조회 수정 필요
     * */
    @GetMapping
    public ResponseEntity getMemberList(@PageableDefault(size = 28, sort = "createdDate") Pageable pageable) {
        log.info("##### GET MEMBER PAGE #####");
        Page<MemberListDto> pageDto = memberTransService.getMemberPageDto(pageable);

        return new ResponseEntity(pageDto, HttpStatus.OK);
    }

}
