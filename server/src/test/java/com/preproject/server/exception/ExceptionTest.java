package com.preproject.server.exception;


import com.preproject.server.Member.entity.Member;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.repository.QuestionRepository;
import java.util.List;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Slf4j
public class ExceptionTest {

  @Autowired
  private QuestionRepository repository;
  @Test
  public void test() {
    for(int i=1;i<=10;i++) {
      Question question = Question.builder()
          .title("title"+i)
          .content("content"+i)
          .member(Member.builder().id((long) i).build())
          .build();
      repository.save(question);
    }
    PageRequest pageRequest = PageRequest.of(0, 5);
    log.warn("!!!!!!!!!!!!page: {}", repository.findAll(pageRequest));
  }
}
