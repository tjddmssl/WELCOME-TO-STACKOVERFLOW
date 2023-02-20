package com.preproject.server.comment.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.comment.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentEntityTest {

  @Autowired
  private CommentRepository commentRepository;

  @Test
  public void saveCommentTest() {
    Answer answer = Answer.builder().content("contentetnetne").voteCount(3L).build();
    Comment comment = Comment.builder().content("comment").answer(answer).build();
    Comment savedComment = commentRepository.save(comment);
    assertThat(savedComment, is(notNullValue()));
    assertThat(answer.getContent(), is(savedComment.getAnswer().getContent()));
  }
}
