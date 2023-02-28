package com.preproject.server.question.service;

import com.preproject.server.answer.exception.AnswerExceptionCode;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.question.dto.MemberQuestionDto;
import com.preproject.server.question.dto.QuestionGetDto;
import com.preproject.server.question.dto.QuestionListGetDto;
import com.preproject.server.question.dto.QuestionPatchDto;
import com.preproject.server.question.dto.QuestionPostDto;
import com.preproject.server.question.dto.VotedQuestionDto;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.exception.TagExceptionCode;
import com.preproject.server.tag.service.TagService;
import com.preproject.server.vote.IS_VOTED;
import com.preproject.server.vote.service.VoteService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class QuestionTransService {

  private final QuestionMapper questionMapper;
  private final MemberService memberService;
  private final MemberMapper memberMapper;
  private final TagService tagService;
  private final VoteService voteService;

  private LinkedHashMap checkAuthenticated() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      throw new BusinessLogicException(AnswerExceptionCode.NOT_SIGNED_IN);
    }
    return (LinkedHashMap) authentication.getPrincipal();
  }

  public Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
    LinkedHashMap principal = checkAuthenticated();
    Question question = questionMapper.questionPostDtoToQuestion(questionPostDto);
    question.setMember(memberService.getMember(Long.valueOf((Integer) principal.get("id"))));
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
    questionGetDto.setTag(
        question.getTagQuestions().stream().map(tagQuestion -> tagQuestion.getTag().getName())
            .collect(Collectors.toList()));
    // VOTED 상태 체크
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      questionGetDto.setIsVoted(IS_VOTED.NOT_SIGNED_IN);
    } else {
      LinkedHashMap principal = (LinkedHashMap) authentication.getPrincipal();
      IS_VOTED voted = voteService.getVoteStatus(((Integer) principal.get("id")).longValue(),
          question.getId());
      questionGetDto.setIsVoted(voted);
    }
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

  public Page<VotedQuestionDto> questionPageToVotedQuestionDto(Page<Question> questionPage) {
    return questionPage.map(question -> {
      VotedQuestionDto dto = questionMapper.questionToVotedQuestionDto(question);
      dto.setTag(question.getTagQuestions().stream().map(name -> name.getTag().getName()).collect(
          Collectors.toList()));
      return dto;
    });
  }

  public Page<MemberQuestionDto> questionPageToMemberQuestionDto(Page<Question> questionPage) {
    return questionPage.map(question -> {
      MemberQuestionDto dto = questionMapper.questionToMemberQuestionDto(question);
      dto.setTag(question.getTagQuestions().stream().map(name -> name.getTag().getName()).collect(
          Collectors.toList()));
      return dto;
    });
  }
}
