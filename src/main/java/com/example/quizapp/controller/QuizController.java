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
    public ResponseEntity<List<Question>> getQuizQuestions() {
        String url = "https://opentdb.com/api.php?amount=10&category=11&difficulty=easy&type=multiple";
        RestTemplate restTemplate = new RestTemplate();
        TriviaResponse response = restTemplate.getForObject(url, TriviaResponse.class);
        assert response != null;
        return ResponseEntity.ok(response.getResults());
    }

    @PostMapping("/results")
    public QuizResult saveResult(@RequestBody QuizResult result) {
        return quizResultRepository.save(result);
    }

    @GetMapping("/results/top10")
    public List<QuizResult> getTop10Results() {
        return quizResultRepository.findTop10ByOrderByScoreDesc();
    }
}
