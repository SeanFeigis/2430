package adventure;
import java.util.ArrayList;

public class Player implements java.io.Serializable {
    private String userName;
    private ArrayList<Item> inventory;
    private String saveGameName;
    private Room currentRoom;


    /**
     *Default Constructor
     *
     */
    public Player() {
        setUserName("null");
        setSaveGameName("null");
        setCurrentRoom(new Room());
        setInventory(new ArrayList<Item>());
    }

     /**
     *Default Constructor
     *@param name name of the player
     *@param gameName name of the saveGame
     *@param currRoom of the player
     */
    public Player(String name, String gameName, Room currRoom) {
        setUserName(name);
        setSaveGameName(gameName);
        setCurrentRoom(currRoom);
        setInventory(new ArrayList<Item>());
    }

    /**
     *Adds an item to the inventory
     *@param itemToAdd new item
     */
    public void addItem(Item itemToAdd) {
        inventory.add(itemToAdd);
    }

    /**
     *Getter for name
     *@return userName
     */
    public String getName(){
        return userName;
    }

    /**
     *Setter for name
     *@param name name to set
     */
    public void setUserName(String name) {
        userName = name;
    }

    /**
     *Getter for inventory
     *@return inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     *Setter for userItems
     *@param itemArray inventory to set
     */
    public void setInventory(ArrayList<Item> itemArray) {
        inventory = itemArray;
    }

    /**
     *Getter for current room
     *@return current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

     /**
     *Setter for currentRoom
     *@param room room to set
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     *Getter for save game name
     *@return save game name
     */
    public String getSaveGameName() {
        return saveGameName;
    }

     /**
     *Setter for save game name
     *@param gameName save game name to set
     */
    public void setSaveGameName(String gameName) {
        saveGameName = gameName;
    }


    /**
     *toString method for Player
     *@return returns name and room
     */
    public String toString() {
        return userName + "room: " + currentRoom ;
    }
}
