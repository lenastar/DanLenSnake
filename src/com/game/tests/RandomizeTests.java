import com.game.classes.*;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IController;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Food;
import com.game.models.FoodManager;
import com.game.models.Snake;
import com.game.runnable.FoodManagerRunnable;
import com.game.views.FoodManagerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomizeTests {
    public void testRandomFoodIsNotCollidedWithSomebody() throws LevelBadSizeException {
        Point randomFoodLocation;
        MapGUI map = new MapGUI(5, 5, 20);
        Instance<FoodManager, FoodManagerView, IController, FoodManagerRunnable> instance = Model.createFoodManager(1);
        Game game = new Game(map, Collections.singletonList(instance));
        FoodManager foodManager = instance.getModel();
        game.doIteration();

        randomFoodLocation = foodManager.getLocations().stream().findFirst().get();
        foodManager.removeFood(randomFoodLocation);
        assertTrue(game
                .getContainerModels()
                .stream()
                .noneMatch(model -> model.isCollisionWith(randomFoodLocation)));
    }

    @Test
    public void voidSomeTests() throws LevelBadSizeException {
        for (int i = 0; i < 100; i++) {
            testRandomFoodIsNotCollidedWithSomebody();
        }
    }
}
