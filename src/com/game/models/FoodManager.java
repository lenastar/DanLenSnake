package com.game.models;

import com.game.classes.interfaces.IModel;
import com.game.models.Food;

import java.awt.*;
import java.util.*;

public class FoodManager implements IModel{
    private HashMap<Point, Food> Foods;
    private int Limit;

    public FoodManager(int limit)
    {
        Limit = limit;
        Foods = new HashMap<Point, Food>();
    }

    public boolean isCollisionWith(Point location)
    {
        if (Foods.containsKey(location)) {
            return true;
        }
        return false;
    }

    public int getLimit() {
        return Limit;
    }

    public void addFood(Food food)
    {
        if (Foods.size() < Limit) {
            Foods.put(food.getLocation(), food);
        }
    }

    public void removeFood(Point location)
    {
        Food food = Foods.remove(location);
    }

    public int count()
    {
        return Foods.size();
    }

    public Food getFood(Point location)
    {
        return Foods.get(location);
    }

    public Set<Point> getLocations()
    {
        return Foods.keySet();
    }

    public void clear()
    {
        Foods.clear();
    }
}
