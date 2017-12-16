import com.game.models.HighscoreTable;
import com.game.models.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HighscoreTableTests {
    HighscoreTable highscoreTable;
    List<Result> results;

    @BeforeEach
    public void init(){
        results = Arrays.asList(
                new Result(50,"DanPlayer" ),
                new Result(30,"LenStarrPlayer" ),
                new Result(40,"Player2" ),
                new Result(400,"Player3" )
                );
        highscoreTable = new HighscoreTable();
    }

    public void addResults(){
        highscoreTable.addResult(results.get(0));
        highscoreTable.addResult(results.get(1));
        highscoreTable.addResult(results.get(2));
        highscoreTable.addResult(results.get(3));
    }

    @Test
    public void AddResultTest(){
        addResults();
        assertIterableEquals(
                Arrays.asList(results.get(3), results.get(0), results.get(2), results.get(1)),
                highscoreTable.getAllResults()
        );
    }

    @Test
    public void getBestTest(){
        addResults();
        assertEquals(results.get(3), highscoreTable.getBest());
    }

    @Test
    public void deleteByResult()
    {
        addResults();
        highscoreTable.deleteResult(new Result(40, "Player2"));
        assertIterableEquals(Arrays.asList(results.get(3), results.get(0), results.get(1)), highscoreTable.getAllResults());
    }

    @Test
    public void HighscoreTableEqualsTest(){
        addResults();
        HighscoreTable table = new HighscoreTable();
        for (Result result: results){
            table.addResult(result);
        }
        assertEquals(highscoreTable, table);
    }
}
