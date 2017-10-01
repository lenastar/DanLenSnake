package com.game.views;

import com.game.classes.IView;
import com.game.classes.SimpleObjects;
import com.game.models.Food;
import com.game.models.Map;

public class FoodView implements IView<Food>{

    private Food Model;

    public FoodView(Food model){
        Model = model;
    }
    @Override
    public Food getModel() {
        return Model;
    }

    @Override
    public void draw(Map map) throws Exception {
        if (Model.getLocation().x < 0 || Model.getLocation().y < 0 || Model.getLocation().x >= map.getWidth()
                || Model.getLocation().y >= map.getHeight()){
            throw new Exception("Food is outside of map");
        }
        map.setItem(Model.getLocation().x,Model.getLocation().y, SimpleObjects.Food);
    }
}
