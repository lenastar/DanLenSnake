package com.game.views;

import com.game.classes.Context;
import com.game.classes.enumerators.Images;
import com.game.models.FoodManager;
import com.game.classes.interfaces.IView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FoodManagerView implements IView<FoodManager, Context> {
    private final FoodManager model;

    public FoodManagerView(FoodManager model) {
        this.model = model;
    }

    @Override
    public FoodManager getModel() {
        return model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException {
        for (Point point: model.getLocations()){
            //TODO: optimize
            try {
                context.map.drawImagePoint(context.g,point, ImageIO.read(new File("src/com/game/resources/images/banana.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
