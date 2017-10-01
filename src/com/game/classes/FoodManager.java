package com.game.classes;

import com.game.models.Food;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class FoodManager {
    private Map<Point, Food> Foods;
    private int Count;
    private List<IController> ExternalContainer;

    public FoodManager(int count, List<IController> externalContainer)
    {
        Count = count;
        ExternalContainer = externalContainer;
    }

    public boolean isFood(Point location)
    {
        if (Foods.containsKey(location)) {
            removeFood(location);
            return true;
        }
        return false;
    }

    public int getCount() {
        return Count;
    }

    public void addFood(Food food)
    {
        if (Foods.size() < Count) {
            Foods.put(food.getLocation(), food);
            ExternalContainer.add(...);
        }
    }

    public void removeFood(Point location)
    {
        Food food = Foods.remove(location);
        for (IController controller: ExternalContainer) {
            if (controller.getModel().equal(food)){
                ExternalContainer.remove(controller);
            }
        }
    }
}
