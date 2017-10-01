package com.game.tests;

import com.game.models.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class FoodTests {
    @Test
    public void testSuccessEqual()
    {
        Food food1 = new Food(new Point(2, 3), 4);
        Food food2 = new Food(new Point(2, 3), 4);
        assertTrue(food1.equals(food2));
    }

    @Test
    public void testFailEqualWrongInstance()
    {
        Food food = new Food(new Point(1, 1), 4);
        assertFalse(food.equals(new Point(2, 3)));
        assertFalse(food.equals(1));
    }

    @Test
    public void testFailEqual()
    {
        Food food1 = new Food(new Point(2, 3), 4);
        Food food2 = new Food(new Point(2, 5), 1);
        assertFalse(food1.equals(food2));
    }

    @Test
    public void testHashCode()
    {
        Food food1 = new Food(new Point(1, 3), 5);
        Food food2 = new Food(new Point(0, 0), 0);
        Food food3 = new Food(new Point(0, 1), 3);
        assertEquals(food1.hashCode(), 211*211*1 + 211*3 + 5);
        assertEquals(food2.hashCode(), 0);
        assertEquals(food3.hashCode(), 211);

    }
}
