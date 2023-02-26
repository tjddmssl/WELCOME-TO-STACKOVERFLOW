package com.preproject.server.comment.service;

import com.preproject.server.answer.service.AnswerService;
import com.preproject.server.comment.dto.CommentPostDto;
import com.preproject.server.comment.dto.CommentResponseDto;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.mapper.CommentMapper;
import com.preproject.server.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentTransService {

  private final CommentMapper commentMapper;
  private final QuestionService questionService;
  private final AnswerService answerService;

  public Comment questionCommentPostDtoToComment(CommentPostDto commentPostDto,
      Long questionId) {
    Comment comment = commentMapper.questionCommentPostDtoToComment(commentPostDto);
    comment.setQuestion(questionService.findQuestion(questionId));
    comment.setMember(commentPostDto.getMemberId());
    return comment;
  }
  public Comment answerCommentPostDtoToComment(CommentPostDto commentPostDto,
                                                 Long answerId) {
    Comment comment = commentMapper.answerCommentPostDtoToComment(commentPostDto);
    comment.setAnswer(answerService.findAnswer(answerId));
    comment.setMember(commentPostDto.getMemberId());
    return comment;
  }
  public Comment answerCommentPostDtoToComment(CommentPostDto commentPostDto,
                                                 Long answerId,Long questionId) {
    Comment comment = commentMapper.answerCommentPostDtoToComment(commentPostDto);
    comment.setQuestion(questionService.findQuestion(questionId));
    comment.setAnswer(answerService.findAnswer(answerId));
    comment.setMember(commentPostDto.getMemberId());
    return comment;
  }

  public CommentResponseDto commentToCommentResponseDto(Comment comment) {
    return commentMapper.commentToCommentResponseDto(comment);
  }

}
