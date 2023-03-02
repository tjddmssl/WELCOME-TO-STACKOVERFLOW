package com.preproject.server.answer.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.exception.AnswerExceptionCode;
import com.preproject.server.answer.repository.AnswerRepository;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {

  private final AnswerRepository answerRepository;
  private final QuestionService questionService;
  private final MemberService memberService;

  @Transactional
  public Answer creatAnswer(Answer answer) {
    log.info("## CREATE ANSWER : {} ", answer.toString());
    return answerRepository.save(answer);
  }

  @Transactional
  public Answer updateAnswer(Answer answer) {
    log.info("## UPDATE ANSWER : {} ", answer.toString());
    Answer findAnswer = findAnswer(answer.getId());
    Optional.ofNullable(answer.getContent()).ifPresent(findAnswer::setContent);

    return answerRepository.save(findAnswer);
  }

  public Answer findAnswer(Long answerId) {
    Optional<Answer> answer = answerRepository.findById(answerId);
    return answer.orElseThrow(
        () -> new BusinessLogicException(AnswerExceptionCode.ANSWER_NOT_FOUND));
  }

  public List<Answer> findAnswersByQuestionId(Long questionId) {
    Question findQuestion = questionService.findQuestion(questionId);
    return answerRepository.findAnswersByQuestionId(findQuestion.getId());

  }

  @Transactional
  public long addAnswerVoteCount(Answer answer, int num) {
    answer.setVoteCount(answer.getVoteCount() + num);
    answerRepository.save(answer);
    return answer.getVoteCount();
  }

  @Transactional
  public void removeAnswer(Long answerId) {
    Answer findAnswer = findAnswer(answerId);
    answerRepository.delete(findAnswer);
  }

  public Page<Answer> getVotedAnswers(Pageable pageable, long id) {
    memberService.checkMemberExist(id);
    return answerRepository.findVotedAnswers(pageable, id);
  }

  public Page<Answer> getAnswersByUser(Pageable pageable, long memberId) {
    memberService.checkMemberExist(memberId);
    return answerRepository.findSimpleAnswer(pageable, memberId);
  }
}
