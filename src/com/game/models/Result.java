package com.game.models;

import java.io.Serializable;

public class Result implements Serializable, Comparable<Result>{
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

    @Override
    public String toString(){
        return String.format("%s: %d",name,scores);
    }

    @Override
    public int hashCode() {
        return 211 * 211 * scores + 211 * name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Result &&
                ((Result) o).getScores() == scores &&
                ((Result) o).getName().equals(name);
    }

    @Override
    public int compareTo(Result result) {
        if (scores == result.getScores()){
            return result.getName().compareTo(name);
        }
        return Integer.compare(result.getScores(), scores);
    }
}
