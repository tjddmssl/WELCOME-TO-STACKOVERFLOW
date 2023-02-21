package com.preproject.server.tag.mapper;

import com.preproject.server.tag.dto.TagQuestionDto;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.service.TagService;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

  TagQuestionDto tagToTagQuestionDto(Tag tag);
  default TagQuestion tagToTagQuestion(Tag tag){
    return TagQuestion.builder().tag(tag).build();
  }
  List<TagQuestionDto> tagListToTagQuestionDtoList(List<Tag> tagList);

//  Tag tagQuestionDtoToTag(TagQuestionDto tagQuestionDto);
//  List<Tag> tagQuestionDtoListToTagList(List<TagQuestionDto> tagQuestionDtoList);
}
