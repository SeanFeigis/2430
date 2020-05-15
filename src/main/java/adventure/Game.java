package adventure;

public class Game{

    /* this is the class that runs the game.
    You may need some member variables */

    public static void main(String args[]){

        /* You will need to instantiate an object of type
        game as we're going to avoid using static methods
        for this assignment */

        Game theGame = new Game();
        // 1. Print a welcome message to the user
            System.out.println("Welcome to the <insert name> game!");
        // 2. Ask the user if they want to load a json file.
            System.out.prtinln("Would you like to load a json file(y/n)?");
        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/

        // 4. Print the beginning of the adventure

        // 5. Begin game loop here

        // 6. Get the user input. You'll need a Scanner

        /* 7+. Use a game instance method to parse the user
        input to learn what the user wishes to do*/

        //use a game instance method to execute the users wishes*/

        /* if the user doesn't wish to quit,
        repeat the steps above*/
    }

    /* you must have these instance methods and may need more*/

    public JSONObject loadAdventureJson(String filename){

    }
    public Adventure generateAdventure(JSONObject obj) {
        
    }

}