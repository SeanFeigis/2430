package adventure;
import java.util.ArrayList;

public class Player implements java.io.Serializable {
    private String userName;
    private ArrayList<Item> userItems;
    private String saveGameName;
    private Room currentRoom;


    /**
     *Default Constructor
     *
     */
    public Player() {
        setName("null");
        setSaveGameName("null");
        setCurrentRoom(new Room());
        setUserItems(new ArrayList<Item>());
    }

     /**
     *Default Constructor
     *@param name name of the player
     *@param gameName name of the saveGame
     *@param currRoom of the player
     */
    public Player(String name, String gameName, Room currRoom) {
        setName(name);
        setSaveGameName(gameName);
        setCurrentRoom(currRoom);
        setUserItems(new ArrayList<Item>());
    }

    /**
     *Adds an item to the inventory
     *@param itemToAdd new item
     */
    public void addItem(Item itemToAdd) {
        userItems.add(itemToAdd);
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
    public void setName(String name) {
        userName = name;
    }

    /**
     *Getter for inventory
     *@return inventory
     */
    public ArrayList<Item> getUserItems() {
        return userItems;
    }

    /**
     *Setter for userItems
     *@param itemArray inventory to set
     */
    public void setUserItems(ArrayList<Item> itemArray) {
        userItems = itemArray;
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
}
