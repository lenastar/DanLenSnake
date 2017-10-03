package com.game.tests;

import com.game.classes.FoodManager;
import com.game.models.Food;
import com.game.models.Map;
import com.game.views.FoodManagerView;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodManagerViewTests {

    @Test
    public void TestDrawFoodSucces() throws Exception{
        Map map = new Map(5, 6);
        FoodManager foodManager = new FoodManager(2);
        Food food = new Food(new Point(1,1), 5) ;
        foodManager.addFood(food);
        FoodManagerView foodView = new FoodManagerView(foodManager);
        foodView.draw(map);
        char[][] expectedResult = {
                {'+', '-', '-', '-', '-', '+'},
                {'|', '*', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', '|'},
                {'+', '-', '-', '-', '-', '+'}
        };
        assertArrayEquals(expectedResult, map.getCanvas());
    }

    @Test
    public void testDrawFoodManagerFail() throws IndexOutOfBoundsException {
        Map map = new Map(5, 6);
        FoodManager foodManager = new FoodManager(2);
        Food food = new Food(new Point(-1,1),5);
        foodManager.addFood(food);
        FoodManagerView foodManagerView = new FoodManagerView(foodManager);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            foodManagerView.draw(map);
        });
    }

}
