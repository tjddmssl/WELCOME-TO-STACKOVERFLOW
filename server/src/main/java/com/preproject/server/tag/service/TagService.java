package com.preproject.server.tag.service;

import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Page<Tag> findTags(Pageable pageable) {
    return tagRepository.findAll(pageable);
  }
  /*
  * 전체 tag 리스트 조회
  * */
  public List<Tag> findTagList() {
    return tagRepository.findAll();
  }
}
