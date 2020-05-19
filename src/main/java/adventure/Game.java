package adventure;
import java.util.Scanner;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

//JSON imports
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Game{

   /* this is the class that runs the game.
   You may need some member variables */

   public static void main(String args[]){

      /* You will need to instantiate an object of type
      game as we're going to avoid using static methods
      for this assignment */
      Scanner scnr = new Scanner(System.in);
      Game theGame = new Game();

      JSONObject adventure_json = new JSONObject();
      JSONObject adventure_json2 = new JSONObject();
      Adventure adventure;
      String gameInput;
      boolean defaultAdventure = true;


        // 1. Print a welcome message to the user
            System.out.println("Welcome to the adventure game!");
        // 2. Ask the user if they want to load a json file.
            System.out.println("Would you like to load a json file(y/n)?");
            boolean correctInput = false;
            while (!correctInput) {
               gameInput = scnr.nextLine().toLowerCase();
               if (gameInput.equals("y")) {
                  System.out.println("Please enter the filename(Remember to ues the proper relative path).");
                  adventure_json = theGame.loadAdventureJson(scnr.next());
                  if (adventure_json != null) {
                      correctInput = true;
                    defaultAdventure = false;
                  }
                  
               } 
               else if (gameInput.equals("n")) {
                  adventure_json = theGame.loadAdventureJson("./src/main/java/adventure/example_adventure.json");
                  correctInput = true;
               } 
               else {
                  System.out.println("Error, invald input. Please Try again.");
               }
            }
            adventure_json2 = (JSONObject) adventure_json.get("adventure");
        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/
        
        adventure = theGame.generateAdventure(adventure_json2);
        Room currentGameRoom = new Room();
        currentGameRoom = adventure.getCurrentRoom();
        // 4. Print the beginning of the adventure
        if (defaultAdventure) {
            System.out.println("Loading default adventure...\n");
            System.out.println("You wake to the sound of chirping and immediately absorb your surrounds. You are in a bright deciduous forest, covered with a full canopy and thich leaves.\nYou see no signs of any civilization. This is where you adventure begins");
        }
        // 5. Begin game loop here
        boolean endGame = false;
            while(!endGame) {
            System.out.println("What is your action?\n");
            // 6. Get the user input. You'll need a Scanner
            gameInput = scnr.nextLine();
            System.out.printf("\n");
            currentGameRoom = theGame.parseInput(gameInput,currentGameRoom);
            adventure.setCurrentRoom(currentGameRoom);
            /* 7+. Use a game instance method to parse the user
            input to learn what the user wishes to do*/

            //use a game instance method to execute the users wishes*/

            /* if the user doesn't wish to quit,
            repeat the steps above*/
            }
    }

    /* you must have these instance methods and may need more*/

    private Room parseInput(String gameInput, Room currentRoom) {
        String[] splitInput;
        gameInput = gameInput.trim();
        gameInput = gameInput.toLowerCase();
        if (gameInput.indexOf(" ") == -1) {
            if (gameInput.equals("look")) {
                System.out.println(currentRoom.getLongDescription());
            } else if (gameInput.equals("q") || gameInput.equals("quit") || gameInput.equals("exit") ) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            } else {
                System.out.println("Please enter a vald input");
            }
        }  
        else {
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
            System.out.println(currentRoom.getShortDescription());
        } else {
            System.out.println("Room not found");
        }
        return currentRoom;
    }

    private void lookItems(String item, Room currentRoom) {
        ArrayList<Item> itemList = currentRoom.listItems();

        for (Item current_item : itemList) {
            if (current_item.getName().equals(item)) {
                System.out.println(current_item.getLongDescription());
            } else {
                System.out.println("Cannot find item");
            }
        }
    }



   public JSONObject loadAdventureJson(String filename){
      JSONObject adventure_json;
      JSONParser parser = new JSONParser();
      JSONObject adventure;

      try (Reader reader = new FileReader(filename)) {
         adventure_json = (JSONObject) parser.parse(reader);
         //System.out.println(adventure_json.toString());
      } catch (Exception e) {
         System.out.println("File not found.");
         adventure_json = null;
      }

      

      return adventure_json;
    }
    public Adventure generateAdventure(JSONObject obj) {
      
        ArrayList<Room> roomArray = new ArrayList<Room>();
        ArrayList<Item> itemArray = new ArrayList<Item>();
        Room startRoom = new Room();
        JSONArray room_list = (JSONArray) obj.get("room");
        JSONArray item_list = (JSONArray) obj.get("item");

        for (Object current_Item : item_list) {
            JSONObject jItem = (JSONObject) current_Item;
            //System.out.println(jItem.toString());
            Item item = new Item( (long) jItem.get("id"), (String) jItem.get("name"),  (String) jItem.get("desc"));
            itemArray.add(item);
        }

      
      for (Object current_room : room_list) {
        JSONObject jRoom = (JSONObject) current_room;
        //System.out.println(jRoom.toString());
        Room room = new Room((long)jRoom.get("id") , (String) jRoom.get("name"), (String) jRoom.get("short_description"), (String) jRoom.get("long_description") );
        if (jRoom.containsKey("start")) {
            room.addStart((Boolean.valueOf((String)jRoom.get("start"))));
            startRoom = room;
        }
        if ( jRoom.containsKey("loot")) {
            JSONArray loot = (JSONArray) jRoom.get("loot");
            for (Object current_Item : loot) {
                JSONObject itemInRoom = (JSONObject) current_Item;
                for (Item item : itemArray) { 

                    if ((long) itemInRoom.get("id") == item.getID() ) {
                        item.setContainingRoom(room);
                        room.addItem(item); 
                        
                    } 
                }
            }
        }
        JSONArray entranceID = (JSONArray) jRoom.get("entrance");
        for (Object current_entrance : entranceID) {
            JSONObject jEntrance = (JSONObject) current_entrance;
            room.setConnectedRoom( (String) jEntrance.get("dir") , (long) jEntrance.get("id"));
        }
        
        roomArray.add(room);
      }

        for (Room current_Room : roomArray) {
            current_Room.addAllRooms(roomArray);
        }





        //System.out.println(roomArray.get(1).listItems().get(0).getName());
        Adventure adventure = new Adventure(roomArray, itemArray);
        adventure.setCurrentRoom(startRoom);
        
      return adventure;
    }

}