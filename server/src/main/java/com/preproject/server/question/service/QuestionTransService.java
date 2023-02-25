package com.preproject.server.question.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.question.dto.QuestionGetDto;
import com.preproject.server.question.dto.QuestionListGetDto;
import com.preproject.server.question.dto.QuestionPatchDto;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.exception.TagExceptionCode;
import com.preproject.server.tag.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionTransService {

  private final QuestionMapper questionMapper;
  private final MemberService memberService;
  private final MemberMapper memberMapper;
  private final TagService tagService;


  public Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
    Question question = questionMapper.questionPostDtoToQuestion(questionPostDto);
    question.setMember(memberService.getMember(questionPostDto.getMemberId()));
    return getTagQuestion(question, questionPostDto.getTags());
  }

  public Question questionPatchDtoToQuestion(Long id, QuestionPatchDto questionPatchDto) {
    questionPatchDto.setId(id);
    Question question = questionMapper.questionPatchDtoToQuestion(questionPatchDto);
    return getTagQuestion(question, questionPatchDto.getTags());
  }

  private Question getTagQuestion(Question question, List<String> tags) {
    List<Tag> allTags = tagService.findTagList();
    List<TagQuestion> tagQuestionList = tags.stream().map(name -> {
      TagQuestion tagQuestion = TagQuestion.builder().build();
      tagQuestion.addQuestion(question);
      tagQuestion.addTag(findTagFromTags(allTags, name));
      return tagQuestion;
    }).collect(Collectors.toList());
    question.setTagQuestions(tagQuestionList);
    return question;
  }

  private Tag findTagFromTags(List<Tag> tagList, String tagName) {
    for (Tag tag : tagList) {
      if (tag.getName().equals(tagName)) {
        return tag;
      }
    }
    throw new BusinessLogicException(TagExceptionCode.TAG_NOT_FOUND);
  }

  public QuestionGetDto questionToQuestionGetDto(Question question) {
    log.info("## Question to QuestionGetDto Trans Service ##");
    QuestionGetDto questionGetDto = questionMapper.questionToQuestionGetDto(question);
    questionGetDto.setTags(
        question.getTagQuestions().stream().map(tagQuestion -> tagQuestion.getTag().getName())
            .collect(Collectors.toList()));
    return questionGetDto;
  }

  public Page<QuestionListGetDto> questionToQuestionListGetDto(Page<Question> questionPage) {
    return questionPage.map(question -> {
      QuestionListGetDto dto = questionMapper.questionToQuestionListGetDto(question);
      dto.setTags(
          question.getTagQuestions().stream().map(tagQuestion -> tagQuestion.getTag().getName())
              .collect(Collectors.toList()));
      dto.setMember(memberMapper.memberToSimpleDto(question.getMember()));
      return dto;
    });
  }
}
