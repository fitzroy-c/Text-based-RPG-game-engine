package Options;

public class Option {
    String command;
    String description;

    public Option(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCommand(String command) {this.command = command;}

    public String getCommand() {return command;}
}
