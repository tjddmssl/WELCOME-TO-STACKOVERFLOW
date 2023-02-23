package com.preproject.server.question.service;

import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.dto.QuestionGetDto;
import com.preproject.server.question.dto.QuestionPatchDto;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionTransService {

  private final QuestionMapper questionMapper;
  private final MemberService memberService;
  private final TagService tagService;


  public Question QuestionPostDtoToQuestion(QuestionPostDto questionPostDto) {
    Question question = questionMapper.questionPostDtoToQuestion(questionPostDto);
    question.setMember(memberService.getMember(questionPostDto.getMemberId()));
    return getTagQuestion(question, questionPostDto.getTags());
  }

  public Question QuestionPatchDtoToQuestion(Long id, QuestionPatchDto questionPatchDto) {
    questionPatchDto.setId(id);
    Question question = questionMapper.questionPatchDtoToQuestion(questionPatchDto);
    return getTagQuestion(question, questionPatchDto.getTags());
  }

  private Question getTagQuestion(Question question, List<String> tags) {
    List<TagQuestion> tagQuestionList = tags.stream().map(name -> {
      Tag tag = tagService.findTag(name);
      TagQuestion tagQuestion = TagQuestion.builder().build();
      tagQuestion.addQuestion(question);
      tagQuestion.addTag(tag);
      return tagQuestion;
    }).collect(Collectors.toList());
    question.setTagQuestions(tagQuestionList);
    return question;
  }

  public QuestionGetDto QuestionToQuestionGetDto(Question question) {
    QuestionGetDto questionGetDto = questionMapper.questionToQuestionGetDto(question);
    log.info(questionGetDto.toString());
    return questionGetDto;
  }

}
