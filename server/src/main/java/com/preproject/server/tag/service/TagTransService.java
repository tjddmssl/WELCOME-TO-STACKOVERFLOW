package com.preproject.server.tag.service;

import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.tag.dto.TagGetDto;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.mapper.TagMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagTransService {

  private final QuestionRepository questionRepository;
  private final TagMapper tagMapper;

  public Page<TagGetDto> tagPageToTagGetDtoPage(Page<Tag> tagPage) {
    return tagPage.map(tag -> {
      TagGetDto dto = tagMapper.tagToTagGetDto(tag);
      dto.setQuestionCount(questionRepository.getQuestionCountByTag(tag.getId()));
      List<LocalDateTime> createdDateList = questionRepository.getCreatedDateByTag(tag.getId());
      dto.setCreatedDate(createdDateList.size() == 0 ? null : createdDateList.get(0));
      return dto;
    });
  }
}
