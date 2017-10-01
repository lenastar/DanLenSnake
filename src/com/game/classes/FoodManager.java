package com.game.classes;

import com.game.controllers.FoodController;
import com.game.models.Food;
import com.game.views.FoodView;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FoodManager {
    private HashMap<Point, Food> Foods;
    private int Limit;
    private List<IController> ExternalContainer;

    public FoodManager(int count, List<IController> externalContainer)
    {
        Limit = count;
        ExternalContainer = externalContainer;
        Foods = new HashMap<Point, Food>();
    }

    public boolean isFood(Point location)
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
            ExternalContainer.add(new FoodController(food, new FoodView(food)));
        }
    }

    public void removeFood(Point location)
    {
        Food food = Foods.remove(location);
        ExternalContainer.removeIf(controller -> controller.getModel().equals(food));
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
        ExternalContainer.removeIf(controller -> controller.getModel() instanceof Food);
        Foods.clear();
    }
}
