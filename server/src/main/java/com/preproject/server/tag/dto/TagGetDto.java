package com.preproject.server.tag.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class TagGetDto {

  private Long id;
  private String name;
  private String description;
  @Setter
  private long questionCount;
  @Setter
  private LocalDateTime createdDate;
}
