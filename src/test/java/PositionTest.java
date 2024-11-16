import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void testPositionInitialization(){

        Position position = new Position(5,10);

        Assertions.assertEquals(5, position.getX(), "X coordinate should be initialized to 5");
        Assertions.assertEquals(10, position.getY(), "Y should be initialized to 10");

    }

    @Test
    public void testSetXAndY(){

        Position position = new Position(0,0);
        position.setX(15);
        position.setY(20);

        Assertions.assertEquals(15, position.getX(), "X coordinate should be updated to 15.");
        Assertions.assertEquals(20,position.getY(), "Y coordinate should be updated to 20.");
    }





}
