package com.preproject.server.Member.mapper;

import com.preproject.server.Member.dto.MemberPostDto;
import com.preproject.server.Member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member postDtoToMember(MemberPostDto memberPostDto);
}
