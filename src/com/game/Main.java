package com.game;

import com.game.classes.Defaults;
import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.enumerators.Direction;
import com.game.models.HighscoreTable;
import com.game.views.Display;
import com.game.views.MainMenu;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = Defaults.getEasyGame();
        MainMenu app = new MainMenu(game.getMap(), game);

    }
}
