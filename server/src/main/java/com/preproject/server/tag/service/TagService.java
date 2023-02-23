package com.preproject.server.tag.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.entity.Question;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagMember;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.exception.TagExceptionCode;
import com.preproject.server.tag.repository.TagRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Tag findTag(String name) {
    Optional<Tag> optionalTag = tagRepository.findTagByName(name);
    return optionalTag.orElseThrow(
        () -> new BusinessLogicException(TagExceptionCode.TAG_NOT_FOUND));
  }
//
//  public List<String> findTagNames(Long id) {
//    List<Tag> tags = tagRepository.findTagsByQuestionId(id);
//    return tags.stream().map(Tag::getName).collect(Collectors.toList());
//  }
}
