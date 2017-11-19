import com.game.models.Level;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTests {
    @Test
    public void testGetDefaultLevel() throws LevelBadSizeException {
        Level level = Level.getDefaultLevel(5, 4);
        HashSet<Point> expectedResult = new HashSet<>(Arrays.asList(
                new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(0, 3),
                new Point(4, 0), new Point(4, 1), new Point(4, 2),
                new Point(4, 3),
                new Point(1, 0), new Point(2, 0), new Point(3, 0),
                new Point(1, 3), new Point(2, 3), new Point(3, 3)
        ));
        assertTrue(expectedResult.containsAll(level.getWalls()));
        assertTrue(new HashSet<>(level.getWalls()).containsAll(expectedResult));
    }

    @Test
    public void testGetDefaultLevelFail() {
        assertThrows(LevelBadSizeException.class,
                () -> {
                    Level level = Level.getDefaultLevel(1, 3);
                });

    }

    @Test
    public void testGetFromList() throws LevelBadSizeException {
        Level level = Level.getFromList(new String[]{
                "000#0",
                "00#00",
                "#000#"
        });
        assertEquals(5, level.getWidth());
        assertEquals(3, level.getHeight());
        ArrayList<Point> expectedResult = new ArrayList<>(Arrays.asList(
                new Point(3, 0), new Point(2, 1),
                new Point(0, 2), new Point(4, 2)
        ));
        assertIterableEquals(expectedResult, level.getWalls());
    }

    @Test
    public void testGetFromListFail() {
        assertThrows(LevelBadSizeException.class,
                () -> {
                    Level level = Level.getFromList(new String[]{
                            "###",
                            "###"
                    });
                });
    }

    @Test
    public void testCollisionWith() throws LevelBadSizeException {
        Level level = Level.getDefaultLevel(3, 3);
        Point point1 = new Point(3, 2);
        Point point2 = new Point(2, 2);
        assertTrue(level.isCollisionWith(point2));
        assertFalse(level.isCollisionWith(point1));
    }

    @Test
    public void testCollisionWithSnake() throws LevelBadSizeException {
        Level level = Level.getDefaultLevel(6, 6);
        Snake snake = new Snake(new Point(2, 2), 3, Direction.Up);
        assertTrue(level.snakeIsAliveAfterCollision(snake));
        snake.move();
        assertTrue(level.snakeIsAliveAfterCollision(snake));
        snake.move();
        assertFalse(level.snakeIsAliveAfterCollision(snake));
    }
}
