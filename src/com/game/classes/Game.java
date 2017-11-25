package com.game.classes;

import com.game.classes.exceptions.GameSerializableException;
import com.game.classes.interfaces.*;
import com.game.models.*;
import com.game.runnable.FoodManagerRunnable;
import com.game.views.FoodManagerView;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;

public class Game{
    private final int MAX_SPEED = 350;

    private IMap map;
    private ArrayList<IController> containerControllers;
    private ArrayList<IRunnable> containerRunnable;
    private ArrayList<IModel> containerModels;
    private Thread mainThread;
    private int speed = 200;
    private PriorityQueue<Integer> pressedKeys;
    private int scores = 0;
    private String playerName = "Player";
    public boolean isGameOver = false;
    private HighscoreTable highscoreTable;

    public Game(IMap map, List<Instance> instances) {
        this.map = map;
        pressedKeys = new PriorityQueue<>();
        containerModels = new ArrayList<>();
        containerControllers = new ArrayList<>();
        containerRunnable = new ArrayList<>();
        addHighscoreTable();
        for (Instance instance: instances){
            addInstance(instance);
        }
        mainThread = new Thread(() -> {
            try {
                while(!isGameOver) {
                    doIteration();
                    Thread.sleep(500 - speed);
                }
                gameOver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Game(IMap map) {
        this(map, Collections.singletonList(Model.createFoodManager(2)));
    }



    private void addHighscoreTable(){
        try{
            highscoreTable = HighscoreTable.get(HighscoreTable.path);
        } catch (GameSerializableException e){
          highscoreTable = new HighscoreTable();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public HighscoreTable getHighscoreTable() throws ClassNotFoundException{
        return highscoreTable;
    }

    public void addScores(int value){
        scores += value;
    }

    public void addModel(IModel model) {
        containerModels.add(model);
    }

    public void addController(IController controller) {
        containerControllers.add(controller);
    }

    public void addRunnable(IRunnable runnable){
        containerRunnable.add(runnable);
    }

    public void addInstance(Instance instance){
        addModel(instance.getModel());
        if (instance.hasController()){
            addController(instance.getController());
        }
        if (instance.hasRunnable()) {
            addRunnable(instance.getRunnable());
        }
        if (instance.hasView()) {
            map.addView(instance.getView());
        }
    }

    public synchronized void doIteration(){
        try{
            map.paint();
            speed = Integer.min(MAX_SPEED, speed + 1);
            if (pressedKeys.size() > 0){
                processKey(pressedKeys.poll());
            }
                for (IRunnable runnable : containerRunnable) {
                    if (!runnable.run(this)) {
                        isGameOver = true;
                    }
                }
            }
        catch (Exception e){
            isGameOver = true;
        }
    }

    private void processKey(int key)
    {
        for (IController controller : containerControllers) {
            if (controller.keyExists(key)) {
                controller.runAction(key);
            }
        }
    }

    public void processKey(KeyEvent event)
    {
        handleKey(event.getKeyCode());
    }

    private synchronized void handleKey(int key){
        if (pressedKeys.size() == 0 || pressedKeys.peek() != key) {
            pressedKeys.add(key);
        }
    }

    public void start()
    {
        mainThread.start();
    }

    public void stop(){
        mainThread.stop();
    }

    public void gameOver() throws IOException {
        System.out.println("Game is over");
        containerModels.clear();
        containerControllers.clear();
        containerRunnable.clear();
        map.getViews().clear();
        isGameOver = true;
        highscoreTable.addResult(new Result(scores, playerName));
        highscoreTable.save(HighscoreTable.path);
        stop();
    }

    public IMap getMap() {
        return map;
    }

    public boolean isRunning(){
        return mainThread.isAlive();
    }

    public ArrayList<IController> getContainerControllers() {
        return containerControllers;
    }

    public ArrayList<IModel> getContainerModels() {
        return containerModels;
    }

    public int getScores() {
        return scores;
    }

    public int getSpeed() {
        return speed;
    }
}
