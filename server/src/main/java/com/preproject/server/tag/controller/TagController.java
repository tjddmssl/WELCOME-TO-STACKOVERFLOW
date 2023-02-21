package com.preproject.server.tag.controller;

import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.repository.TagRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;

// 테스트용
@RestController
public class TagController {

  private final TagRepository tagRepository;
  private final MemberRepository memberRepository;

  public TagController(TagRepository tagRepository, MemberRepository memberRepository) {
    this.tagRepository = tagRepository;
    this.memberRepository = memberRepository;
  }

  @PostConstruct
  public void initTag() {
    List<Tag> list = List.of(
        Tag.builder().name("java").description("java description").build(),
        Tag.builder().name("javascript").description("javascript description").build(),
        Tag.builder().name("spring").description("spring description").build()
    );
    List<Member> memberList = List.of(
        Member.builder().email("email").displayName("name1").build(),
        Member.builder().email("email").displayName("name1").build(),
        Member.builder().email("email").displayName("name1").build()
    );
    tagRepository.saveAll(list);
    memberRepository.saveAll(memberList);
  }
}
