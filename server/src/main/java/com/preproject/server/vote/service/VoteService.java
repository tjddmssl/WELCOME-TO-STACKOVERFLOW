package com.preproject.server.vote.service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.service.QuestionService;
import com.preproject.server.vote.IS_VOTED;
import com.preproject.server.vote.entity.Vote;
import com.preproject.server.vote.entity.Vote.status;
import com.preproject.server.vote.exception.VoteExceptionCode;
import com.preproject.server.vote.repository.VoteRepository;
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

  public Page<Question> createQuestionSimplePage(Pageable pageable, Long id) {
    return voteRepository.findSimpleQuestion(pageable, id);
  }

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
    // 전에 투표 x
    // -> 새로운 vote 추가 후 +1
    // 전에 투표 o
    // 전에 한거 삭제
    // 전에 up 후 up
    // -> -1 (전 투표 취소)
    // 전에 down 후 up
    // -> +2 해야지 (전 투표 취소 + 이번 up)
    long memberId = getAuthenticatedMemberId();
    Optional<Vote> prev = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    log.info("## is found: {}" , prev);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(memberService.getMember(memberId))
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
        Vote vote = Vote.builder().member(memberService.getMember(memberId))
            .question(questionService.findQuestion(questionId)).status(
                status.PLUS).build();
        voteRepository.save(vote);
        return questionService.addQuestionVoteCount(vote.getQuestion(),
            vote.getStatus().getNum() * 2);
      }
    }
  }

  public long questionVoteDown(long questionId) {
    // 전에 투표 안하고 down 눌렀을 때
    // -> 그냥 vote 추가 후 -1
    // 전에 투표를 했을 때
    // db에서 전 vote 삭제
    // 전에 down 후 down 눌렀을 때
    // -> +1 되어야 함(투표 취소)
    // 전에 up 후 down 눌렀을 때
    // -> -2 되어야 함 = 전 투표 취소 + 이번 down
    long memberId = getAuthenticatedMemberId();
    Optional<Vote> prev = voteRepository.findVoteByMemberAndQuestion(memberId, questionId);
    if (prev.isEmpty()) {
      Vote vote = Vote.builder().member(memberService.getMember(memberId))
          .question(questionService.findQuestion(questionId)).status(
              status.MINUS).build();
      voteRepository.save(vote);
      return questionService.addQuestionVoteCount(vote.getQuestion(), vote.getStatus().getNum());
    } else {
      Vote prevVote = prev.get();
      voteRepository.delete(prevVote);
      if (prev.get().getStatus().getNum() == -1) {
        return questionService.addQuestionVoteCount(prev.get().getQuestion(), status.PLUS.getNum());
      } else {
        Vote vote = Vote.builder().member(memberService.getMember(memberId))
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

  public long answerVoteUp(long answerId, long memberId) {
    Vote vote = Vote.builder().member(memberService.getMember(memberId))
            .answer(answerService.findAnswer(answerId)).status(
                    status.VOTE_PLUS).build();
    voteRepository.save(vote);
    return answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus());
  }

  public long answerVoteDown(long answerId, long memberId) {
    Vote vote = Vote.builder().member(memberService.getMember(memberId))
            .answer(answerService.findAnswer(answerId)).status(
                    status.VOTE_MINUS).build();
    voteRepository.save(vote);
    return answerService.addAnswerVoteCount(vote.getAnswer(), vote.getStatus());
  }
}
