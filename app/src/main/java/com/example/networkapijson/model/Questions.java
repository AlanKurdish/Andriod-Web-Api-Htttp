package com.example.networkapijson.model;

public class Questions {

    private String answer;
    private boolean istrue;


    public Questions() {

    }

    public Questions(String answer, boolean istrue) {
        this.answer = answer;
        this.istrue = istrue;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isIstrue() {
        return istrue;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "answer='" + answer + '\'' +
                ", istrue=" + istrue +
                '}';
    }
}
