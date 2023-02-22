package com.preproject.server.vote.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.question.entity.Question;
import com.preproject.server.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    public Page<Question> createQuestionSimplePage(Pageable pageable, Long id) {
        return voteRepository.findSimpleQuestion(pageable, id);
    }

    public Page<Answer> createAnswerSimplePage(Pageable pageable, Long id) {
        return voteRepository.findSimpleAnswer(pageable, id);
    }
}
