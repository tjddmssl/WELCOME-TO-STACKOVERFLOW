package com.preproject.server.tag.service;

import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.entity.Question;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagMember;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.exception.TagExceptionCode;
import com.preproject.server.tag.mapper.TagMapper;
import com.preproject.server.tag.repository.TagRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  public Tag findTag (String name) {
    Optional<Tag> optionalTag = tagRepository.findTagByName(name);
    Tag tag = optionalTag.orElseThrow(
        () -> new BusinessLogicException(TagExceptionCode.TAG_NOT_FOUND));
    return tag;
  }
}
