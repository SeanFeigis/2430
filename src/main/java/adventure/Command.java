package adventure;
import java.util.ArrayList;
import java.util.Arrays;

public class Command {
    private String action;
    private String noun;
    private static ArrayList<String> allCommands = new ArrayList<String>(Arrays.asList("go", "look", "take", "quit", "inventory"));

  /**
     * Create a command object with default values.  
     * both instance variables are set to null
     * 
     */
    public Command() throws InvalidCommandException {
        this(null, null);
    }

  /**
     * Create a command object given only an action.  this.noun is set to null
     *
     * @param command The first word of the command. 
     * 
     */
    public Command(String command) throws InvalidCommandException{

        //TODO validate the action word here and throw an exception if it isn't
        // a single-word action
        this(command, null);
    }
    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command. 
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException{
        //TODO validate the command here and ensure that the noun provided
        // is a legitimate second word for the command
        // throw an exception if not
        //if ()

            if (allCommands.contains(command)) {
                this.action = command;
                this.noun = what;
            } else {
                throw new InvalidCommandException( "Invalid command: " + command);  
            }
    }


    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }

    /**
     *toString of Command class
     *@return toString of command
     */
    public String toString() {
        return getActionWord() + " " + getNoun();
    }
}
