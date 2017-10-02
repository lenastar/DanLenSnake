package com.game.views;

import com.game.classes.FoodManager;
import com.game.classes.IView;
import com.game.classes.SimpleObjects;
import com.game.models.Food;
import com.game.models.Map;

import java.awt.*;

public class FoodManagerView implements IView<FoodManager> {
    private final FoodManager Model;

    public FoodManagerView(FoodManager model) {
        Model = model;
    }

    @Override
    public FoodManager getModel() {
        return Model;
    }

    @Override
    public void draw(Map map) throws IndexOutOfBoundsException {
        for (Point location: Model.getLocations())
        {
            map.setItem(location, SimpleObjects.Food);
        }
    }
}
