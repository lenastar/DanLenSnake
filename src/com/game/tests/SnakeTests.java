import com.game.classes.enumerators.Directions;
import com.game.models.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTests {

    Point head = new Point(5, 5);

    public Point getDiff(Directions direction)
    {
        Point point = new Point(0, 0);
        switch (direction){
            case Down:
                point = new Point(-1, 0);
                break;
            case Up:
                point = new Point(1, 0);
                break;
            case Left:
                point = new Point(0, 1);
                break;
            case Right:
                point = new Point(0, -1);
                break;
        }
        return point;
    }

    public void testInit(Directions direction)
    {
        Snake snake = new Snake(head, 3, direction);
        Point point = getDiff(direction);
        assertArrayEquals(
                new Point[]{
                        new Point(2*point.x + head.x, 2*point.y + head.y),
                        new Point(point.x + head.x, point.y + head.y),
                        new Point(head.x, head.y),
                },
                snake.getSegments().toArray()
        );
    }

    @Test
    public void testInitDirRight()
    {
        testInit(Directions.Right);
    }

    @Test
    public void testInitDirLeft()
    {
        testInit(Directions.Left);
    }

    @Test
    public void testInitDirUp()
    {
        testInit(Directions.Up);
    }

    @Test
    public void testInitDirDown()
    {
        testInit(Directions.Down);
    }

    @Test
    public void testGrow()
    {
        Snake snake = new Snake(head, 3);
        assertEquals(3, snake.getLength());
        snake.grow(2, new Point(0,0));
        assertEquals(5, snake.getLength());
    }

    public void testMove(Directions direction) throws Exception
    {
        Snake snake = new Snake(head, 4);
        ArrayList<Point> old = new ArrayList<>(snake.getSegments());
        snake.move(direction);
        Point point = new Point( -getDiff(direction).x, -getDiff(direction).y);
        assertArrayEquals(
                new Point[]
                        {
                                old.get(1),
                                old.get(2),
                                old.get(3),
                                new Point(old.get(3).x + point.x,
                                        old.get(3).y + point.y)
                        },
                snake.getSegments().toArray()
        );
    }

    @Test
    public void testMoveRight() throws Exception
    {
        assertThrows(Exception.class, () -> { testMove(Directions.Right); });
    }

    @Test
    public void testMoveLeft() throws Exception
    {
        testMove(Directions.Left);
    }

    @Test
    public void testMoveUp() throws Exception
    {
        testMove(Directions.Up);
    }

    @Test
    public void testMoveDown() throws Exception
    {
        testMove(Directions.Down);
    }
}