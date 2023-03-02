package com.preproject.server.comment.service;

import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.comment.dto.CommentPostDto;
import com.preproject.server.comment.dto.CommentResponseDto;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.exception.CommentExceptionCode;
import com.preproject.server.comment.mapper.CommentMapper;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class CommentTransService {

  private final CommentMapper commentMapper;
  private final QuestionService questionService;
  private final AnswerService answerService;

  private LinkedHashMap checkAuthenticated() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
      throw new BusinessLogicException(CommentExceptionCode.NOT_SIGNED_IN);
    }
    return (LinkedHashMap) authentication.getPrincipal();
  }

  public Comment questionCommentPostDtoToComment(CommentPostDto commentPostDto,
      Long questionId) {
    LinkedHashMap principal = checkAuthenticated();
    Comment comment = commentMapper.questionCommentPostDtoToComment(commentPostDto);
    comment.setQuestion(questionService.findQuestion(questionId));
    comment.setMember(Long.valueOf((Integer) principal.get("id")));
    return comment;
  }

  public Comment answerCommentPostDtoToComment(CommentPostDto commentPostDto,
      Long answerId, Long questionId) {
//    LinkedHashMap principal = checkAuthenticated();
    Comment comment = commentMapper.answerCommentPostDtoToComment(commentPostDto);
    comment.setAnswer(answerService.findAnswer(answerId));
//    comment.setMember(Long.valueOf((Integer) principal.get("id")));
    comment.setMember(1L);
    return comment;
  }

  public CommentResponseDto commentToCommentResponseDto(Comment comment) {
    return commentMapper.commentToCommentResponseDto(comment);
  }

}
