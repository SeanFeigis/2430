package adventure;
import java.util.ArrayList;

public class Adventure{
    /* you will need to add some private member variables */

    ArrayList<Room> allRooms;
    ArrayList<Item> allItems; 
    Room currentRoom;

    public Adventure (ArrayList<Room> roomArray, ArrayList<Item> itemArray ) {
        allRooms = roomArray;
        allItems = itemArray;
    }

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

   //public Adventure 

    public ArrayList<Room> listAllRooms(){
        System.out.printf("Listing all items:");
        return null;

    }

    public ArrayList<Item> listAllItems(){
        System.out.printf("Listing all items");
        return null;
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