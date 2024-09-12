package com.example.quizapp.repository;

import com.example.quizapp.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findTop10ByOrderByScoreDesc();
    List<QuizResult> findTop10ByCategoryAndNumberOfQuestionsOrderByScoreDesc(int category, int numberOfQuestions);
}
