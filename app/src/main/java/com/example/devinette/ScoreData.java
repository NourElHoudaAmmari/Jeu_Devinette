package com.example.devinette;

import java.util.Date;

public class ScoreData {
    private int idScore;
    private String name;
    private int score;
    private Date when_;

    public ScoreData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getWhen_() {
        return when_;
    }

    public void setWhen_(Date when_) {
        this.when_ = when_;
    }

    public ScoreData(int idScore,String name, int score, Date when_) {
        this.setIdScore(idScore);
        this.setName(name);
        this.setScore(score);
        this.setWhen_(when_);
    }

    @Override
    public String toString() {
        return idScore+":"+name+" ->" +score+"at"+when_.toString();
    }
}
