package adventure;

public class Item implements java.io.Serializable{
    /* you will need to add some private member variables */
    private long itemID;
    private String itemName;
    private String longDescription;
    private Room containingRoom;
    /* required public methods */


    /**
     *Default constructor
     */
    public Item() {
        setID(-1);
        setName("null");
        setDescription("null");
    }



    /**
     *Constructor
     *@param id id of the item
     *@param name name of the item
     *@param description description of the item
    */
    public Item(long id, String name, String description) {
        setID(id);
        setName(name);
        setDescription(description);
    }

    /**
     *Name getter
     *@return item name
    */
    public String getName() {
        return itemName;
    }

     /**
      *ID getter
      *@return item ID
    */
    public long getID() {
        return itemID;
    }

     /**
      *long description getter
      *@return item long description
    */
    public String getLongDescription(){
        return longDescription;
    }

    /**
      *returns a reference to the room that contains the item
      *@return containing room
    */
    public Room getContainingRoom(){
       return containingRoom;
    }

    /**
     *Containing room setter
     @param room containing room
     */
    public void setContainingRoom(Room room) {
        containingRoom = room;
    }

    /**
     *Containing item name setter
     @param name the item name
     */
    public void setName(String name) {
        itemName = name;
    }

    /**
     *Containing item description setter
     @param description the item description
     */
    public void setDescription(String description) {
        longDescription = description;
    }

    /**
     *Containing item ID setter
     @param id the item ID
     */
    public void setID(long id) {
        itemID = id;
    }

    /**
     *toString for item
     *@return toString of item
     */
    public String toString() {
        return (getName() + " " + getID());
    }

    /* you may wish to add some helper methods*/
}
