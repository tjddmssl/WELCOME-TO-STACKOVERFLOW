package com.preproject.server.comment.service;

import com.preproject.server.comment.dto.CommentPostDto;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.mapper.CommentMapper;
import com.preproject.server.member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentTransService {

  private final CommentMapper commentMapper;

  public Comment QuestionCommentPostDtoToComment(CommentPostDto commentPostDto,
      Long questionId) {
    Comment comment = commentMapper.questionCommentPostDtoToComment(commentPostDto);
    comment.setQuestion(questionId);
    comment.setMember(commentPostDto.getMemberId());
    return comment;
  }

}
