package com.preproject.server.comment.repository;


import com.preproject.server.comment.dao.CommentSimpleDao;
import com.preproject.server.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query("select new com.preproject.server.comment.dao.CommentSimpleDao(c.content, c.createdDate, q.member) "
      + "from Comment c "
      + "join c.question q "
      + "where q.id = :id")
  List<CommentSimpleDao> findSimpleComment(@Param("id") Long questionId);

}
