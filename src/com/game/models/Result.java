package com.game.models;

public class Result{
    private final int scores;
    private final String name;

    public Result(int scores, String name) {
        this.scores = scores;
        this.name = name;
    }

    public int getScores() {
        return scores;
    }

    public String getName() {
        return name;
    }
}
