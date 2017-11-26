package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.GameSerializableException;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Level;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class Defaults {
    private static final String pathForGame = "src/com/game/resources/games/defaults";
    private static final String line_1 = "000000000000000000000000000000";
    private static final String line_2 = "000000#################0000000";


    public static void saveMediumLevel() throws LevelBadSizeException, IOException {
        String[] lines = Collections.nCopies(30, line_1).toArray(new String[30]);
        lines[5] = line_2;
        lines[6] = line_2;
        lines[15] = line_2;
        lines[16] = line_2;
        lines[25] = line_2;
        lines[26] = line_2;
        Level level = Level.getFromList(lines, "Medium");
        level.save(level.getFullPath());
    }

    public static void saveHardLevel() throws LevelBadSizeException, IOException {
        Level level = Level.getFromList(new String[]{
                "##############################",
                "#0000000000000000000000000000#",
                "#0##########################0#",
                "#0##########################0#",
                "#0##########################0#",
                "#0##########################0#",
                "#0000000000000000000000000000#",
                "#0000000000000000000000000000#",
                "#000#0####################000#",
                "#000#0#000000000000000000#000#",
                "#000#0#000000000000000000#000#",
                "#000#00000000000000000000#000#",
                "#000#00000000000000000000#000#",
                "#000#00000000000000000000#000#",
                "#000#00000000000000000000#000#",
                "0000#00000000000000000000#0000",
                "#000#00000000000000000000#000#",
                "#000#00000000000000000000#000#",
                "#000#0##################0#000#",
                "#000#0##################0#000#",
                "#000#00000000000000000000#000#",
                "#000######################000#",
                "#0000000000000000000000000000#",
                "#0000000000000000000000000000#",
                "#0##########################0#",
                "#0##########################0#",
                "#0##########################0#",
                "#0##########################0#",
                "#0000000000000000000000000000#",
                "##############################"
        }, "Hard");
        level.save(level.getFullPath());
    }

    public static Game getEasyGame() throws NoSuchMethodException {
        MapGUI map = new MapGUI(30,30,30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Right));
        return game;
    }

    public static Game getMediumGame() throws NoSuchMethodException, GameSerializableException, ClassNotFoundException {
        Level level = Level.get(Level.getFullPath("Medium"));
        MapGUI map = new MapGUI(level.getWidth(),level.getHeight(),30);
        Game game = new Game(map);
        game.addInstance(Model.createLevel(level));
        game.addInstance(Model.createSnake(new Point(3,5), 5, Direction.Right));
        return game;
    }

    public static Game getHardGame() throws NoSuchMethodException, GameSerializableException, ClassNotFoundException {
        Level level = Level.get(Level.getFullPath("Hard"));
        MapGUI map = new MapGUI(level.getWidth(),level.getHeight(),30);
        Game game = new Game(map);
        game.addInstance(Model.createLevel(level));
        game.addInstance(Model.createSnake(new Point(10,10), 3, Direction.Right));
        return game;
    }
}
