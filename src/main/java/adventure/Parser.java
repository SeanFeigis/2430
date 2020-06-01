package adventure;
import java.util.Arrays;

public class Parser {

    private static String[] allCommands = {"go", "look", "take", "quit", "inventory"};

    /**
     *returns a command object with parsed values
     *@param userCommand the user input
     *@return command object
     */
    public Command parseUserCommands(String userCommand) throws InvalidCommandException {
        Command newCommand;
        userCommand = userCommand.trim();
        userCommand = userCommand.toLowerCase();
        if (userCommand.indexOf(" ") == -1) {
            newCommand = new Command(userCommand);
        } else { 
            String[] splitCommand = userCommand.split(" ", 2);
            newCommand = new Command(splitCommand[0], splitCommand[1]);
        }
        return newCommand;
    }

    /**
     *return all the commands
     *@return commands
     */
    public String[] allCommands() {
        return allCommands;
    }

    /**
     *toString of Parser classs
     *@return toString
     */
    public String toString() {
        return Arrays.toString(allCommands);
    }
}
