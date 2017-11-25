package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.GameSerializableException;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Level;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;

public class Defaults {
    private static final String pathForGame = "src/com/game/resources/games/defaults";

    public static void saveMediumLevel() throws LevelBadSizeException, IOException {
        String line_1 = "000000000000000000000000000000";
        String line_2 = "000000#################0000000";
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

    public static void saveHardLevel()
    {

    }

    public static Game getEasyGame() throws NoSuchMethodException {
        MapGUI map = new MapGUI(30,30,30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Down));
        return game;
    }

    public static Game getMediumGame() throws NoSuchMethodException, GameSerializableException, ClassNotFoundException {
        Level level = Level.get(Level.getFullPath("Medium"));
        MapGUI map = new MapGUI(level.getWidth(),level.getHeight(),30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Down));
        return game;
    }
}
