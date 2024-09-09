package com.example.quizapp.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class QuizResult {
    // Getters och Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int score;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
