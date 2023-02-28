package com.preproject.server.question.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.entity.Member;
import com.preproject.server.question.dao.RelatedQuestionDao;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.exception.QuestionExceptionCode;
import com.preproject.server.question.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final MemberService memberService;

  public LinkedHashMap checkAuthenticated() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      throw new BusinessLogicException(QuestionExceptionCode.NOT_SIGNED_IN);
    }
    return (LinkedHashMap) authentication.getPrincipal();
  }

  @Transactional
  public Question createQuestion(Question question) {
    // TODO verify if member is present
    log.info("## POST QUESTION ##");
    return questionRepository.save(question);
  }

  @Transactional
  public Question updateQuestion(Question question) {
    log.info("## PATCH QUESTION ##");
    LinkedHashMap principal = checkAuthenticated();
    Question findQuestion = findQuestion(question.getId());
    if (!Long.valueOf((Integer) principal.get("id")).equals(findQuestion.getMember().getId())) {
      throw new BusinessLogicException(QuestionExceptionCode.UNAUTHORIZED);
    }
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
    Question question = questionOptional.orElseThrow(
        () -> new BusinessLogicException(QuestionExceptionCode.QUESTION_NOT_FOUND));
    return addQuestionViewCount(question);
  }

  public Page<Question> findQuestions(Pageable pageable) {
    log.info("## GET ALL QUESTIONS ##");
    return questionRepository.findAll(pageable);
  }

  public Page<Question> findQuestionsByTagName(String tagName, Pageable pageable) {
    return questionRepository.findQuestionsByTagName(tagName, pageable);
  }

  public Page<Question> findVotedQuestions(Pageable pageable, long memberId) {
    memberService.checkMemberExist(memberId);
    return questionRepository.findVotedQuestions(pageable, memberId);
  }

  public Page<Question> findQuestionsByUser(Pageable pageable, long memberId) {
    memberService.checkMemberExist(memberId);
    return questionRepository.findQuestionsByMemberId(pageable, memberId);
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

  @Transactional
  public void removeQuestion(Long questionId) {
    Question findQuestion = findQuestion(questionId);
    LinkedHashMap principal = checkAuthenticated();
    if (!Long.valueOf((Integer) principal.get("id")).equals(findQuestion.getMember().getId())) {
      throw new BusinessLogicException(QuestionExceptionCode.UNAUTHORIZED);
    }
    questionRepository.delete(findQuestion);
  }

  @Transactional
  public long addQuestionVoteCount(Question question, int status) {
    question.setVoteCount(question.getVoteCount() + status);
    questionRepository.save(question);
    return question.getVoteCount();
  }

  private Question addQuestionViewCount(Question question) {
    question.setViewCount(question.getViewCount() + 1);
    return questionRepository.save(question);
  }

  // SEARCH - member displayName / question title, content
  private Specification<Question> search(String keyword) {
    return (q, query, cb) -> {
      query.distinct(true);
      Join<Question, Member> m = q.join("member", JoinType.LEFT);
      return cb.or(cb.like(q.get("content"), "%" + keyword + "%"),
          cb.like(q.get("title"), "%" + keyword + "%"),
          cb.like(m.get("displayName"), "%" + keyword + "%"));
    };
  }

  public Page<Question> getSearchedPage(String keyword, Pageable pageable) {
    Specification<Question> spec = search(keyword);
    return questionRepository.findAll(spec, pageable);
  }
}
