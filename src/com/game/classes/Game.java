package com.game.classes;

import com.game.classes.Directions;
import com.game.classes.IController;
import com.game.controllers.SnakeController;
import com.game.classes.SimpleObjects;
import com.game.models.Map;
import com.game.models.Snake;
import com.game.views.SnakeView;

import java.awt.*;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Map _Map;
    private List<IController> Container;
    private Scanner _Scanner;

    public void init() throws NoSuchMethodException
    {
        _Map = new Map(10, 10);
        Snake snake = new Snake( new Point(5, 5), 5, Directions.Right);
        SnakeView snakeView = new SnakeView(snake);
        SnakeController snakeController = new SnakeController(snake, snakeView);
        Container = Arrays.asList(snakeController);
    }

    public void gameOver()
    {

    }

    public void loop() throws Exception {
        init();
        InputStreamReader reader = new InputStreamReader(System.in);
        while (true)
        {
            if (reader.ready()) {
                int readKey = reader.read();
                for (IController controller : Container) {
                    if (controller.keyExists(readKey)) {
                        controller.getAction(readKey).invoke(controller);
                    }
                }
            }
            Thread.sleep(50);
            _Map.fillMap();

            for (IController controller : Container)
            {
                if (controller.getModel() instanceof Snake)
                {
                    Snake snake = (Snake) controller.getModel();
                    snake.move(snake.getDirection());
                    char item = _Map.getItem(snake.getHead().x, snake.getHead().y);
                    if (item == SimpleObjects.HorizonWall
                            || item == SimpleObjects.VerticalWall
                            || snake.isBody(snake.getHead()))
                    {
                        gameOver();
                        return;
                    }
                }

                controller.getView().draw(_Map);
            }

            _Map.print();
            Thread.sleep(1000);
        }
    }
}
