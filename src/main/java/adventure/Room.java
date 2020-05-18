package adventure;
import java.util.ArrayList;


public class Room{
    /* you will need to add some private member variables */
    private long roomId;
    private String roomName;
    private boolean startRoom = false;;
    private String longDescription;
    private String shortDescription;
    private ArrayList<Long> entranceID = new ArrayList<Long>();
    private ArrayList<String> directions = new ArrayList<String>();
    private ArrayList<Item> items = new ArrayList<Item>();
    /* required public methods */

    public Room() {
        
    }

    public Room(long id, String Name, String sDescription, String lDescription) {
        roomId = id;
        roomName = Name;
        shortDescription = sDescription;
        longDescription = lDescription;    
    }

    public ArrayList<Item> listItems(){
        return items;
    }

    public String getName(){
        return roomName;  
    }

    public String getLongDescription(){
        return longDescription;
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public Room getConnectedRoom(String direction) {
        return null;
    }

    public void addItem (Item item) {
        items.add(item);
    }

    public void addStart (boolean start) {
        startRoom = start;
    }

    public void setConnectedRoom(String direction, long id) {
        directions.add(direction);
        entranceID.add(id);
    }

    /* you may wish to add some helper methods*/
}