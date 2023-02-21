package com.preproject.server.member.controller;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.dto.MemberListDto;
import com.preproject.server.member.dto.MemberPatchDto;
import com.preproject.server.member.dto.MemberPostDto;
import com.preproject.server.member.dto.MemberResponseDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.
import com.preproject.server.question.entity.Question;
import com.preproject.server.utils.UriCreator;
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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@CrossOrigin("*")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/users";
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
        Member ceatedMember = memberService.createMember(member);

        //dto 변환 로직
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL,ceatedMember.getId());

        return ResponseEntity.created(location).build();
    }

    /*
     * 유저 상세 조회 - user 정보만 따로 빼준다.
     * */
    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable Long id) {
        Member member = memberService.getMember(id);

        MemberResponseDto memberResponseDto = memberMapper.memberResponseDtoToMember(member);
        List<String> collect = member.getTagMembers().stream().map(tag -> tag.getTag().getName()).collect(Collectors.toList());
        memberResponseDto.setTags(collect);

        return new ResponseEntity(new ResponseDto(memberResponseDto), HttpStatus.OK);
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


        MemberResponseDto responseDto = memberMapper.memberResponseDtoToMember(updatedMember);

        //TODO 리펙토링
        List<String> collect = updatedMember.getTagMembers().stream()
                .map(tag -> tag.getTag().getName()).collect(Collectors.toList());
        responseDto.setTags(collect);

        return new ResponseEntity(new ResponseDto<MemberResponseDto>(responseDto),HttpStatus.OK);
    }

    /*
    * 회원들 정보 조회 수정 필요
    * */
    @GetMapping
    public ResponseEntity getMemberList(@PageableDefault(size = 28, sort = "createdAt") Pageable pageable) {
        Page<Member> memberPage = memberService.getPageMember();

        Page<MemberListDto> pageDto = memberPage.map(member -> {
            MemberListDto responseDto = memberMapper.memberToMemberListDto(member);
            List<String> collect = member.getTagMembers().stream().map(tag -> tag.getTag().getName()).collect(Collectors.toList());
            responseDto.setTags(collect);

            long sum = member.getQuestions().stream().mapToLong(Question::getVoteCount).sum();
            sum += member.getAnswers().stream().mapToLong(Answer::getVoteCount).sum();

            responseDto.setVoteCount(sum);
            return responseDto;
        });

        return new ResponseEntity(pageDto,HttpStatus.OK);
    }


}
