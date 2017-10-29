package com.game.classes;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class Images {
    private static final String Banana = "src/com/game/resources/images/banana.png";
    private static final String Background = "src/com/game/resources/images/images.jpg";

    private static Image getImages(String pathname){
        try {
            return ImageIO.read(new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getBanana(){
        return getImages(Banana);
    }

    public static Image getBackground(){
        return getImages(Background);
    }
}
