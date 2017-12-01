package com.game.models;

import com.game.classes.SerializationUtil;
import com.game.classes.exceptions.GameSerializableException;

import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.lang.Math.min;

public class HighscoreTable implements Serializable {
    private TreeSet<Result> allResults;
    private static final String path = "src/com/game/resources/data/highscore.dat";

    public HighscoreTable() {
        allResults = new TreeSet<>();
    }

    public TreeSet<Result> getAllResults() {
        return allResults;
    }

    public void save () {
        SerializationUtil.save(path,this);
    }

    public static HighscoreTable get() throws GameSerializableException {
        return (HighscoreTable)SerializationUtil.get(path);
    }


    public void addResult(Result result){
        allResults.add(result);
    }

    public void deleteResult(Result result){
        allResults.remove(result);
    }

    public Result getBest(){
        return allResults.first();
    }

    public List<Result> take(int n){
        return allResults.stream().limit(n).collect(Collectors.toList());
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
