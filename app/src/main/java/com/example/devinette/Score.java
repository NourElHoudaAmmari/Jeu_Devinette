package com.example.devinette;

import java.util.ArrayList;
import java.util.Date;

public class Score {
    public static ArrayList<Score> scoreArrayList = new ArrayList<>();
    private int idScore;
    private String name;
    private String score;
    private Date when_;

    public Score(int idScore, String name, String score) {
        this.idScore = idScore;
        this.name = name;
        this.score = score;
    }

    public Score(int idScore, String name, String score, Date when_) {
        this.idScore = idScore;
        this.name = name;
        this.score = score;
        this.when_ = when_;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getWhen_() {
        return when_;
    }

    public void setWhen_(Date when_) {
        this.when_ = when_;
    }
}
