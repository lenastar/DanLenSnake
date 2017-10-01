package com.game.tests;

import com.game.classes.Directions;
import com.game.models.Map;
import com.game.models.Snake;
import com.game.views.SnakeView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class SnakeViewTests {
    @Test
    public void testDrawSnakeSuccess() throws Exception {
        Map map = new Map(5, 6);
        Snake snake = new Snake(new Point(3, 3), 3, Directions.Right);
        SnakeView snakeView = new SnakeView(snake);
        snakeView.draw(map);
        char[][] expectedResult = {
                {'+', '-', '-', '-', '-', '+'},
                {'|', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', '|'},
                {'|', 'x', 'x', 'O', ' ', '|'},
                {'+', '-', '-', '-', '-', '+'}
        };
        assertArrayEquals(expectedResult, map.getCanvas());
    }

    @Test
    public void testDrawSnakeFail() throws Exception {
        Map map = new Map(5, 6);
        Snake snake = new Snake(new Point(3, 3), 3, Directions.Up);
        SnakeView snakeView = new SnakeView(snake);
        assertThrows(Exception.class, () -> {snakeView.draw(map);});
    }
}
