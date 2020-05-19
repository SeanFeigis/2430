package adventure;
import java.util.ArrayList;

public class Adventure{
    /* you will need to add some private member variables */

    private ArrayList<Room> allRooms;
    private ArrayList<Item> allItems; 
    private Room currentRoom;

    public Adventure(ArrayList<Room> roomArray, ArrayList<Item> itemArray ) {
        allRooms = roomArray;
        allItems = itemArray;
    }


   //public Adventure 
    public ArrayList<Room> listAllRooms(){
        return allRooms;

    }

    public ArrayList<Item> listAllItems(){
        return allItems;
    }

    public String getCurrentRoomDescription(){
        return currentRoom.getShortDescription();
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /* you may wish to add additional methods*/
}
