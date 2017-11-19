package com.game.models;

import com.game.classes.interfaces.IModel;

import java.awt.*;
import java.util.*;

public class FoodManager implements IModel{
    private HashMap<Point, Food> foods;
    private int limit;
    private Food lastEatenFood;

    public FoodManager(int limit)
    {
        this.limit = limit;
        foods = new HashMap<>();
    }

    public boolean isCollisionWith(Point location)
    {
        return foods.containsKey(location);
    }

    public int getLimit() {
        return limit;
    }

    public void addFood(Food food)
    {
        if (foods.size() < limit) {
            foods.put(food.getLocation(), food);
        }
    }

    public void removeFood(Point location)
    {
        foods.remove(location);
    }

    public int count()
    {
        return foods.size();
    }

    public Food getFood(Point location)
    {
        return foods.get(location);
    }

    public Set<Point> getLocations()
    {
        return foods.keySet();
    }

    public void clear()
    {
        foods.clear();
    }

    public Food getLastEatenFood() {
        return lastEatenFood;
    }

    public boolean snakeIsAliveAfterCollision(Snake snake){
        if (isCollisionWith(snake.getHead())){
            snake.grow(getFood(snake.getHead()).getScores());
            lastEatenFood = getFood(snake.getHead());
            removeFood(snake.getHead());
        }
        return true;
    }
}
