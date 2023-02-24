package com.preproject.server.answer.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.exception.AnswerExceptionCode;
import com.preproject.server.answer.repository.AnswerRepository;
import com.preproject.server.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

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





    public void removeAnswer(Long answerId) {
        Answer findAnswer = findAnswer(answerId);
        answerRepository.delete(findAnswer);
    }


    public Page<Answer> createSimplePage(Pageable pageable, Long id) {
        return answerRepository.findSimpleAnswer(pageable,id);
    }
}
