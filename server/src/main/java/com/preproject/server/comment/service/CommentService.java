package com.preproject.server.comment.service;

import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.exception.CommentExceptionCode;
import com.preproject.server.comment.mapper.CommentMapper;
import com.preproject.server.comment.repository.CommentRepository;
import com.preproject.server.exception.BusinessLogicException;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;

  public Comment createComment(Comment comment) {
    LinkedHashMap principal = (LinkedHashMap) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    if (principal.equals("anonymousUser")) {
      throw new BusinessLogicException(CommentExceptionCode.NOT_SIGNED_IN);
    }
    return commentRepository.save(comment);
  }
}
