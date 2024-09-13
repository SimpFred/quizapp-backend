package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizResult;
import com.example.quizapp.model.TriviaResponse;
import com.example.quizapp.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    @Autowired
    private QuizResultRepository quizResultRepository;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuizQuestions(
            @RequestParam int amount,
            @RequestParam int category,
            @RequestParam String difficulty) {

        String url = String.format("https://opentdb.com/api.php?amount=%d&category=%d&difficulty=%s&type=multiple",
                amount, category, difficulty);
        System.out.println(url + ": URL");
        RestTemplate restTemplate = new RestTemplate();
        TriviaResponse response = restTemplate.getForObject(url, TriviaResponse.class);
        assert response != null;
        return ResponseEntity.ok(response.getResults());
    }

    @PostMapping("/results")
    public QuizResult saveResult(@RequestBody QuizResult result) {
        return quizResultRepository.save(result);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<QuizResult>> getTop10Results(
            @RequestParam int category,
            @RequestParam int numberOfQuestions,
            @RequestParam String difficulty
    ) {
        return ResponseEntity.ok(quizResultRepository.findTop10ByCategoryAndNumberOfQuestionsAndDifficultyOrderByScoreDesc(category, numberOfQuestions, difficulty));
    }
}
