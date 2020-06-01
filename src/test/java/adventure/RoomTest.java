package adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

/*
Due to the way my getConnectedRoom is implemented, it will only return null unless
the array of all rooms is populated correctly. therefore I can only test that they 
return null correctly
*/


public class RoomTest{
    private Room testRoom;
    long id = 0;
@Before
public void setup(){
    testRoom = new Room();
    testRoom.setID(id);
    testRoom.setConnectedRoom("S", id);
}

@Test
public void testgetConnectedRoom(){
    System.out.println("Testing getConnectedRoom");
    String dir = null;
    testRoom = testRoom.getConnectedRoom(dir);
    assertTrue(testRoom == null);

}
@Test
public void testgetConnectedRoom1(){
    System.out.println("Testing getConnectedRoom");
    String dir = "W";
    testRoom = testRoom.getConnectedRoom(dir);
    assertTrue(testRoom == null);
}

@Test
public void testgetConnectedRoom2() {
    System.out.println("Testing getConnectedRoom");
    String dir = "blah";
    testRoom = testRoom.getConnectedRoom(dir);
    assertTrue(testRoom == null);

}
@Test
public void testgetConnectedRoom3(){
    System.out.println("Testing getConnectedRoom");
    String dir = "up";
    testRoom = testRoom.getConnectedRoom(dir);
    assertTrue(testRoom == null);
}
}