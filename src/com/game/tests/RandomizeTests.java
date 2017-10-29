import com.game.classes.*;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.SnakeOppositeMoveException;
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
        game.addModel(new Snake(new Point(3, 3), 2, Direction.Left));
        Food food = new Food(new Point(2, 1), 2);
        game.addFood(food);
        Randomize.addFoodRandomly(game);
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
        for (int i = 0; i < new Random().nextInt(6); i++) {
            testRandomFoodIsNotCollidedWithSomebody();
        }
    }
}
