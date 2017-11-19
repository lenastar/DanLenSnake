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
    private static final String pathForGame = "src/com/game/resources/data/games/defaults";

    private static void saveEasyLevel() throws LevelBadSizeException, IOException {
        String line = "000000000000000000000000000000";
        String[] lines = Collections.nCopies(30, line).toArray(new String[30]);
        Level level = Level.getFromList(lines, "Easy");
        level.save(level.getFullPath());
    }

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

    public static void saveEasyGame() throws LevelBadSizeException, IOException,
            GameSerializableException, ClassNotFoundException, NoSuchMethodException {
        saveEasyLevel();
        Level level = Level.get(Level.getFullPath("Easy"));
        MapGUI map = new MapGUI(level.getWidth(),level.getHeight(),30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Down));
        game.save(Paths.get(pathForGame, "Easy", ".dat").toString());
    }

    public static void saveMediumGame() throws GameSerializableException,
            ClassNotFoundException, NoSuchMethodException, IOException {
        Level level = Level.get(Level.getFullPath("Medium"));
        MapGUI map = new MapGUI(level.getWidth(),level.getHeight(),30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Down));
        game.save(Paths.get(pathForGame, "Medium", ".dat").toString());
    }

    public static void saveHardGame(){

    }

    public static Game getEasyGame() throws ClassNotFoundException,
            LevelBadSizeException, IOException, GameSerializableException {
        try{
            return Game.get(Paths.get(pathForGame, "Easy", ".dat").toString());
        } catch (GameSerializableException e) {
            saveEasyLevel();
            return Game.get(Paths.get(pathForGame, "Easy", ".dat").toString());
        }
    }
}
