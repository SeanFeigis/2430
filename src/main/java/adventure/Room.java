package adventure;
import java.util.ArrayList;
import java.util.HashMap;

public class Room implements java.io.Serializable{
    //Private member variables
    private long roomId;
    private String roomName;
    private boolean startRoom;
    private String longDescription;
    private String shortDescription;
    private ArrayList<Room> allRooms;
    private ArrayList<Item> items;
    private HashMap<String, Long> roomMapping;


    /**
     *Default Constructor
     */
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

     /**
     *Constructor
     *@param id id of room
     *@param name name of room
     *@param sDescription short description of room
     *@param lDescription long description of room
     */
    public Room(long id, String name, String sDescription, String lDescription) {
        roomId = id;
        roomName = name;
        startRoom = false;
        shortDescription = sDescription;
        longDescription = lDescription;
        allRooms = new ArrayList<Room>();    
        items = new ArrayList<Item>();
        roomMapping = new HashMap<String, Long>();
    }

    /**
     *Getter for item list
     *@return itemlist
     */
    public ArrayList<Item> listItems(){
        return items;
    }

     /**
     *Adds the list of items sin the room
     *@param item arrayList of items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     *Setter for room list
     *@param allRoom array of rooms
     */
    public void setAllRooms(ArrayList<Room> allRoom) {
        allRooms = allRoom;
    }

    /**
     *Getter for room list
     *@return allRooms
     */
    public ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    /**
     *Getter for room name
     *@return roomName
     */
    public String getName(){
        return roomName;  
    }

    /**
     *Getter for item id
     *@return roomId
     */
    public long getID() {
        return roomId;
    }

    /**
     *Getter for long description
     *@return longDescription
     */
    public String getLongDescription(){
        return longDescription;
    }

    /**
     *Getter for short description
     *@return shortDescription
     */
    public String getShortDescription(){
        return shortDescription;
    }

    /**
     *Matches the room from a list of all the rooms with a direction
     *@param direction string direction
     *@return Room object of the next room
     */
    public Room getConnectedRoom(String direction) {
        long id = 0;
        Room newRoom = null;

        if (roomMapping.containsKey(direction)) {
            id = roomMapping.get(direction);
            newRoom = new Room();
            newRoom.setRoomID(id);
        }

        for (Room connectedRoom : allRooms) {
            if (id == connectedRoom.getID()) {
                newRoom = connectedRoom;
            }
        }
        return newRoom;
    }

    /**
     *adds an item to a room
     *@param item item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     *removes an item from the room
     *@param item item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     *adds start to a room
     *@param start boolean of start
     */
    public void setStartRoom(boolean start) {
        startRoom = start;
    }

    /**
     *removes star from a room
     */
    public void removeStart() {
        startRoom = false;
    }

    /**
     *Sets the id of the connected room with the direction
     *@param direction direction of the room
     *@param id id of the room
     */
    public void setConnectedRoom(String direction, long id) {
        roomMapping.put(direction.toLowerCase(), id);
    }

    /**
     *Setter for Id
     *@param id id of room
     */
    public void setRoomID(long id) {
        roomId = id;
    }

     /**
     *Setter for roomName
     *@param name name of room
     */
    public void setRoomName(String name) {
        roomName = name;
    }

    /**
     *Setter for short description
     *@param sDescription description of room
     */
    public void setShortDescription(String sDescription) {
        shortDescription = sDescription;
    }

    /**
     *Setter for long description
     *@param lDescription description of room
     */
    public void setLongDescription(String lDescription) {
        longDescription = lDescription;
    }

    public void setRoomMapping(HashMap<String, Long> roomMapping) {
        this.roomMapping = roomMapping;
    }

    /**
     *toString for room
     *@return room.toString
     */
    public String toString() {
        return (roomName + " " + shortDescription);
    }
}
