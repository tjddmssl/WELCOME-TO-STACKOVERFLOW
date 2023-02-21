package com.preproject.server.tag.controller;

import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.repository.TagRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

  private final TagRepository tagRepository;

  public TagController(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @PostConstruct
  public void initTag() {
    List<Tag> list = List.of(
        Tag.builder().name("java").description("java description").build(),
        Tag.builder().name("javascript").description("javascript description").build(),
        Tag.builder().name("spring").description("spring description").build()
    );
    tagRepository.saveAll(list);
  }
}
