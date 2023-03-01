package com.preproject.server.comment.service;

import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

  private final CommentRepository commentRepository;

  public Comment createComment(Comment comment) {
    return commentRepository.save(comment);
  }
}
