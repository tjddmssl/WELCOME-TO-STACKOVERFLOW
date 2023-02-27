package com.preproject.server.tag.controller;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.answer.repository.AnswerRepository;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.comment.repository.CommentRepository;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.question.entity.Question;
import com.preproject.server.question.repository.QuestionRepository;
import com.preproject.server.tag.dto.TagGetDto;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.tag.repository.TagRepository;
import com.preproject.server.tag.service.TagService;
import com.preproject.server.tag.service.TagTransService;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트용
@RestController
@RequiredArgsConstructor
public class TagController {

  private final TagRepository tagRepository;
  private final MemberRepository memberRepository;
  private final QuestionRepository questionRepository;
  private final CommentRepository commentRepository;
  private final AnswerRepository answerRepository;


  private final TagTransService tagTransService;
  private final TagService tagService;

  @GetMapping("/tags")
  public ResponseEntity getTags(
      @PageableDefault(size = 28, sort = "name") Pageable pageable) {
    Page<TagGetDto> page = tagTransService.tagPageToTagGetDtoPage(tagService.findTags(pageable));
    return ResponseEntity.ok().body(new ResponseDto<>(page));
  }

  @PostConstruct
  public void initTag() {
    List<Tag> tags = List.of(
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
        Question.builder().title("title").content("content").member(memberList.get(0))
            .tagQuestions(List.of(
                TagQuestion.builder().tag(tags.get(0)).question(Question.builder().id(1L).build())
                    .build())).build(),
        Question.builder().title("title").content("content").member(memberList.get(1))
            .tagQuestions(List.of(
                TagQuestion.builder().tag(tags.get(0)).question(Question.builder().id(2L).build())
                    .build())).build(),
        Question.builder().title("title").content("content").member(memberList.get(2))
            .tagQuestions(List.of(
                TagQuestion.builder().tag(tags.get(2)).question(Question.builder().id(1L).build())
                    .build())).build()
    );
    List<Comment> comments = List.of(
        Comment.builder().member(memberList.get(0)).content("comment").question(questions.get(0))
            .build(),
        Comment.builder().member(memberList.get(1)).content("comment").question(questions.get(1))
            .build(),
        Comment.builder().member(memberList.get(2)).content("comment").question(questions.get(0))
            .build()
    );
    List<Answer> answers = List.of(
            Answer.builder().member(memberList.get(0)).content("answer1").question(questions.get(0))
                    .build(),
            Answer.builder().member(memberList.get(1)).content("answer5").question(questions.get(0))
                    .build(),
            Answer.builder().member(memberList.get(1)).content("answer2").question(questions.get(1))
                    .build(),
            Answer.builder().member(memberList.get(2)).content("answer3").question(questions.get(2))
                    .build()
    );

//    List<Comment> comments2 = List.of(
//            Comment.builder().member(memberList.get(0)).content("comment").answer(answers.get(0))
//                    .build(),
//            Comment.builder().member(memberList.get(1)).content("comment").answer(answers.get(0))
//                    .build(),
//            Comment.builder().member(memberList.get(2)).content("comment").answer(answers.get(1))
//                    .build()
//    );
    tagRepository.saveAll(tags);
    memberRepository.saveAll(memberList);
    questionRepository.saveAll(questions);
    commentRepository.saveAll(comments);
//    commentRepository.saveAll(comments2);
    answerRepository.saveAll(answers);
  }
}
