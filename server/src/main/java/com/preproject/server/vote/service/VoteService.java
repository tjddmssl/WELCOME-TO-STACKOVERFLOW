package com.preproject.server.vote.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.entity.Member;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.IS_VOTED;
import com.preproject.server.vote.entity.Vote;
import com.preproject.server.vote.entity.Vote.status;
import com.preproject.server.vote.exception.VoteExceptionCode;
import com.preproject.server.vote.repository.VoteRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteService {

  private final VoteRepository voteRepository;
  private final MemberService memberService;
  private final QuestionService questionService;
  private final AnswerService answerService;

  public Page<Answer> createAnswerSimplePage(Pageable pageable, Long id) {
    return voteRepository.findSimpleAnswer(pageable, id);
  }

  private Long getAuthenticatedMemberId() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      throw new BusinessLogicException(VoteExceptionCode.NOT_SIGNED_IN);
    } else {
      LinkedHashMap principal = (LinkedHashMap) authentication.getPrincipal();
      return Long.valueOf((Integer) principal.get("id"));
    }
  }

  public long questionVoteUp(long questionId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .question(questionService.findQuestion(questionId)).status(
              status.PLUS).build();
      voteRepository.save(vote);
      return questionService.addQuestionVoteCount(vote.getQuestion(), vote.getStatus().getNum());
    } else {
      voteRepository.delete(prev.get());
      if (prev.get().getStatus().getNum() == 1) {
        return questionService.addQuestionVoteCount(prev.get().getQuestion(),
            status.MINUS.getNum());
      } else {
        Vote vote = Vote.builder().member(member)
            .question(questionService.findQuestion(questionId)).status(
                status.PLUS).build();
        voteRepository.save(vote);
        return questionService.addQuestionVoteCount(vote.getQuestion(),
            vote.getStatus().getNum() * 2);
      }
    }
  }

  public long questionVoteDown(long questionId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .question(questionService.findQuestion(questionId)).status(
              status.MINUS).build();
      voteRepository.save(vote);
      return questionService.addQuestionVoteCount(vote.getQuestion(), vote.getStatus().getNum());
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prevVote.getStatus().getNum() == -1) {
        return questionService.addQuestionVoteCount(prevVote.getQuestion(), status.PLUS.getNum());
      } else {
        Vote vote = Vote.builder().member(member)
            .question(questionService.findQuestion(questionId)).status(status.MINUS).build();
        voteRepository.save(vote);
        return questionService.addQuestionVoteCount(vote.getQuestion(),
            vote.getStatus().getNum() * 2);
      }
    }
  }

  public IS_VOTED getVoteStatus(long memberId, long questionId) {
    Optional<Vote> voteOptional = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    log.info("## vote is optional: {}", voteOptional);
    if (voteOptional.isPresent()) {
      if (voteOptional.get().getStatus().getNum() == 1) {
        return IS_VOTED.VOTE_PLUS;
      } else {
        return IS_VOTED.VOTE_MINUS;
      }
    } else {
      return IS_VOTED.NOT_VOTED;
    }
  }

  public long answerVoteUp(long questionId, long answerId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndAnswer(memberId, answerId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .answer(answerService.findAnswer(answerId)).status(status.PLUS).build();
      voteRepository.save(vote);
      return answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus().getNum());
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prevVote.getStatus().getNum() == 1) {
        return answerService.addAnswerVoteCount(prevVote.getAnswer(), status.MINUS.getNum());
      } else {
        Vote vote = Vote.builder().member(member)
            .answer(answerService.findAnswer(answerId)).status(status.PLUS).build();
        voteRepository.save(vote);
        return answerService.addAnswerVoteCount(vote.getAnswer(),
            vote.getStatus().getNum() * 2);
      }
    }
  }

  public long answerVoteDown(long questionId, long answerId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndAnswer(memberId, answerId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .answer(answerService.findAnswer(answerId)).status(status.MINUS).build();
      voteRepository.save(vote);
      return answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus().getNum());
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prevVote.getStatus().getNum() == -1) {
        return answerService.addAnswerVoteCount(prevVote.getAnswer(), status.PLUS.getNum());
      } else {
        Vote vote = Vote.builder().member(member)
            .answer(answerService.findAnswer(answerId)).status(status.MINUS).build();
        voteRepository.save(vote);
        return answerService.addAnswerVoteCount(vote.getAnswer(),
            vote.getStatus().getNum() * 2);
      }
    }
  }

  private void verifyMemberCanVote(Member member) {
    LocalDateTime createdDate = member.getCreatedDate();
    LocalDateTime now = LocalDateTime.now();

    // TODO: 테스트용 5초
    if (Duration.between(createdDate, now).compareTo(Duration.ofSeconds(5L)) <= 0) {
      throw new BusinessLogicException(VoteExceptionCode.TOO_EARLY_VOTE);
    }
    // TODO: 배포 시 20분
//    if(Duration.between(createdDate, now).compareTo(Duration.ofMinutes(20L)) <= 0){
//      throw new BusinessLogicException(VoteExceptionCode.TOO_EARLY_VOTE);
//    }
  }
}
