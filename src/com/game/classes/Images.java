package com.game.classes;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class Images {
    private static final Image Banana;
    private static final Image Background ;

    static {
        try {
            Banana = new BufferedImage(Image.class.getResource("src/com/game/resources/images/banana.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
