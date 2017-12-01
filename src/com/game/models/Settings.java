package com.game.models;

import java.io.Serializable;

public class Settings implements Serializable{
    private boolean soundOn;
    private static final String path = "src/com/game/resources/data/settings.dat";


    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    private boolean fullScreen;

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

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
