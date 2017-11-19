package com.game;

import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.enumerators.Direction;
import com.game.models.HighscoreTable;
import com.game.views.Display;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        MapGUI map = new MapGUI(20,20,30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Down));
//        Display app = new Display(map, game);
    }
}
