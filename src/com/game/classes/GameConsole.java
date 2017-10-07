package com.game.classes;

import com.game.controllers.FoodManagerController;
import com.game.controllers.SnakeController;
import com.game.models.Food;
import com.game.models.MapConsole;
import com.game.models.Snake;
import com.game.views.FoodManagerView;
import com.game.views.SnakeView;
import org.ietf.jgss.GSSManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameConsole extends Game<MapConsole> {
    public GameConsole()
    {
        super();
    }

    @Override
    public void init()
    {
        map = new MapConsole(10, 10);
        Snake snake = new Snake( new Point(5, 5), 5, Directions.Right);
        SnakeView snakeView = new SnakeView(snake);
        SnakeController snakeController = null;
        try {
            snakeController = new SnakeController(snake, snakeView);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        foodManager = new FoodManager(1);
        foodManager.addFood(new Food(new Point(3, 7), 3 ));
        FoodManagerView foodManagerView = new FoodManagerView(foodManager);
        FoodManagerController foodManagerController = new FoodManagerController(foodManager, foodManagerView);
        container = new ArrayList<IController>();
        container.add(snakeController);
        container.add(foodManagerController);
    }

    public void gameOver()
    {
        System.out.println("Game is over");
    }

    public void loop() throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in);
        while (true)
        {
            if (reader.ready()) {
                int readKey = reader.read();
                this.processKey(readKey);
            }
            Thread.sleep(50);
            map.fillMap();
            this.doIteration();
            Thread.sleep(1000);
        }
    }
}