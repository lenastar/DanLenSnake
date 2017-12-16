import com.game.classes.Cheat;
import com.game.classes.Game;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.PathNotFoundException;
import com.game.models.Food;
import com.game.models.FoodManager;
import com.game.models.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class CheatTests {
    private Level level_1;
    private Level level_2;

    @BeforeEach
    public void setUp() throws LevelBadSizeException {
        level_1 = Level.getFromList(new String[]{
                "###########",
                "#0#0000000#",
                "#0#000#000#",
                "#0#####000#",
                "#0#000#000#",
                "#0#0#######",
                "#000000000#",
                "###########"
        });

        level_2 = Level.getFromList(new String[]{
                "000#000",
                "000#000",
                "000#000",
                "000#000",
                "000#000",
        });
    }

    @Test
    public void getPathSuccess_1() throws PathNotFoundException {
        Point[] result = new Point[12];
        Cheat.getPath(level_1, new Point(1, 1), new Point(5, 4)).toArray(result);
        Point[] expected = new Point[]{
            new Point(1, 1), new Point(1, 2), new Point(1, 3),
            new Point(1, 4), new Point(1, 5), new Point(1, 6),
            new Point(2, 6), new Point(3, 6), new Point(3, 5),
            new Point(3, 4), new Point(4, 4), new Point(5, 4),
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void getPathFail_1(){
        assertThrows(PathNotFoundException.class, () -> {
           Cheat.getPath(level_1, new Point(1, 1), new Point(5, 1));
        });
    }

    @Test
    public void getPathSuccess_2() throws PathNotFoundException {
        Point[] result = new Point[5];
        Cheat.getPath(level_2, new Point(1, 1), new Point(4, 1)).toArray(result);
        Point[] expected = new Point[]{
                new Point(1, 1), new Point(0, 1), new Point(6, 1),
                new Point(5, 1), new Point(4, 1),
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void getShortPathToFood() throws PathNotFoundException {
        FoodManager foodManager = new FoodManager(3);
        foodManager.addFood(new Food(new Point(0, 0), 3));
        foodManager.addFood(new Food(new Point(5, 3), 3));
        Point[] result = new Point[3];
        Cheat.getPath(level_2, new Point(0, 3), foodManager::isCollisionWith, point -> true).toArray(result);
        Point[] expected = new Point[] {
          new Point(0, 3), new Point(6, 3), new Point(5, 3)
        };
        assertArrayEquals(expected, result);
    }
}
