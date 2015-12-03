package com.example.jacky.questionappmaster;

/**
 * Created by che14143708 on 02/12/2015.
 */
public class HighScoreObject {

    private int score;
    private String name;
    private Long timestamp;

    public HighScoreObject(int score, String name, Long timestamp) {
        this.score = score;
        this.name = name;
        this.timestamp = timestamp;
    }

    public HighScoreObject() {
    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
    public Long getTimestamp() {
        return timestamp;
    }


}

// this is a place where the highscores are stored momentarily, they act as a place for data types to be defined