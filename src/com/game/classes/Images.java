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
    private static final String Head = "src/com/game/resources/images/head.png";
    private static final String Body = "src/com/game/resources/images/body.png";
    private static final String Khvost = "src/com/game/resources/images/Khvost.png";

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

    public static Image getHead(){
        return getImages(Head);
    }

    public static Image getBody(){
        return getImages(Body);
    }

    public static Image getKhvost(){
        return getImages(Khvost);
    }
}
