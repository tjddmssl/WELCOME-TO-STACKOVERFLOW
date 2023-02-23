package com.preproject.server.question.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.dao.RelatedQuestionDao;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.exception.QuestionExceptionCode;
import com.preproject.server.question.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository questionRepository;

  public Question createQuestion(Question question) {
    // TODO verify if member is present
    log.info("## POST QUESTION ##");
    log.info("## requested question: {}", question.toString());
    return questionRepository.save(question);
  }

  public Question updateQuestion(Question question) {
    log.info("## PATCH QUESTION ##");
    Question findQuestion = findQuestion(question.getId());
    Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
    Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);
    if (!question.getTagQuestions().isEmpty()) {
      findQuestion.setTagQuestions(question.getTagQuestions());
    }
    return questionRepository.save(findQuestion);
  }

  public Question findQuestion(Long questionId) {
    log.info("## GET QUESTIONS by id ##");
    Optional<Question> questionOptional = questionRepository.findById(questionId);
    return questionOptional.orElseThrow(
        () -> new BusinessLogicException(QuestionExceptionCode.QUESTION_NOT_FOUND));
  }

  public Page<Question> findQuestions(Pageable pageable) {
    log.info("## GET ALL QUESTIONS ##");
    return questionRepository.findAll(pageable);
  }

  public List<RelatedQuestionDao> findRelatedQuestions(Long questionId) {
    log.info("## GET RELATED QUESTIONS ##");
    Question findQuestion = findQuestion(questionId);
    List<Long> tagIdList = findQuestion.getTagQuestions().stream()
        .map(tagQuestion -> tagQuestion.getTag().getId()).collect(
            Collectors.toList());
    List<RelatedQuestionDao> questionList = new ArrayList<>();
    tagIdList.forEach(id -> {
      List<RelatedQuestionDao> list = questionRepository.findQuestionsByTag(id);
      questionList.addAll(list);
    });
    List<RelatedQuestionDao> result = questionList.stream()
        .filter(distinctByKey(RelatedQuestionDao::getId)).collect(Collectors.toList());
    return result.size() > 10 ? result.subList(0, 9) : result;
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

  public void removeQuestion(Long questionId) {
    Question findQuestion = findQuestion(questionId);
    questionRepository.delete(findQuestion);
  }

  public Page<Question> createPageSimplePage(Pageable pageable, Long id) {
    return questionRepository.findSimpleQuestion(pageable, id);
  }
}
