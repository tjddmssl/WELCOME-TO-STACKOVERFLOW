package com.preproject.server.question.service;

import com.preproject.server.Member.Service.MemberService;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.exception.QuestionExceptionCode;
import com.preproject.server.question.repository.QuestionRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
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

  public Question createQuestion(Question question){
    // TODO verify if member is present
    // TODO verify if tag is available
    Question savedQuestion = questionRepository.save(question);
    return savedQuestion;
  }

  public Question findQuestion(Long questionId) {
    Optional<Question> questionOptional = questionRepository.findById(questionId);
    return questionOptional.orElseThrow(() -> new BusinessLogicException(QuestionExceptionCode.QUESTION_NOT_FOUND));
  }



}
