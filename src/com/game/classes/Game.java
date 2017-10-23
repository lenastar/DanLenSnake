package com.game.classes;

import com.game.classes.interfaces.*;
import com.game.models.Food;
import com.game.models.FoodManager;
import com.game.views.FoodManagerView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.OptionalInt;
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
    private Random random = new Random();
    private boolean endIteration = false;
    private OptionalInt pressedKey;

    public Game(IMap map) {
        this.map = map;

        speed = 100;
        containerModels = new ArrayList<>();
        containerControllers = new ArrayList<>();
        foodManager = new FoodManager(2);
        containerRunnable = new ArrayList<>();
        map.addView(new FoodManagerView(foodManager));
        mainThread = new Thread(() -> {
            try {
                while(true) {
                    doIteration();
                    Thread.sleep(500 - speed);
                }
            } catch (Exception e) {
            }
        });
    }

    public void addFood(Food food){
        foodManager.addFood(food);
    }

    public void addView(IView view){
        map.addView(view);
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
        map.addView(instance.getView());
    }

    public synchronized void doIteration() throws Exception {
            map.paint();
            Randomize.addFoodRandomly(this);
            speed = Integer.min(MAX_SPEED, speed + 1);
            for (IRunnable runnable : containerRunnable) {
                if (!runnable.run(this)) {
                    gameOver();
                }
            }
            pressedKey.ifPresent(key -> {
                for (IController controller : containerControllers) {
                    if (controller.keyExists(key)) {
                        controller.runAction(key);
                    }
                }
            });
            pressedKey = OptionalInt.empty();
    }

    public synchronized void processKey(int key)
    {
        if (!pressedKey.isPresent()){
            pressedKey = OptionalInt.of(key);
        }
    }

    public void processKey(KeyEvent event)
    {
        processKey(event.getKeyCode());
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
