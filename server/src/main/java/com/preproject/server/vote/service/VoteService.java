package com.preproject.server.vote.service;

import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.entity.Member;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.IS_VOTED;
import com.preproject.server.vote.dto.VoteResponseDto;
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

  private Long getAuthenticatedMemberId() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      throw new BusinessLogicException(VoteExceptionCode.NOT_SIGNED_IN);
    } else {
      LinkedHashMap principal = (LinkedHashMap) authentication.getPrincipal();
      return Long.valueOf((Integer) principal.get("id"));
    }
  }

  public VoteResponseDto questionVoteUp(long questionId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .question(questionService.findQuestion(questionId)).status(
              status.PLUS).build();
      voteRepository.save(vote);
      long count = questionService.addQuestionVoteCount(vote.getQuestion(),
          vote.getStatus().getNum());
      return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.VOTE_PLUS).build();
    } else {
      voteRepository.delete(prev.get());
      if (prev.get().getStatus().getNum() == 1) {
        long count = questionService.addQuestionVoteCount(prev.get().getQuestion(),
            status.MINUS.getNum());
        return VoteResponseDto.builder().isVoted(IS_VOTED.NOT_VOTED).voteCount(count).build();
      } else {
        Vote vote = Vote.builder().member(member)
            .question(questionService.findQuestion(questionId)).status(
                status.PLUS).build();
        voteRepository.save(vote);
        long count = questionService.addQuestionVoteCount(vote.getQuestion(),
            vote.getStatus().getNum() * 2);
        return VoteResponseDto.builder().isVoted(IS_VOTED.VOTE_PLUS).voteCount(count).build();
      }
    }
  }

  public VoteResponseDto questionVoteDown(long questionId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .question(questionService.findQuestion(questionId)).status(
              status.MINUS).build();
      voteRepository.save(vote);
      long count = questionService.addQuestionVoteCount(vote.getQuestion(),
          vote.getStatus().getNum());
      return VoteResponseDto.builder().isVoted(IS_VOTED.VOTE_MINUS).voteCount(count).build();
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prevVote.getStatus().getNum() == -1) {
        long count = questionService.addQuestionVoteCount(prev.get().getQuestion(),
            status.PLUS.getNum());
        return VoteResponseDto.builder().isVoted(IS_VOTED.NOT_VOTED).voteCount(count).build();
      } else {
        Vote vote = Vote.builder().member(member)
            .question(questionService.findQuestion(questionId)).status(status.MINUS).build();
        voteRepository.save(vote);
        long count = questionService.addQuestionVoteCount(vote.getQuestion(),
            vote.getStatus().getNum() * 2);
        return VoteResponseDto.builder().isVoted(IS_VOTED.VOTE_MINUS).voteCount(count).build();
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

  public VoteResponseDto answerVoteUp(long questionId, long answerId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndAnswer(memberId, answerId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .answer(answerService.findAnswer(answerId)).status(status.PLUS).build();
      voteRepository.save(vote);
      long count = answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus().getNum());
      return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.VOTE_PLUS).build();
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prevVote.getStatus().getNum() == 1) {
        long count = answerService.addAnswerVoteCount(prevVote.getAnswer(), status.MINUS.getNum());
        return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.NOT_VOTED).build();
      } else {
        Vote vote = Vote.builder().member(member)
            .answer(answerService.findAnswer(answerId)).status(status.PLUS).build();
        voteRepository.save(vote);
        long count = answerService.addAnswerVoteCount(vote.getAnswer(),
            vote.getStatus().getNum() * 2);
        return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.VOTE_PLUS).build();
      }
    }
  }

  public VoteResponseDto answerVoteDown(long questionId, long answerId) {
    long memberId = getAuthenticatedMemberId();
    Member member = memberService.getMember(memberId);
    verifyMemberCanVote(member);
    Optional<Vote> prev = voteRepository.findVoteByMemberAndAnswer(memberId, answerId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(member)
          .answer(answerService.findAnswer(answerId)).status(status.MINUS).build();
      voteRepository.save(vote);
      long count = answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus().getNum());
      return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.VOTE_MINUS).build();
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prevVote.getStatus().getNum() == -1) {
        long count = answerService.addAnswerVoteCount(prevVote.getAnswer(), status.PLUS.getNum());
        return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.NOT_VOTED).build();
      } else {
        Vote vote = Vote.builder().member(member)
            .answer(answerService.findAnswer(answerId)).status(status.MINUS).build();
        voteRepository.save(vote);
        long count = answerService.addAnswerVoteCount(vote.getAnswer(),
            vote.getStatus().getNum() * 2);
        return VoteResponseDto.builder().voteCount(count).isVoted(IS_VOTED.VOTE_MINUS).build();
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
