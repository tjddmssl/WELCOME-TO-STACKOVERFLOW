package com.preproject.server.comment.mapper;

import com.preproject.server.comment.dao.CommentSimpleDao;
import com.preproject.server.comment.dto.CommentPostDto;
import com.preproject.server.comment.dto.CommentResponseDto;
import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.comment.entity.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

  Comment questionCommentPostDtoToComment(CommentPostDto commentPostDto);
  Comment answerCommentPostDtoToComment(CommentPostDto commentPostDto);

  @Mapping(target = "questionId", source = "question.id")
  @Mapping(target = "memberId", source = "member.id")
  CommentResponseDto commentToCommentResponseDto(Comment comment);

  CommentSimpleDto commentSimpleDaoToCommentSimpleDto(CommentSimpleDao commentSimpleDao);
  List<CommentSimpleDto> commentSimpleDaosToCommentSimpleDtos(List<CommentSimpleDao> commentSimpleDaos);
}
