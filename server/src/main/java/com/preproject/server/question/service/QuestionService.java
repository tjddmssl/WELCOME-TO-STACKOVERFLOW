package com.preproject.server.question.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
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
    return questionRepository.save(question);
  }

  public Question updateQuestion(Question question) {
    Question findQuestion = findQuestion(question.getId());
    Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
    Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);
    return questionRepository.save(findQuestion);
  }

  private Question findQuestion(Long questionId) {
    Optional<Question> questionOptional = questionRepository.findById(questionId);
    return questionOptional.orElseThrow(() -> new BusinessLogicException(QuestionExceptionCode.QUESTION_NOT_FOUND));
  }

  // NOTE tag

}
