package Options;

/**
 * The class can store command and it's description
 *
 * @author Zihong Yuan
 */
public class Option {
    String command;
    String description;

    /**
     * The constructor method for Option class.
     * To store command.
     *
     * @author Zihong Yuan
     * @param command command name
     * @param description command description
     */
    public Option(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Set description
     *
     * @author Zihong Yuan
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get description
     *
     * @author Zihong Yuan
     * @return this command's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set this command.
     *
     * @author Zihong Yuan
     * @param command command
     */
    public void setCommand(String command) {this.command = command;}

    /**
     * Get this command.
     *
     * @author Zihong Yuan
     * @return this command
     */
    public String getCommand() {return command;}
}
