package com.game.classes;

import com.game.classes.interfaces.*;
import com.game.models.Food;
import com.game.models.FoodManager;
import com.game.views.FoodManagerView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.Random;

public class Game{
    private final int MAX_SPEED = 350;

    private IMap map;
    private ArrayList<IController> containerControllers;
    private FoodManager foodManager;
    private ArrayList<IRunnable> containerRunnable;
    private ArrayList<IModel> containerModels;
    private Thread mainThread;
    private int speed;
    private PriorityQueue<Integer> pressedKeys;

    public Game(IMap map) {
        this.map = map;
        pressedKeys = new PriorityQueue<>();
        speed = 200;
        containerModels = new ArrayList<>();
        containerControllers = new ArrayList<>();
        foodManager = new FoodManager(2);
        addModel(foodManager);
        containerRunnable = new ArrayList<>();
        map.addView(new FoodManagerView(foodManager));
        mainThread = new Thread(() -> {
            try {
                while(true) {
                    doIteration();
                    Thread.sleep(500 - speed);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addFood(Food food){
        foodManager.addFood(food);
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
        addController(instance.getController());
        addRunnable(instance.getRunnable());
        addModel(instance.getModel());
        map.addView(instance.getView());
    }

    public void doIteration() throws Exception {
        map.paint();
        Randomize.addFoodRandomly(this);
        speed = Integer.min(MAX_SPEED, speed + 1);
        if (pressedKeys.size() > 0){
            processKey(pressedKeys.poll());
        }
        for (IRunnable runnable : containerRunnable) {
            if (!runnable.run(this)) {
                gameOver();
            }
        }
    }

    public void processKey(int key)
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

    public void handleKey(int key){
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

    public void gameOver(){
        System.out.println("Game is over");
        stop();
    }

    public IMap getMap() {
        return map;
    }

    public FoodManager getFoodManager() {
        return foodManager;
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
}
