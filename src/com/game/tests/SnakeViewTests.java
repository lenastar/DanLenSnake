package com.game.tests;

import com.game.classes.Directions;
import com.game.models.MapConsole;
import com.game.models.Snake;
import com.game.views.SnakeView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class SnakeViewTests {
    @Test
    public void testDrawSnakeSuccess() throws Exception {
        MapConsole mapConsole = new MapConsole(5, 6);
        Snake snake = new Snake(new Point(3, 3), 3, Directions.Right);
        SnakeView snakeView = new SnakeView(snake);
        snakeView.paint(mapConsole);
        char[][] expectedResult = {
                {'+', '-', '-', '-', '-', '+'},
                {'|', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', '|'},
                {'|', 'x', 'x', 'O', ' ', '|'},
                {'+', '-', '-', '-', '-', '+'}
        };
        assertArrayEquals(expectedResult, mapConsole.getCanvas());
    }

    @Test
    public void testDrawSnakeFail() throws Exception {
        MapConsole mapConsole = new MapConsole(5, 6);
        Snake snake = new Snake(new Point(3, 3), 3, Directions.Up);
        SnakeView snakeView = new SnakeView(snake);
        assertThrows(Exception.class, () -> {
            snakeView.paint(mapConsole);
        });
    }
}
