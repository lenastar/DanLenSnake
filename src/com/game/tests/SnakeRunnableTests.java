import com.game.classes.Game;
import com.game.classes.Instance;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IController;
import com.game.controllers.SnakeController;
import com.game.models.Food;
import com.game.models.FoodManager;
import com.game.models.Snake;
import com.game.runnable.FoodManagerRunnable;
import com.game.runnable.SnakeRunnable;
import com.game.views.FoodManagerView;
import com.game.views.SnakeView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class SnakeRunnableTests {
    private Game game;
    private MapGUI map;
    private FoodManager foodManager;

    @BeforeEach
    public void init() throws LevelBadSizeException {
        map = new MapGUI(10, 10, 6);
        Instance<FoodManager, FoodManagerView, IController, FoodManagerRunnable> instance = Model.createFoodManager(1);
        game = new Game(map, Collections.singletonList(instance));
        foodManager = instance.getModel();
        game.addInstance(Model.createLevel(10, 10));
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
    public void testSnakeDeadAfterCollisionWithItself() throws NoSuchMethodException, SnakeOppositeMoveException {
        Instance<Snake, SnakeView, SnakeController, SnakeRunnable> instance = Model.createSnake(new Point(3, 3), 5, Direction.Down);
        game.addInstance(instance);
        Snake snake = instance.getModel();

        snake.setDirection(Direction.Left);
        game.doIteration();
        assertFalse(game.isGameOver);

        snake.setDirection(Direction.Left);
        game.doIteration();
        assertFalse(game.isGameOver);

        snake.setDirection(Direction.Left);
        game.doIteration();
        assertTrue(game.isGameOver);
    }

    @Test
    public void testSnakeIsAliveAfterCollisionWithFood() throws NoSuchMethodException {
        Food food = new Food(new Point(3, 4), 2);
        Instance<Snake, SnakeView, SnakeController, SnakeRunnable> instance = Model.createSnake(new Point(3, 3), 5, Direction.Down);
        Snake snake = instance.getModel();
        game.addInstance(instance);;

        foodManager.addFood(food);
        game.doIteration();
        assertFalse(foodManager.isCollisionWith(food.getLocation()));
        assertEquals(7, snake.getLength());
        assertFalse(game.isGameOver);
    }
}
