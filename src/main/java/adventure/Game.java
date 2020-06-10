package adventure;
import java.util.Scanner;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

//JSON imports
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game implements java.io.Serializable {

    private Parser parser = new Parser();
    private Adventure adventure;
    private Scanner scnr = new Scanner(System.in);

    public static void main(String[] args){
        Command nextCommand = null;
        Game theGame = new Game();
        String saveGameType;
        String fileName;
        JSONObject adventureJson = new JSONObject();
        JSONObject adventureJson2 = new JSONObject();
        String gameInput;
        boolean defaultAdventure = true;

        if (args.length != 2) {
            System.out.println("Invalid commandline, please input proper command line commands");
            System.exit(1);
        } else {
            saveGameType = args[0];
            fileName = args[1];
        }
        
        if (args[0].equals("-a")) {
            InputStream inputStream = Game.class.getClassLoader().getResourceAsStream(args[1]);
            if (inputStream == null) {
                System.out.println("The file does not exist!");
                System.exit(0);
            }
            adventureJson = theGame.loadAdventureJson(inputStream);
            adventureJson2 = (JSONObject) adventureJson.get("adventure");
            theGame.adventure = theGame.generateAdventure(adventureJson2);
        } else if (args[0].equals("-l")) {
            theGame.adventure = theGame.loadAdventure(args[1]);
        }
        
        System.out.println("*******************************");
        System.out.println("Welcome to the adventure game!");
        System.out.println("*******************************");
        System.out.println("\nLoading your adventure...\n");
    

        theGame.printCurrentRoom();
        boolean endGame = false;
        while(!endGame) {
            System.out.println("What do you do?\n");
            gameInput = theGame.scnr.nextLine();
            System.out.printf("\n");

            try {
                nextCommand = theGame.parse(gameInput);
                theGame.followCommands(nextCommand);
            } catch (InvalidCommandException exc) {
                System.out.println("Didnt recognize command\n");
                theGame.printCurrentRoom();
            }

        }
    }

    public void followCommands(Command toDo) {
        if (toDo.getActionWord().equals("go")) {
           System.out.print(adventure.moveRooms(toDo));
            printCurrentRoom();
        } else if (toDo.getActionWord().equals("take")) {
            System.out.println(adventure.takeItems(toDo));
            printCurrentRoom();
        } else if (toDo.getActionWord().equals("look")) {
            System.out.println(adventure.look(toDo));
        } else if (toDo.getActionWord().equals("quit")) {
            quitAdventure();
        } else if (toDo.getActionWord().equals("inventory")) {
            System.out.println(adventure.viewInventory());
            printCurrentRoom();
        } /*else if (toDo.getActionWord().equals("use")) {
            adventure.useItem(toDo);
            printCurrentRoom();
        } */
    }

    public void promptName(){
        System.out.println("What is your characters name?");
        adventure.getPlayer().setUserName(scnr.nextLine());
        adventure.getPlayer().setSaveGameName(adventure.getPlayer().getName() + "Save.sav");
    }

    public void quitAdventure() {
        System.out.println("Would you like to save the game?(\"y\" to save, anything else to quit)");
        String input = scnr.nextLine().toLowerCase();
            if (input.equals("y")) {
                promptName();
                try {
                    FileOutputStream outPutStream = new FileOutputStream(adventure.getPlayer().getSaveGameName());
                    ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream);

                    outPutDest.writeObject(adventure);

                    outPutDest.close();
                    outPutStream.close();

                    System.out.println("Game saved as: "+adventure.getPlayer().getSaveGameName());
                    System.exit(0);
                } catch(IOException ex) {
                    System.out.println(ex);
                }
            } else {
                System.exit(0);
            }
       
    }

    public void printCurrentRoom() {
        System.out.println(adventure.getCurrentRoom().getName());
        System.out.println(adventure.getCurrentRoom().getShortDescription() + "\n");
        if (!adventure.getCurrentRoom().listItems().isEmpty()) {
            System.out.printf("Area contains:");
            for (Item item : adventure.getCurrentRoom().listItems()) {
                System.out.printf("%s ",item.getName());
            }
            System.out.println("\n");
        }
    }

    public void printDefaultAdventure() {
        System.out.println("\nLoading default adventure...\n");
        System.out.println("You wake to the sound of chirping and immediately absorb your surroundingss. You are in a bright deciduous forest");
        System.out.println("covered with a full canopy and thick leaves. You see no signs of any civilization. This is where you adventure begins!\n");
    }
 
    public Command parse(String userInput) throws InvalidCommandException {
        return parser.parseUserCommand(userInput);
    }

    public Adventure loadAdventure(String saveName) {
        Adventure advent = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveName));) {
            advent = (Adventure)in.readObject();
            System.out.println("Object has been loaded");
        } catch(IOException ex) {
            System.out.println("IOException is caught " + ex);
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught " + ex);
        }
        return advent;
    }

    public JSONObject loadAdventureJson(InputStream inputStream) {
        JSONObject adventureJson;
        JSONParser jparser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            adventureJson = (JSONObject) jparser.parse(reader);
        } catch (Exception e) {
            System.out.println("File not found.");
            System.exit(0);
            adventureJson = null;
        }
        return adventureJson;
    }

    public JSONObject loadAdventureJson(String filename){
        JSONObject adventureJson;
        JSONParser jparser = new JSONParser();

        try (Reader reader = new FileReader(filename)) {
            adventureJson = (JSONObject) jparser.parse(reader);
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
        Room room = new Room((long)jRoom.get("id"),(String) jRoom.get("name"),(String) jRoom.get("short_description"),(String)jRoom.get("long_description") );
        if (jRoom.containsKey("start")) {
            room.setStartRoom((Boolean.valueOf((String)jRoom.get("start"))));
            startRoom = room;
        }
        if ( jRoom.containsKey("loot")) {
            JSONArray loot = (JSONArray) jRoom.get("loot");
            for (Object currentItem : loot) {
                JSONObject itemInRoom = (JSONObject) currentItem;
                for (Item item : itemArray) { 

                    if ((long) itemInRoom.get("id") == item.getItemID() ) {
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
            currentRoom.setAllRooms(roomArray);
        }

        Adventure advent = new Adventure(roomArray, itemArray);
        advent.setCurrentRoom(startRoom);
        
      return advent;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public void setScnr(Scanner scnr) {
        this.scnr = scnr;
    }
}
