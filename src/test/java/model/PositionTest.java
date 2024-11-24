package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    void testGettersAndSetters() {
        Position position = new Position(5,10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());

        position.setX(20);
        position.setY(30);
        assertEquals(20, position.getX());
        assertEquals(30, position.getY());
    }

    @Test
    void testEquals() {
        Position position1 = new Position(5,10);
        Position position2 = new Position(5,10);
        Position position3 = new Position(10,5);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
    }
}
