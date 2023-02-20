package com.preproject.server.answer.entity;


import static org.hamcrest.Matchers.notNullValue;

import com.preproject.server.answer.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnswerEntityTest {
  @Autowired
  private AnswerRepository answerRepository;

  @Test
  public void saveAnswerTest() {
    Answer answer = Answer.builder().content("contentetnetne").voteCount(3L).build();
    Answer savedAnswer = answerRepository.save(answer);
    assertThat(savedAnswer, is(notNullValue()));
    assertThat(savedAnswer.getContent(), is(answer.getContent()));
  }

}
