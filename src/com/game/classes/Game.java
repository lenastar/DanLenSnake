package com.game.classes;

import com.game.classes.interfaces.IController;
import com.game.classes.interfaces.IMap;
import com.game.classes.interfaces.IRunnable;
import com.game.classes.interfaces.IView;
import com.game.models.Food;
import com.game.models.FoodManager;
import com.game.views.FoodManagerView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Game{
    private final int MAX_SPEED = 350;

    private IMap map;
    private ArrayList<IController> containerControllers;
    private FoodManager foodManager;
    private ArrayList<IRunnable> containerRunnable;
    private Thread mainThread;
    private int speed;
    private Random random = new Random();
    private boolean endIteration = false;

    public Game(IMap map) {
        this.map = map;

        speed = 100;
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

    public void doIteration() throws Exception {
        synchronized ((Object)endIteration) {
            map.paint();
            addFoodRandomly();
            speed = Integer.min(MAX_SPEED, speed + 1);
            for (IRunnable runnable : containerRunnable) {
                if (!runnable.run(this)) {
                    gameOver();
                }
            }
            endIteration = true;
        }
    }

    public void processKey(int key)
    {
        synchronized ((Object)endIteration) {
            for (IController controller : containerControllers) {
                if (controller.keyExists(key)) {
                    controller.runAction(key);
                }
            }
            endIteration = false;
        }
        while (!endIteration && isRunning()){
            Thread.yield();
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

    public void addFoodRandomly(){
        Point point;
        boolean flag;
        do {
            int x = random.nextInt(map.getLevel().getWidth());
            int y = random.nextInt(map.getLevel().getHeight());
            point = new Point(x, y);
            final Point _point = new Point(point);
            flag = containerControllers
                    .stream()
                    .anyMatch(controller -> controller
                            .getModel()
                            .isCollisionWith(_point));
        }while (map.getLevel().isCollision(point)
                || flag
                || foodManager.isCollisionWith(point));
        addFood(new Food(point, 1));
    }

    public boolean isRunning(){
        return mainThread.isAlive();
    }
}
