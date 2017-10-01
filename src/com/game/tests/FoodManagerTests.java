package com.game.tests;

import com.game.classes.FoodManager;
import com.game.models.Food;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FoodManagerTests {
    private List<IController> ExternalContainer;
    private List<Food> Foods;
    private FoodManager Manager;

    @BeforeAll
    public void init()
    {
        ExternalContainer = new List<IController>();
        Foods = Arrays.asList(
                new Food(new Point(1, 2), 3),
                new Food(new Point(2, 3), 1),
                new Food(new Point(3, 3), 2),
                new Food(new Point(5, 5), 1)
        );
        Manager = new FoodManager(3, ExternalContainer);
    }

    @Test
    public void testAddFood()
    {

    }

    @Test
    public void testAddFoodOverflow()
    {

    }

    @Test
    public void tetsRemoveFood()
    {

    }

    @Test
    public void testIsFoodSuccess()
    {

    }

    @Test
    public void testIsFoodFail()
    {

    }

}
