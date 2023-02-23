package com.preproject.server.answer;

import com.preproject.server.answer.dto.AnswerPatchDto;
import com.preproject.server.answer.dto.AnswerPostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/")
@Slf4j
public class AnswerController {

    @PostMapping("/question/{question-id}/answers")
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto requestBody,
                                     @PathVariable("question-id") @Positive Long questionId) {

//questionId가 바디에 들어가야하는가?

        return null;
    }

    @PatchMapping("/question/{question-id}/answers/{answer-id}")
    public ResponseEntity patchAnswer(@Valid @RequestBody AnswerPatchDto requestBody,
                                      @PathVariable("question-id") @Positive Long questionId,
                                      @PathVariable("answer-id") @Positive Long answerId) {



        return null;
    }
    @GetMapping("/question/{question-id}/answers")
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive Long questionId ) {



        return null;
    }

    @DeleteMapping("/question/{question-id}/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("question-id") @Positive Long questionId,
                                       @PathVariable("answer-id") @Positive Long answerId) {



        return null;
    }
}
