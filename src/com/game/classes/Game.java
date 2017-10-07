package com.game.classes;

import com.game.models.Snake;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class Game<TMap extends IMap>{
    protected TMap map;
    protected ArrayList<IController> container;
    protected FoodManager foodManager;

    public abstract void init();

    public Game() {
        init();
    }

    public void doIteration() throws Exception {
        for (IController controller:container) {
            if (controller.getModel() instanceof Snake)
            {
                Snake snake = (Snake) controller.getModel();
                snake.move(snake.getDirection());
                if (foodManager.isFood(snake.getHead()))
                {
                    snake.grow(foodManager.getFood(snake.getHead()).getScores(), snake.getHead());
                    foodManager.removeFood(snake.getHead());
                }
            }
            controller.getView().paint(map);
        }
        map.paint();
    }

    public void processKey(int key)
    {
        for (IController controller:container) {
            if (controller.keyExists(key)) {
                controller.runAction(key);
            }
        }
    }

    public void processKey(KeyEvent event)
    {
        processKey(event.getKeyCode());
    }
}
