package com.preproject.server.answer.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.exception.AnswerExceptionCode;
import com.preproject.server.answer.repository.AnswerRepository;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.entity.Vote;
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
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    public Answer creatAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
    public Answer updateAnswer(Answer answer) {
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

    public long addAnswerVoteCount(Answer answer, Vote.status status) {
        answer.setVoteCount(answer.getVoteCount() + status.getNum());
        return answer.getVoteCount();
    }


    public void removeAnswer(Long answerId) {
        Answer findAnswer = findAnswer(answerId);
        answerRepository.delete(findAnswer);
    }


    public Page<Answer> createSimplePage(Pageable pageable, Long id) {
        return answerRepository.findSimpleAnswer(pageable,id);
    }
}
