package com.preproject.server.tag.repository;

import com.preproject.server.question.entity.Question;
import com.preproject.server.tag.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long> {

  Optional<Tag> findTagByName(String name);

}
