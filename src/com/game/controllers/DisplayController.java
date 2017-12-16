package com.game.controllers;

import com.game.classes.Cheat;
import com.game.classes.Game;
import com.game.classes.exceptions.PathNotFoundException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DisplayController extends KeyAdapter{
    private final Game game;
    private final Cheat cheat;

    public DisplayController(Game game, Cheat cheat) {
        this.game = game;
        this.cheat = cheat;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_H){
            try {
                game.clearKeys();
                game.processKey(cheat.getKeycodes());
            } catch (PathNotFoundException e) {
                System.out.println("Path not found");
            }
        }else{
            game.processKey(keyEvent);
        }
    }
}
