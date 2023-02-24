package com.preproject.server.tag.repository;

import com.preproject.server.tag.entity.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

  Optional<Tag> findTagByName(String name);
}
