package com.game.classes;

import com.game.classes.interfaces.IController;
import com.game.classes.interfaces.IMap;
import com.game.classes.interfaces.IRunnable;
import com.game.models.FoodManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game{
    private IMap map;
    private ArrayList<IController> containerControllers;
    private FoodManager foodManager;
    private ArrayList<IRunnable> containerRunnable;

    public Game(IMap map) {
        this.map = map;
        containerControllers = new ArrayList<>();
        foodManager = new FoodManager(10);
        containerRunnable = new ArrayList<>();
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
        map.paint();
        for (IRunnable runnable: containerRunnable){
            if (runnable.run(this)){
                System.out.println("Game Over");
            }
        }
    }

    public void processKey(int key)
    {
        for (IController controller: containerControllers) {
            if (controller.keyExists(key)) {
                controller.runAction(key);
            }
        }
    }

    public void processKey(KeyEvent event)
    {
        processKey(event.getKeyCode());
    }

    public void start()
    {
        Thread thread = new Thread(() -> {
            try {
                while(true) {
                    doIteration();
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public IMap getMap() {
        return map;
    }

    public FoodManager getFoodManager() {
        return foodManager;
    }
}
