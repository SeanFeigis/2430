package adventure;
import java.util.ArrayList;
import java.util.HashMap;

public class Room{
    //Private member variables
    private long roomId;
    private String roomName;
    private boolean startRoom;
    private String longDescription;
    private String shortDescription;
    private ArrayList<Room> allRooms;
    private ArrayList<Item> items;
    private HashMap<String, Long> roomMapping;

    public Room() {
        roomId = -1;
        roomName = null;
        startRoom = false;
        longDescription = null;
        shortDescription = null;
        allRooms = new ArrayList<Room>();
        items = new ArrayList<Item>();
        roomMapping = new HashMap<String, Long>();
    }

    public Room(long id, String Name, String sDescription, String lDescription) {
        roomId = id;
        roomName = Name;
        startRoom = false;
        shortDescription = sDescription;
        longDescription = lDescription;
        allRooms = new ArrayList<Room>();    
        items = new ArrayList<Item>();
        roomMapping = new HashMap<String, Long>();
    }

    public ArrayList<Item> listItems(){
        return items;
    }

    public void addAllRooms(ArrayList<Room> allRoom) {
        allRooms = allRoom;
    }

    public ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    public String getName(){
        return roomName;  
    }

    public long getID() {
        return roomId;
    }

    public String getLongDescription(){
        return longDescription;
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public Room getConnectedRoom(String direction) {
        long id = 0;
        Room newRoom = null;

        
        if (roomMapping.containsKey(direction)) {
            id = roomMapping.get(direction);
            newRoom = new Room();
            newRoom.setID(id);
        }

        
        

        for (Room connectedRoom : allRooms) {
            if (id == connectedRoom.getID()) {
                newRoom = connectedRoom;
            }
        }
        return newRoom;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addStart(boolean start) {
        startRoom = start;
    }

    public void setConnectedRoom(String direction, long id) {
        roomMapping.put(direction, id);
    }

    public void setID (long id) {
        roomId = id;
    }

    public void setRoomName(String name) {
        roomName = name;
    }

    public void setShortDescription(String sDescription) {
        shortDescription = sDescription;
    }

    public void setLongDescription(String lDescription) {
        longDescription = lDescription;
    }

    public void setStartRoom(boolean start) {
        startRoom = start;
    }

    public String toString() {
        return (roomName + " " + shortDescription);
    }
}
