package com.game.models;

import com.game.classes.GameSerializable;


import java.util.ArrayList;

public class HighscoreTable extends GameSerializable<HighscoreTable>{
    private ArrayList<Result> allResults;
    public static final String path = "src/com/game/resources/data/highscore.dat";

    public HighscoreTable() {
        allResults = new ArrayList<>();
    }

    public ArrayList<Result> getAllResults() {
        return allResults;
    }

    public void addResult(Result result){
        int last_index = 0;
        for (int index = 0; index < allResults.size() && allResults.get(index).getScores() > result.getScores(); index++ ){
            last_index = index + 1;
        }
        allResults.add(last_index, result);
    }

    public void deleteResult(int index){
        allResults.remove(index);
    }

    public void deleteResult(Result result){
        allResults.remove(result);
    }

    public Result getBest(){
        return allResults.get(0);
    }

    @Override
    public String toString(){
        return allResults.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof HighscoreTable && allResults.equals(((HighscoreTable) o).getAllResults());
    }

    @Override
    public int hashCode() {
        return allResults.hashCode();
    }
}
