package com.preproject.server.tag.controller;

import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.repository.CommentRepository;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.repository.TagRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 테스트용
@RestController
@RequiredArgsConstructor
public class TagController {

  private final TagRepository tagRepository;
  private final MemberRepository memberRepository;
  private final QuestionRepository questionRepository;
  private final CommentRepository commentRepository;

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
    List<Question> questions = List.of(
        Question.builder().title("title").content("content").member(memberList.get(0)).build(),
        Question.builder().title("title").content("content").member(memberList.get(1)).build(),
        Question.builder().title("title").content("content").member(memberList.get(2)).build()
    );
    List<Comment> comments = List.of(
        Comment.builder().member(memberList.get(0)).content("comment").question(questions.get(0))
            .build(),
        Comment.builder().member(memberList.get(1)).content("comment").question(questions.get(1))
            .build(),
        Comment.builder().member(memberList.get(2)).content("comment").question(questions.get(0))
            .build()
    );
    tagRepository.saveAll(list);
    memberRepository.saveAll(memberList);
    questionRepository.saveAll(questions);
    commentRepository.saveAll(comments);
  }
}
