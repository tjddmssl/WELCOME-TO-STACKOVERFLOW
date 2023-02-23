package com.preproject.server.question;

import com.preproject.server.question.dto.QuestionPostDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionServiceTest {

  @Test
  public void getQuestionTest() {
    QuestionPostDto post = QuestionPostDto.builder()
        .build();
  }

}
