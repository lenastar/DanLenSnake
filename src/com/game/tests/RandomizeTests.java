import com.game.classes.*;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Food;
import com.game.models.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomizeTests {
    public void testRandomFoodIsNotCollidedWithSomebody() throws LevelBadSizeException {
        Point randomFoodLocation;
        MapGUI map = new MapGUI(5, 5, 20);
        Game game = new Game(map);
        Food food = new Food(new Point(2, 1), 2);
        game.addFood(food);
        game.doIteration();

        randomFoodLocation = game
                .getFoodManager()
                .getLocations()
                .stream()
                .filter(point -> !point.equals(food.getLocation()))
                .findFirst()
                .get();

        assertFalse(game
                .getMap()
                .getLevel()
                .isCollision(randomFoodLocation));

        game.getFoodManager().removeFood(randomFoodLocation);
        assertTrue(game
                .getContainerModels()
                .stream()
                .allMatch(model -> !model.isCollisionWith(randomFoodLocation)));
    }

    @Test
    public void voidSomeTests() throws LevelBadSizeException {
        for (int i = 0; i < 100; i++) {
            testRandomFoodIsNotCollidedWithSomebody();
        }
    }
}
