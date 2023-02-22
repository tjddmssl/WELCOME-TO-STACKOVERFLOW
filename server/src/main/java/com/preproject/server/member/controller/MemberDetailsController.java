package com.preproject.server.member.controller;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.dto.MemberDetailAnswerDto;
import com.preproject.server.member.dto.MemberDetailQuestionDto;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.service.QuestionService;
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
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberDetailsController {
    private final MemberService memberService;
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final VoteService voteService;
    private final MemberMapper memberMapper;



    @GetMapping("/{id}/questions")
    public ResponseEntity getMemberDetailQuestion(@PathVariable Long id , @PageableDefault(size = 10 , page = 0 ,sort = "createdDate") Pageable pageable) {

        //TODO 서비스단 타고 가서 값 만들고
        Page<Question> questionPage = questionService.createPageSimplePage();

        Page<MemberDetailQuestionDto> result = questionPage.map(MemberDetailQuestionDto::new);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/answer")
    public ResponseEntity getMemberDetailAnswer(@PathVariable Long id, @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

        //TODO 서비스단 타고 가서 값 만들고
        Page<Answer> answerPage = answerService.createPageSimplePage();

        Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/voted-questions")
    public ResponseEntity getMemberVotedQuestion(@PathVariable Long id, @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

        //TODO 서비스단 타고 가서 값 만들고
        Page<Question> questionPage = voteService.createQuestionSimplePage();
        Page<MemberDetailQuestionDto> result = questionPage.map(MemberDetailQuestionDto::new);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @GetMapping("/{id}/voted-answer")
    public ResponseEntity getMemberVotedAnswer(@PathVariable Long id, @PageableDefault(size = 10, page = 0, sort = "createdDate") Pageable pageable) {

        //TODO 서비스단 타고 가서 값 만들고
        Page<Answer> answerPage = tagService.createAnswerSimplePage();

        Page<MemberDetailAnswerDto> result = answerPage.map(MemberDetailAnswerDto::new);
        return new ResponseEntity(result, HttpStatus.OK);
    }


}
