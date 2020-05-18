package adventure;

public class Item{
    /* you will need to add some private member variables */
   private long itemID;
   private String itemName;
   private String longDescription;
   private Room containingRoom;
    /* required public methods */


    //Constructor 
   public Item(long id, String name, String description) {
        itemID = id;
        itemName = name;
        longDescription = description;
   }


   public String getName() {
      return itemName;
   }

    public long getID() {
        return itemID;
    }

    public String getLongDescription(){
        return longDescription;
    }

    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
       return null;
    }

    public void setContainingRoom(Room room) {
        containingRoom = room;
    }

    /* you may wish to add some helper methods*/
}