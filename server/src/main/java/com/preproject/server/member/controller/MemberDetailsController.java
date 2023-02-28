package com.preproject.server.member.controller;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.dto.MemberDetailAnswerDto;
import com.preproject.server.member.dto.MemberResponseDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.service.VoteService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberDetailsController {

  private final MemberService memberService;
  private final AnswerService answerService;
  private final QuestionService questionService;
  private final VoteService voteService;
  private final MemberMapper memberMapper;


  /*
   * 유저 상세 조회 - user 정보만 따로 빼준다.
   * */
  @GetMapping("/{id}")
  public ResponseEntity getMember(@PathVariable Long id) {
    Member member = memberService.getMember(id);
    MemberResponseDto memberResponseDto = memberMapper.memberResponseDtoToMember(member);
    List<String> collect = member.getTagMembers().stream().map(tag -> tag.getTag().getName())
        .collect(Collectors.toList());
    memberResponseDto.setTags(collect);
    return new ResponseEntity(new ResponseDto(memberResponseDto), HttpStatus.OK);
  }

  @GetMapping("/{id}/answers")
  public ResponseEntity getMemberDetailAnswer(@PathVariable Long id,
      @PageableDefault(sort = "createdDate") Pageable pageable) {

    Page<Answer> answerPage = answerService.getVotedAnswers(pageable, id);
    Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
    return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

  }

  @GetMapping("/{id}/voted-answers")
  public ResponseEntity getMemberVotedAnswer(@PathVariable Long id,
      @PageableDefault(sort = "createdDate") Pageable pageable) {

    //TODO 서비스단 타고 가서 값 만들고
    Page<Answer> answerPage = voteService.createAnswerSimplePage(pageable, id);
    Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
    return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

  }
}
