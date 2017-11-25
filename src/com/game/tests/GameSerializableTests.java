import com.game.classes.exceptions.GameSerializableException;
import com.game.models.HighscoreTable;
import com.game.models.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameSerializableTests {
    @Test
    public void SerializeTest() throws IOException, GameSerializableException, ClassNotFoundException {
        HighscoreTable highscoreTable = new HighscoreTable();
        highscoreTable.addResult(new Result(123, "123"));
        highscoreTable.addResult(new Result(1234, "123"));
        highscoreTable.addResult(new Result(12345, "12345"));
        highscoreTable.addResult(new Result(123456, "123456"));
        highscoreTable.addResult(new Result(1234456, "1234456"));
        highscoreTable.save("tmp.dat");
        assertEquals(highscoreTable, HighscoreTable.get("tmp.dat"));
    }
}
