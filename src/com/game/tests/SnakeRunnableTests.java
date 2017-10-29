import com.game.classes.Game;
import com.game.classes.Instance;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.models.Food;
import com.game.models.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;


public class SnakeRunnableTests {
    private Game game;
    private MapGUI map;

    @BeforeEach
    public void init() throws LevelBadSizeException {
        map = new MapGUI(10, 10, 6);
        game = new Game(map);
    }

    @Test
    public void testSnakeDeadAfterCollisionWithWall() throws NoSuchMethodException {
        game.addInstance(Model.createSnake(new Point(3, 3), 2, Direction.Down));
        for (int i = 3; i < 8; i++) {
            game.doIteration();
            assertFalse(game.isGameOver);
        }
        game.doIteration();
        assertTrue(game.isGameOver);
    }

    @Test
    public void testSnakeDeadAfterCollisionWithItself() throws NoSuchMethodException {
        game.addInstance(Model.createSnake(new Point(3, 3), 5, Direction.Down));
        Button button = new Button("click");
        KeyEvent eventLeft = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_LEFT, 'a');
        KeyEvent eventUp = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_UP, 'a');
        KeyEvent eventRight = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_RIGHT, 'a');
        game.processKey(eventLeft);
        game.doIteration();
        assertFalse(game.isGameOver);

        game.processKey(eventUp);
        game.doIteration();
        assertFalse(game.isGameOver);

        game.processKey(eventRight);
        game.doIteration();
        assertTrue(game.isGameOver);
    }

    @Test
    public void testSnakeIsAliveAfterCollisionWithFood() throws NoSuchMethodException {
        Food food = new Food(new Point(3, 4), 2);
        Instance instance = Model.createSnake(new Point(3, 3), 5, Direction.Down);
        Snake snake = (Snake) instance.getModel();
        game.addInstance(instance);
        game.addFood(food);
        game.doIteration();
        assertFalse(game.getFoodManager().isCollisionWith(food.getLocation()));
        assertEquals(7, snake.getLength());
        assertFalse(game.isGameOver);
    }
}
