package com.preproject.server.answer.repository;

import com.preproject.server.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a from Answer a where a.member.id  = :id")
    public Page<Answer> findSimpleAnswer(Pageable pageable, @Param("id") Long id);
}
