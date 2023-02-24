package com.preproject.server.member.controller;

import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.dto.MemberDetailAnswerDto;
import com.preproject.server.member.dto.MemberDetailQuestionDto;
import com.preproject.server.member.dto.MemberResponseDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("JAVA");
//        strings.add("Spring Boot");
//        MemberResponseDto responseDto = new MemberResponseDto(1L, "email@gmail.com", "qwerrfd1333", "우리집강아지뽀삐", "URL  값이 들어갈 것같습니다.", "이 글은 동쪽 해가 뜨는 최초의 땅에서 시작해 이곳 저곳에서 행운을 가져다 주는 어쩌고 저쩌고`~", "KR", LocalDateTime.now(), LocalDateTime.now(), strings);
//        return new ResponseEntity(new ResponseDto<>(responseDto),HttpStatus.CREATED);
  }


  @GetMapping("/{id}/questions")
  public ResponseEntity getMemberDetailQuestion(@PathVariable Long id,
      @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

    //TODO 서비스단 타고 가서 값 만들고
        Page<Question> questionPage = questionService.createPageSimplePage(pageable, id);
        Page<MemberDetailQuestionDto> result = questionPage.map(MemberDetailQuestionDto::new);
        return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);
//
//    ArrayList<String> strings = new ArrayList<>();
//    strings.add("JAVA");
//    strings.add("Spring Boot");
//    Page<MemberDetailQuestionDto> memberPage = new PageImpl<>(
//        List.of(new MemberDetailQuestionDto(1L, "test", strings, 1L, 1L, LocalDateTime.now()),
//            new MemberDetailQuestionDto(2L, "test", strings, 2L, 1L, LocalDateTime.now()),
//            new MemberDetailQuestionDto(3L, "test", strings, 1L, 1L, LocalDateTime.now())),
//        PageRequest.of(0, 10), 3);
//    return new ResponseEntity(new ResponseDto<>(memberPage), HttpStatus.OK);
  }

  @GetMapping("/{id}/answer")
  public ResponseEntity getMemberDetailAnswer(@PathVariable Long id,
      @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

    //TODO 서비스단 타고 가서 값 만들고
//        Page<Answer> answerPage = answerService.createSimplePage(pageable,id);
//        Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
//        return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

    Page<MemberDetailAnswerDto> memberPage = new PageImpl<>(
        List.of(new MemberDetailAnswerDto(1L, "content", 1L, LocalDateTime.now()),
            new MemberDetailAnswerDto(2L, "content", 1L, LocalDateTime.now())),
        PageRequest.of(0, 10), 3);
    return new ResponseEntity(new ResponseDto<>(memberPage), HttpStatus.OK);
  }

  @GetMapping("/{id}/voted-questions")
  public ResponseEntity getMemberVotedQuestion(@PathVariable Long id,
      @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

    //TODO 서비스단 타고 가서 값 만들고
//        Page<Question> questionPage = voteService.createQuestionSimplePage(pageable,id);
//        Page<MemberDetailQuestionDto> result = questionPage.map(MemberDetailQuestionDto::new);
//        return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

    ArrayList<String> strings = new ArrayList<>();
    strings.add("JAVA");
    strings.add("Spring Boot");
    Page<MemberDetailQuestionDto> memberPage = new PageImpl<>(
        List.of(new MemberDetailQuestionDto(1L, "test", strings, 1L, 1L, LocalDateTime.now()),
            new MemberDetailQuestionDto(2L, "test", strings, 2L, 1L, LocalDateTime.now()),
            new MemberDetailQuestionDto(3L, "test", strings, 1L, 1L, LocalDateTime.now())),
        PageRequest.of(0, 10), 3);
    return new ResponseEntity(new ResponseDto<>(memberPage), HttpStatus.OK);

  }

  @GetMapping("/{id}/voted-answers")
  public ResponseEntity getMemberVotedAnswer(@PathVariable Long id,
      @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

    //TODO 서비스단 타고 가서 값 만들고
//        Page<Answer> answerPage = voteService.createAnswerSimplePage(pageable,id);
//        Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
//        return new ResponseEntity(new ResponseDto<>(result), HttpStatus.OK);

    Page<MemberDetailAnswerDto> memberPage = new PageImpl<>(
        List.of(new MemberDetailAnswerDto(1L, "content", 1L, LocalDateTime.now()),
            new MemberDetailAnswerDto(2L, "content", 1L, LocalDateTime.now())),
        PageRequest.of(0, 10), 3);
    return new ResponseEntity(new ResponseDto<>(memberPage), HttpStatus.OK);
  }
}
