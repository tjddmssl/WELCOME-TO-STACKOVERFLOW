package com.preproject.server.question.repository;

import com.preproject.server.question.dao.RelatedQuestionDao;
import com.preproject.server.question.entity.Question;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  @Query("select q from Question q where q.member.id  = :id")
  Page<Question> findSimpleQuestion(Pageable pageable, @Param("id") Long id);

  @Query("select new com.preproject.server.question.dao.RelatedQuestionDao(q.id, q.title) "
      + "from Question q "
      + "join q.tagQuestions tq "
      + "join tq.tag t "
      + "where t.id = :id "
      + "order by q.createdDate desc ")
  List<RelatedQuestionDao> findQuestionsByTag(@Param("id") Long tagId);

  @Query("select q from Question q join q.tagQuestions tq join tq.tag t where t.name = :name")
  Page<Question> findQuestionsByTagName(@Param("name") String tagName, Pageable pageable);

  @Query("select COUNT(q) "
      + "from Question q "
      + "join q.tagQuestions tq "
      + "join tq.tag t where t.id = :id")
  Long getQuestionCountByTag(@Param("id") Long id);

  @Query("select q.createdDate from Question q "
      + "join q.tagQuestions tq "
      + "join tq.tag t where t.id = :id order by q.createdDate desc")
  List<LocalDateTime> getCreatedDateByTag(@Param("id") Long id);

  @Query("select q from Question q join q.votes v join v.member m  where m.id = :id")
  Page<Question> findVotedQuestions(Pageable pageable, @Param("id") long memberId);

  @Query("select q from Question q join q.member m where m.id = :id")
  Page<Question> findQuestionsByMemberId(Pageable pageable, @Param("id") long memberId);

  Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
