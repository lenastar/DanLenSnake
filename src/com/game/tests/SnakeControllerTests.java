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

    public void testClick(int keyEvent, Direction direction){
        KeyEvent event = new KeyEvent(button, 1, 20, 1, keyEvent, 'a');
        assertTrue(controller.keyExists(keyEvent));
        controller.runAction(keyEvent);
        assertEquals(direction, snake.getDirection());
    }

    @Test
    public void testClickLeftButton(){
        testClick(KeyEvent.VK_LEFT, Direction.Left);
    }

    @Test
    public void testClickRightButton(){
        testClick(KeyEvent.VK_RIGHT, Direction.Right);
    }

    @Test
    public void testClickUpButton(){
        testClick(KeyEvent.VK_UP, Direction.Up);
    }

    @Test
    public void testClickDownButton(){
        testClick(KeyEvent.VK_DOWN, Direction.Up);
    }
}
