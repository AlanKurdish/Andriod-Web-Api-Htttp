package com.example.networkapijson.data;

import com.example.networkapijson.model.Questions;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {

    void proccessFinished(ArrayList<Questions> QuestionArrayList);
}
