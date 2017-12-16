package com.game.models;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private boolean released = false;
    private Clip clip = null;
    private FloatControl volumeC = null;
    private boolean playing = false;

    public Sound(File file) {
//        try {
//            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
//            clip = AudioSystem.getClip();
//            clip.open(stream);
//            clip.addLineListener(new Listener());
//            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            released = true;
//        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
//            exc.printStackTrace();
//            released = false;
//        }
    }


    public void play(boolean breakOld) {
        if (released) {
            clip.setFramePosition(0);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            playing = true;

        }
    }

    public void play() {
        play(true);
    }

    public void stop() {
        if (playing) {
            clip.stop();
        }
    }

    public void setVolume(float x) {
        if (x<0) x = 0;
        if (x>1) x = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max-min)*x+min);
    }

    public float getVolume() {
        float v = volumeC.getValue();
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        return (v-min)/(max-min);
    }

    

    private class Listener implements LineListener {
        public void update(LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                playing = false;
                synchronized(clip) {
                    ev.notify();
                }
            }
        }
    }

}
