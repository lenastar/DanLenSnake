import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.models.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTests {

    Point head = new Point(5, 5);

    public void testInit(Direction direction)
    {
        Snake snake = new Snake(head, 3, direction);
        Point point = direction.opposite().getPoint();
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
        testInit(Direction.Right);
    }

    @Test
    public void testInitDirLeft()
    {
        testInit(Direction.Left);
    }

    @Test
    public void testInitDirUp()
    {
        testInit(Direction.Up);
    }

    @Test
    public void testInitDirDown()
    {
        testInit(Direction.Down);
    }

    @Test
    public void testGrow()
    {
        Snake snake = new Snake(head, 3);
        assertEquals(3, snake.getLength());
        snake.grow(2);
        assertEquals(5, snake.getLength());
    }

    public void testMove(Direction direction) throws Exception
    {
        Snake snake = new Snake(head, 4);
        ArrayList<Point> old = new ArrayList<>(snake.getSegments());
        snake.setDirection(direction);
        snake.move();
        Point point = direction.getPoint();
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
    public void testMoveRight() throws SnakeOppositeMoveException
    {
        assertThrows(SnakeOppositeMoveException.class, () -> { testMove(Direction.Right); });
    }

    @Test
    public void testMoveLeft() throws Exception
    {
        testMove(Direction.Left);
    }

    @Test
    public void testMoveUp() throws Exception
    {
        testMove(Direction.Up);
    }

    @Test
    public void testMoveDown() throws Exception
    {
        testMove(Direction.Down);
    }
}