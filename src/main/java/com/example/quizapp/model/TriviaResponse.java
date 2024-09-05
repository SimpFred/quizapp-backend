package com.example.quizapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TriviaResponse {
    private int response_code;
    private List<Question> results;

}