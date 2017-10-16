package com.game.views;

import com.game.classes.Context;
import com.game.models.FoodManager;
import com.game.classes.interfaces.IView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FoodManagerView implements IView<FoodManager, Context> {
    private final FoodManager Model;

    public FoodManagerView(FoodManager model) {
        Model = model;
    }

    @Override
    public FoodManager getModel() {
        return Model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException {
        for (Point point:Model.getLocations()){
            if (point.x < 0
                    || point.y < 0
                    || point.x >= context.map.getLevel().getWidth()
                    || point.y >= context.map.getLevel().getHeight())
                throw new IndexOutOfBoundsException();
            try {
                context.map.drawImagePoint(context.g,point, ImageIO.read(new File("src/com/game/resources/images/banana.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
