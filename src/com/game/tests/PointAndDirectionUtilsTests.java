import com.game.classes.PointAndDirectionUtils;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.UnknownDirectionException;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.game.classes.PointAndDirectionUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class PointAndDirectionUtilsTests {
    @Test
    public void addPoints(){
        Point point_1 = new Point(3, 3);
        Point point_2 = new Point(5, 7);
        assertEquals(new Point(8, 10), PointAndDirectionUtils.add(point_1, point_2));
    }

    @Test
    public void moveCyclicallyTest(){
        Point point_1 = new Point(1, 1);
        Point point_2 = new Point(11, 11);
        assertEquals(point_1, moveCyclically(point_1, 10, 10));
        assertEquals(point_1, moveCyclically(point_2, 10, 10));
    }

    @Test
    public void getDirectionTest() throws UnknownDirectionException {
        Point point_11 = new Point(1, 1);
        Point point_10 = new Point(1, 0);
        Point point_12 = new Point(1, 2);
        Point point_01 = new Point(0, 1);
        Point point_21 = new Point(2, 1);
        int width = 10;
        int height = 10;
        assertEquals(Direction.Down, getDirection(point_11, point_12, width, height));
        assertEquals(Direction.Up, getDirection(point_11, point_10, width, height));
        assertEquals(Direction.Right, getDirection(point_11, point_21, width, height));
        assertEquals(Direction.Left, getDirection(point_11, point_01, width, height));
    }

    @Test
    public void getDirectionCyclicallyTest() throws UnknownDirectionException {
        Point point_00 = new Point(0, 0);
        Point point_09 = new Point(0, 9);
        Point point_90 = new Point(9, 0);
        int width = 10;
        int height = 10;
        assertEquals(Direction.Up, getDirection(point_00, point_09, width, height));
        assertEquals(Direction.Left, getDirection(point_00, point_90, width, height));
    }

    @Test
    public void getDirectionFail(){
        Point point_00 = new Point(0, 0);
        Point point_11 = new Point(1, 1);
        assertThrows(UnknownDirectionException.class, () -> {
           getDirection(point_00, point_11, 10, 10);
        });
    }
}
