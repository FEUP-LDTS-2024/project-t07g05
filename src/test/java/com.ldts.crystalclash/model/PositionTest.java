package com.ldts.crystalclash.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() {position = new Position(5, 10);}

    @Test
    void testGetX() {
        assertEquals(5,position.getX(), "getX() should return the correct x coordinate");}

    @Test
    void testGetY() {
        assertEquals(10, position.getY(), "getY() should return teh correct y coordinate");
    }

    @Test
    void testSetX(){
        position.setX(15);
        assertEquals(15, position.getX(), "setX() should update the x coordinate correctly");}

    @Test
    void testSetY(){
        position.setY(20);
        assertEquals(20, position.getY(), "setY() should update the y coordinate correctly");
    }

    @Test
    void testEquals(){
        Position anotherPosition = new Position(5, 10);
        Position differentPosition = new Position(10, 20);

        assertEquals(position, anotherPosition, "Positions should be equal");
        assertNotEquals(position, differentPosition, "Positions should not be equal");
    }

    @Test
    void testEqualsWithNull(){
        assertNotEquals(null, position, "Position should not be equal to null");
    }

    @Test
    void testEqualsWithDifferentClass(){
        String nonPositionObject = "Not a position";
        assertNotEquals(nonPositionObject, position, "Position should not be equal to an object of a different class");
    }

    @Test
    void testHashCode(){
        Position anotherPosition = new Position(5, 10);
        assertEquals(position.hashCode(), anotherPosition.hashCode(), "Equal positions should have the same hashCode");
    }
}
