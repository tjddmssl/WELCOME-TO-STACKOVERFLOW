package com.preproject.server.member.mapper;

import com.preproject.server.member.dto.*;
import com.preproject.server.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

  Member postDtoToMember(MemberPostDto memberPostDto);

//    MemberPostResponseDto memberToMemberDto(Member member);

  @Mapping(source = "profile", target = "profileImage")
  MemberSimpleDto memberToSimpleDto(Member member);

  //리펙토링 부분
  default Member patchDtoToMember(MemberPatchDto patchDto) {
    return Member.builder()
        .password(patchDto.getPassword())
        .displayName(patchDto.getDisplayName())
        .profile(patchDto.getProfileImage())
        .aboutMe(patchDto.getAboutMe())
        .build();
  }
  //Tag 기능은 변환하지 못했다

  @Mapping(source = "id", target = "id")
  MemberListDto memberToMemberListDto(Member member);

  //tag 미구현
  MemberResponseDto memberResponseDtoToMember(Member member);
}

