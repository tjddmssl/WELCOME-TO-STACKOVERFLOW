package com.preproject.server.member.mapper;

import com.preproject.server.member.dto.MemberDto;
import com.preproject.server.member.dto.MemberPostDto;
import com.preproject.server.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member postDtoToMember(MemberPostDto memberPostDto);

    MemberDto memberToMemberDto(Member member);
}
