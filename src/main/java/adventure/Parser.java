package adventure;

public class Parser {

    private String allCommands = "go, look, take, quit, inventory, use";

    /**
     *returns a command object with parsed values
     *@param userCommand the user input
     *@return command object
     */
    public Command parseUserCommand(String userCommand) throws InvalidCommandException {
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
    public String allCommands() {
        return allCommands;
    }

    public void setAllCommands(String commands) {
        allCommands = commands;
    }

    /**
     *toString of Parser classs
     *@return toString
     */
    public String toString() {
        return allCommands;
    }
}
