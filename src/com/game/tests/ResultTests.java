import com.game.models.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTests {
    @Test
    public void ResultEqualsTest(){
        Result result_1 = new Result(20, "Player");
        Result result_2 = new Result(20, "Player");
        Result result_3 = new Result(30, "Player2");
        assertEquals(result_1, result_2);
        assertNotEquals(result_1, result_3);
    }

    @Test
    public void ResultHashCodeEqualsTest(){
        Result result_1 = new Result(20, "Player");
        Result result_2 = new Result(20, "Player");
        Result result_3 = new Result(30, "Player2");
        assertEquals(result_1.hashCode(), result_2.hashCode());
        assertNotEquals(result_2.hashCode(), result_3.hashCode());
    }
}
