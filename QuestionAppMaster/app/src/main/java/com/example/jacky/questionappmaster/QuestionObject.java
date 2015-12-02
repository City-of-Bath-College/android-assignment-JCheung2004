package com.example.jacky.questionappmaster;

/**
 * Created by Jacky on 03/11/2015.
 */
public class QuestionObject {
    private String question;
    private int picture;

    public QuestionObject(String question, int picture) {
        this.question = question;
        this.picture = picture;
    }
    public String getQuestion() {
        return question;
    }

    public int getPicture(){
        return picture;
    }
}
