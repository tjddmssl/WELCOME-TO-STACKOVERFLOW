package com.preproject.server.tag.mapper;

import com.preproject.server.tag.dto.TagGetDto;
import com.preproject.server.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

  TagGetDto tagToTagGetDto(Tag tag);
}
