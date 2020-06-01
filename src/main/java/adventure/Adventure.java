package adventure;
import java.util.ArrayList;

public class Adventure implements java.io.Serializable {
    /* you will need to add some private member variables */

    private static final long serialVersionUID = -8025354008342346245L;
    private ArrayList<Room> allRooms;
    private ArrayList<Item> allItems; 
    private Player playerCharacter;
    private Room currentRoom;

    /**
     * Constructor:  
     * @param roomArray all the rooms
     * @param itemArray all the items
     * 
     */
    public Adventure(ArrayList<Room> roomArray, ArrayList<Item> itemArray ) {
        setAllRooms(roomArray);
        setAllItems(itemArray);
        setCurrentRoom(null);
        setPlayer(new Player());
    }


     /**
     * Default Constructor:  
     */
    public Adventure() {
        setCurrentRoom(new Room());
        setAllRooms(new ArrayList<Room>());
        setAllItems(new ArrayList<Item>());
        setPlayer(new Player());
    }

    /**
     *Getter for allRooms
     *@return allRooms
     */
    public ArrayList<Room> listAllRooms(){
        return allRooms;
    }

    /**
     *Setter for allRooms
     *@param roomList ArrayList of rooms
     */
    public void setAllRooms(ArrayList<Room> roomList) {
        allRooms = roomList;
    }

     /**
     *Getter for allItems
     *@return allItems
     */
    public ArrayList<Item> listAllItems(){
        return allItems;
    }

    /**
     *Setter for allItems
     *@param itemList ArrayList of items
     */
    public void setAllItems(ArrayList<Item> itemList) {
        allItems = itemList;
    }

     /**
     *Getter for currentRoomDescription
     *@return currentRooms Short descrption
     */
    public String getCurrentRoomDescription(){
        return currentRoom.getShortDescription();
    }

    /**
     *Setter for currentRoom
     *@param room the current room
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     *Getter for currentRoom
     *@return current Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *Getter for player
     *@return player
     */
    public Player getPlayer() {
        return playerCharacter;
    }

    /**
     *Setter for player
     *@param player
     */
    public void setPlayer(Player player) {
        playerCharacter = player;
    }

    /**
     *Changes currentRoom based on the move Command
     *@param moveCommand the direction specified by the user
     *@return the string response if move fails
     */
    public String moveRooms(Command moveCommand) {
        Room tempRoom = currentRoom.getConnectedRoom(moveCommand.getNoun());
        String response = "";
        if (tempRoom != null) {
            currentRoom = tempRoom;
        } else {
            response = "You cannot traverse there\n\n";
        }
        return response;
    }

    /**
     *differentiates between look at item and level
     *@param lookCommand the command to look 
     *@return the string of the output
     */
    public String look(Command lookCommand) {
        String response = null;
        if (lookCommand.getNoun() == null) {
            response = lookAround();
        } else {
            response = lookItems(lookCommand);
        }
        return response;
    }

    /**
     *returns the long description
     *@return long description of current room
     */
    public String lookAround() {
        return currentRoom.getLongDescription() + "\n";
    }

    /**
     *returns item description
     *@param lookItemCommand the command to look at item 
     *@return the string of the output
     */
    public String lookItems(Command lookItemCommand) {
        ArrayList<Item> itemList = currentRoom.listItems();
        String response = "Cannot find item\n";
        for (Item currentItem : itemList) {
            if (currentItem.getName().equals(lookItemCommand.getNoun())) {
                response = currentItem.getLongDescription() + "\n";
            } else {
                response = "Cannot find item";
            }
        }
        return response;
    }

     /**
     *takes an item from the current room
     *@param takeItemCommand the command to take an item 
     *@return the string of the output
     */
    public String takeItems(Command takeItemCommand) {
        ArrayList<Item> itemList = currentRoom.listItems();
        String response = null;
        for (Item currentItem : itemList) {
            if (currentItem.getName().equals(takeItemCommand.getNoun())) {
                currentRoom.removeItem(currentItem);
                playerCharacter.addItem(currentItem);
                response = "You take the " + currentItem.getName() + "\n";
                break;
            } else {
                response = "Cannot find item";
            }
        }
        return response;
    }

     /**
     *return the inventory formatted
     *@return the string of the output
     */
    public String viewInventory() {
        String response;

        if (playerCharacter.getUserItems().isEmpty()) {
            response = "Your inventory is empty!\n";
        } else {
            response = "Inventory: \n";
            for (Item item: playerCharacter.getUserItems()) {
                response+= item.getName() + "\n";
            }
        }
        return response;
    }

    /**
     *toString of Adventure
     *@return toString of adventure
     */
    public String toString() {
        String response;

        response = "Rooms: ";
        for (Room room: listAllRooms()) {
            response+= room.getName() + "\n";
        }
        response+= "\nItems: ";
        for (Item item: listAllItems()) {
            response+= item.getName() + "\n";
        }
        response+= "\n Player Name" + playerCharacter.getName(); 
        return response;
    }
    
}
