package com.preproject.server.comment.service;

import com.preproject.server.comment.dao.CommentSimpleDao;
import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.mapper.CommentMapper;
import com.preproject.server.comment.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;

  public Comment createComment(Comment comment) {
    // TODO verify member id available
    return commentRepository.save(comment);
  }

  public List<CommentSimpleDto> findCommentSimpleDto(Long questionId) {
    List<CommentSimpleDao> commentSimpleDaoList = commentRepository.findSimpleComment(questionId);
    return commentMapper.commentSimpleDaosToCommentSimpleDtos(commentSimpleDaoList);
  }
}
