package com.game.tests;

import com.game.models.Food;
import com.game.models.Map;
import com.game.models.Snake;
import com.game.views.FoodView;
import com.game.views.SnakeView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class FoodViewTests {
    @Test
    public void testDrawFoodSuccess() throws Exception {
        Map map = new Map(4, 3);
        Food food = new Food(new Point(2, 1), 4);
        FoodView foodView = new FoodView(food);
        foodView.draw(map);
        char[][] expectedResult = {
                {'+', '-', '+'},
                {'|', ' ', '|'},
                {'|', '*', '|'},
                {'+', '-', '+'},
        };
        assertArrayEquals(expectedResult, map.getCanvas());
    }

    @Test
    public void testDrawFoodFail() throws Exception {
        Map map = new Map(4, 3);
        Food food = new Food(new Point(2, 3), 4);
        FoodView foodView = new FoodView(food);
        assertThrows(Exception.class, () -> {foodView.draw(map);});
    }
}
