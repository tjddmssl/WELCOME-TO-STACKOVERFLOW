package com.preproject.server.question.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.exception.QuestionExceptionCode;
import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.tag.entity.TagQuestion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class QuestionService {

  private final QuestionRepository questionRepository;

  public QuestionService(QuestionRepository questionRepository, MemberService memberService) {
    this.questionRepository = questionRepository;
  }

  public Question createQuestion(Question question) {
    // TODO verify if member is present
    log.info("## POST ##");
    log.info("## requested question: {}", question.toString());
    return questionRepository.save(question);
  }

  public Question updateQuestion(Question question) {
    log.info("## PATCH ##");
    log.info("## requested question: {}", question.getTagQuestions().get(0).getTag().getName());
    Question findQuestion = findQuestion(question.getId());
    Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
    Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);
    if (!question.getTagQuestions().isEmpty()) {
      findQuestion.setTagQuestions(question.getTagQuestions());
    }
    return questionRepository.save(findQuestion);
  }

  public Question findQuestion(Long questionId) {
    Optional<Question> questionOptional = questionRepository.findById(questionId);
    return questionOptional.orElseThrow(
        () -> new BusinessLogicException(QuestionExceptionCode.QUESTION_NOT_FOUND));
  }

  public Question removeQuestion(Long questionId) {
    Question findQuestion = findQuestion(questionId);
    questionRepository.delete(findQuestion);
    return findQuestion;
  }

  public Page<Question> createPageSimplePage(Pageable pageable, Long id) {
    return questionRepository.findSimpleQuestion(pageable, id);
  }
}
