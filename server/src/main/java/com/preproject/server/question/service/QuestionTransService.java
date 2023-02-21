package com.preproject.server.question.service;

import com.preproject.server.question.mapper.QuestionMapper;
import com.preproject.server.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionTransService {

  private final QuestionMapper questionMapper;
  private final QuestionRepository questionRepository;
  private final QuestionService questionService;




}
