package com.preproject.server.question.repository;

import com.preproject.server.question.dao.RelatedQuestionDao;
import com.preproject.server.question.entity.Question;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
      + "order by q.createdDate")
  List<RelatedQuestionDao> findQuestionsByTag(@Param("id") Long tagId);
}
