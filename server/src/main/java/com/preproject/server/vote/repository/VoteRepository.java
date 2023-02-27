package com.preproject.server.vote.repository;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.question.entity.Question;
import com.preproject.server.vote.entity.Vote;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("select q from Vote v join Question q on q.id = :id")
    public Page<Question> findSimpleQuestion(Pageable pageable, @Param("id")Long id);

    @Query("select a from Vote v join Answer a on a.id = :id")
    public Page<Answer> findSimpleAnswer(Pageable pageable, @Param("id")Long id);

    @Query("select v from Vote v "
        + "join v.member m "
        + "join v.question q "
        + "where m.id = :memberId "
        + "and q.id = :questionId")
    Optional<Vote> findVoteByMemberAndQuestion(@Param("memberId") long memberId, @Param("questionId") long questionId);

    @Query("select v from Vote v join v.answer a join v.member m where a.id = :answerId and m.id = :memberId")
    Optional<Vote> findVoteByMemberAndAnswer(@Param("memberId") long memberId, @Param("answerId") long answerId);
}
