package com.game;

import com.game.classes.GameConsole;

public class Main {

    public static void main(String[] args) throws Exception {
        GameConsole game = new GameConsole();
        game.loop();
    }
}
