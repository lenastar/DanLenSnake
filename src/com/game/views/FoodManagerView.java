package com.game.views;

import com.game.classes.FoodManager;
import com.game.classes.IMap;
import com.game.classes.IView;
import com.game.classes.SimpleObjects;
import com.game.models.MapConsole;

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
    public void paint(IMap map) throws IndexOutOfBoundsException {
        draw((MapConsole) map);
    }

    private void draw(MapConsole mapConsole) throws IndexOutOfBoundsException {
        for (Point location: Model.getLocations())
        {
            mapConsole.setItem(location, SimpleObjects.Food);
        }
    }
}
