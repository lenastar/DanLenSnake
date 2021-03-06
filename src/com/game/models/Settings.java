package com.game.models;

import java.io.Serializable;

public class Settings implements Serializable{
    private static boolean fullScreen;
    private Sound sound = new Sound(new java.io.File("src/com/game/resources/sounds/Sunshine.wav"));
    private static final String path = "src/com/game/resources/data/settings.dat";


    public void setSoundOn() {
        this.sound.play();
    }

    public void setSoundOff(){this.sound.stop();}

    private Resolution resolution;

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public class Resolution implements Serializable{
        private final int width;
        private final int height;

        public Resolution(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
