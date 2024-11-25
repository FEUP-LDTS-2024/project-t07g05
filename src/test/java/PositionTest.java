import com.ldts.crystalclash.model.Position;
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


    @Test
    public void testEqualsDifferentObjectSameValues() {

        Position position1 = new Position(5, 10);
        Position position2 = new Position(5, 10);
        Assertions.assertEquals(position1, position2, "Positions with the same coordinates should be equal.");
    }


    @Test
    public void testEqualsDifferentCoordinates() {

        Position position1 = new Position(5, 10);
        Position position2 = new Position(7, 10);
        Assertions.assertNotEquals(position1, position2, "Positions with different coordinates should not be equal.");
    }


    @Test
    public void testEqualsNull() {

        Position position = new Position(5, 10);
        Assertions.assertNotEquals(position, null, "model.Position should not be equal to null.");
    }


    @Test
    public void testEqualsDifferentClass() {

        Position position = new Position(5, 10);
        String otherObject = "Not a model.Position";
        Assertions.assertNotEquals(position, otherObject, "model.Position should not be equal to an object of a different class.");
    }

}
