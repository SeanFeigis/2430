package adventure;
import java.util.Scanner;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

//JSON imports
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game{

   /* this is the class that runs the game.
   You may need some member variables */

   public static void main(String[] args){

    Scanner scnr = new Scanner(System.in);
    Game theGame = new Game();

    JSONObject adventureJson = new JSONObject();
    JSONObject adventureJson2 = new JSONObject();
    Adventure adventure;
    String gameInput;
    boolean defaultAdventure = true;

    System.out.println("*******************************");
    System.out.println("Welcome to the adventure game!");
    System.out.println("*******************************");

    System.out.println("Would you like to load a json file(y/n)?");
    boolean correctInput = false;
    while (!correctInput) {
        gameInput = scnr.nextLine().toLowerCase();
        if (gameInput.equals("y")) {
            System.out.println("Please enter the filename(Remember to ues the proper relative path).");
            adventureJson = theGame.loadAdventureJson(scnr.nextLine());
            if (adventureJson != null) {
                correctInput = true;
                defaultAdventure = false;
            }
        } else if (gameInput.equals("n")) {
            adventureJson = theGame.loadAdventureJson("./src/main/java/adventure/example_adventure.json");
            correctInput = true;
        } else {
            System.out.println("Error, invald input. Please Try again.");
        }
    }
    adventureJson2 = (JSONObject) adventureJson.get("adventure");
      
    adventure = theGame.generateAdventure(adventureJson2);
    Room currentGameRoom = new Room();
    currentGameRoom = adventure.getCurrentRoom();
    
    if (defaultAdventure) {
        System.out.println("\nLoading default adventure...\n");
        System.out.println("You wake to the sound of chirping and immediately absorb your surroundingss. You are in a bright deciduous forest");
        System.out.println("covered with a full canopy and thick leaves. You see no signs of any civilization. This is where you adventure begins!\n");
    } else {
        System.out.println("\nLoading your adventure...\n");
    }
    
    System.out.println(currentGameRoom.getShortDescription() + "\n");
    if (!currentGameRoom.listItems().isEmpty()) {
        System.out.printf("Area contains:");
        for (Item item : currentGameRoom.listItems()) {
            System.out.printf("%s ",item.getName());
        }
        System.out.println("\n");
    }

    boolean endGame = false;
        while(!endGame) {
        System.out.println("What do you do?\n");
        gameInput = scnr.nextLine();
        System.out.printf("\n");
        currentGameRoom = theGame.parseInput(gameInput,currentGameRoom);
        adventure.setCurrentRoom(currentGameRoom);
        }
    }

    private Room parseInput(String gameInput, Room currentRoom) {
        String[] splitInput;
        gameInput = gameInput.trim();
        gameInput = gameInput.toLowerCase();
        if (gameInput.indexOf(" ") == -1) {
            if (gameInput.equals("look")) {
                System.out.println(currentRoom.getLongDescription() + "\n");
            } else if (gameInput.equals("q") || gameInput.equals("quit") || gameInput.equals("exit") ) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            } else {
                System.out.println("Please enter a vald input");
            }
        }  else {
            splitInput = gameInput.split(" ");
            if (splitInput[0].equals("look")) {
                lookItems(splitInput[1], currentRoom);
            } else if (splitInput[0].equals("go")) {
                if (splitInput[1].toUpperCase().equals("N") || splitInput[1].toUpperCase().equals("S") || splitInput[1].toUpperCase().equals("E") || splitInput[1].toUpperCase().equals("W")) {
                    Room tempRoom = moveRooms(splitInput[1].toUpperCase(), currentRoom);
                    if (tempRoom != null) {
                        currentRoom = tempRoom;
                    }
                } else {
                    System.out.println("Please enter a vald input");
                }
            } 
        }
        return currentRoom;
    }

    private Room moveRooms(String dir, Room currentRoom) {
        Room tempRoom = currentRoom.getConnectedRoom(dir);
        if (tempRoom != null) {
            currentRoom = tempRoom;
            System.out.println(currentRoom.getShortDescription() + "\n");
            if (!currentRoom.listItems().isEmpty()) {
                System.out.printf("Area contains:");
                for (Item item : currentRoom.listItems()) {
                    System.out.printf("%s ",item.getName());
                }
                System.out.println("\n");
            }
        } else {
            System.out.println("You cannot traverse there");
        }
        return currentRoom;
    }

    private void lookItems(String item, Room currentRoom) {
        ArrayList<Item> itemList = currentRoom.listItems();
        for (Item currentItem : itemList) {
            if (currentItem.getName().equals(item)) {
                System.out.println(currentItem.getLongDescription()+ "\n");
            } else {
                System.out.println("Cannot find item");
            }
        }
    }

   public JSONObject loadAdventureJson(String filename){
      JSONObject adventureJson;
      JSONParser parser = new JSONParser();
      JSONObject adventure;

      try (Reader reader = new FileReader(filename)) {
         adventureJson = (JSONObject) parser.parse(reader);
         //System.out.println(adventureJson.toString());
      } catch (Exception e) {
         System.out.println("File not found.");
         adventureJson = null;
      }

      

      return adventureJson;
    }
    public Adventure generateAdventure(JSONObject obj) {
      
        ArrayList<Room> roomArray = new ArrayList<Room>();
        ArrayList<Item> itemArray = new ArrayList<Item>();
        Room startRoom = new Room();
        JSONArray roomList = (JSONArray) obj.get("room");
        JSONArray itemList = (JSONArray) obj.get("item");
    
        for (Object currentItem : itemList) {
            JSONObject jItem = (JSONObject) currentItem;
            Item item = new Item( (long) jItem.get("id"), (String) jItem.get("name"),  (String) jItem.get("desc"));
            itemArray.add(item);
        }

      
      for (Object currentRoom : roomList) {
        JSONObject jRoom = (JSONObject) currentRoom;
        Room room = new Room((long)jRoom.get("id") , (String) jRoom.get("name"), (String) jRoom.get("short_description"), (String) jRoom.get("long_description") );
        if (jRoom.containsKey("start")) {
            room.addStart((Boolean.valueOf((String)jRoom.get("start"))));
            startRoom = room;
        }
        if ( jRoom.containsKey("loot")) {
            JSONArray loot = (JSONArray) jRoom.get("loot");
            for (Object currentItem : loot) {
                JSONObject itemInRoom = (JSONObject) currentItem;
                for (Item item : itemArray) { 

                    if ((long) itemInRoom.get("id") == item.getID() ) {
                        item.setContainingRoom(room);
                        room.addItem(item); 
                        
                    } 
                }
            }
        }
        JSONArray entranceID = (JSONArray) jRoom.get("entrance");
        for (Object currentEntrance : entranceID) {
            JSONObject jEntrance = (JSONObject) currentEntrance;
            room.setConnectedRoom( (String) jEntrance.get("dir") , (long) jEntrance.get("id"));
        }
        
        roomArray.add(room);
      }

        for (Room currentRoom : roomArray) {
            currentRoom.addAllRooms(roomArray);
        }





        //System.out.println(roomArray.get(1).listItems().get(0).getName());
        Adventure adventure = new Adventure(roomArray, itemArray);
        adventure.setCurrentRoom(startRoom);
        
      return adventure;
    }
}
