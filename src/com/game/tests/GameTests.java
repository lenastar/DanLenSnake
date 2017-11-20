import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class GameTests {
    private Game game;
    private MapGUI map;

    @BeforeEach
    public void init() throws LevelBadSizeException {
        map = new MapGUI(4, 4, 10);
        game = new Game(map);
    }

    @Test
    public void testAddInstance() throws NoSuchMethodException {
        game.addInstance(Model.createSnake(new Point(1, 2), 2));
        assertEquals(2, game.getContainerModels().size());
        assertEquals(2, map.getViews().size());
        assertEquals(1, game.getContainerControllers().size());
    }

    @Test
    public void testSpeedGrow(){
        int speed_1 = game.getSpeed();
        game.doIteration();
        int speed_2 = game.getSpeed();
        assertEquals(speed_2 - speed_1, 1);

    }

    @Test
    public void testSpeedLimit(){
        for (int i = 0; i < 200; i++)
            game.doIteration();
        assertEquals(game.getSpeed(), 350);
    }

    @Test
    public void testSpeedGrowManyTimes(){
        int speed_1 = game.getSpeed();
        for (int i = 0; i < 50; i++)
            game.doIteration();
        assertEquals(game.getSpeed() - speed_1, 50);
    }

}
