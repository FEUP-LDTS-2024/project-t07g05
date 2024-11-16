import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void testInitialization(){

        Position position = new Position(5,10);

        Assertions.assertEquals(5, position.getX(), "X coordinate should be initialized to 5");
        Assertions.assertEquals(10, position.getY(), "Y should be initialized to 10");

    }







}
