import com.game.classes.enumerators.Direction;
import com.game.controllers.SnakeController;
import com.game.models.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeControllerTests {
    private Button button;
    private Snake snake;
    private SnakeController controller;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        button = new Button("Click");
        snake = new Snake(new Point(3, 3), 2, Direction.Up);
        controller = new SnakeController(snake);
    }

    //TODO: make common test with parameters (KeyEvent.KeyCode: int, Direction snake: Direction)
    //TODO: and remake those four tests with common one

    @Test
    public void testClickLeftButton(){
        KeyEvent event = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_LEFT, 'a');
        assertTrue(controller.keyExists(KeyEvent.VK_LEFT));
        controller.runAction(KeyEvent.VK_LEFT);
        assertEquals(Direction.Left, snake.getDirection());
    }

    @Test
    public void testClickRightButton(){
        KeyEvent event = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_RIGHT, 'a');
        assertTrue(controller.keyExists(KeyEvent.VK_RIGHT));
        controller.runAction(KeyEvent.VK_RIGHT);
        assertEquals(Direction.Right, snake.getDirection());
    }

    @Test
    public void testClickUpButton(){
        KeyEvent event = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_UP, 'a');
        assertTrue(controller.keyExists(KeyEvent.VK_UP));
        controller.runAction(KeyEvent.VK_UP);
        assertEquals(Direction.Up, snake.getDirection());
    }

    @Test
    public void testClickDownButton(){
        KeyEvent event = new KeyEvent(button, 1, 20, 1, KeyEvent.VK_DOWN, 'a');
        assertTrue(controller.keyExists(KeyEvent.VK_DOWN));
        controller.runAction(KeyEvent.VK_DOWN);
        assertEquals(Direction.Up, snake.getDirection());
    }
}
