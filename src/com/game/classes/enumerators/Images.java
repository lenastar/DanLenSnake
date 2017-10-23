package com.game.classes.enumerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public enum Images {
    Banana (new File("src/com/game/resources/images/banana.png"));

    private Image image;
    Images(File file){
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
        }
    }

    public Image getImage() {
        return image;
    }
}
