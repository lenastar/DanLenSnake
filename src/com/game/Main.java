package com.game;

import com.game.classes.Game;
import com.game.models.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.loop();
    }
}
