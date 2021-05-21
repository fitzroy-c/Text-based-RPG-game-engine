package Options;

/**
 * The class is used before user start the game journey.
 * It can guid user to start the game.
 *
 * @author Zihong Yuan
 */
public class StartOption extends BasicOption{
    /**
     * The method add new game; load game and exit option
     * to this menu.
     *
     * @author Zihong Yuan
     */
    private void buildMenu() {
        this.option.add(new Option("new", "start the new game"));
        this.option.add(new Option("load", "load the game"));
        this.option.add(new Option("exit", "exit the game"));
    }

    /**
     * The constructor method initialize
     * a start option menu.
     *
     * @author Zihong Yuan
     */
    public StartOption() {
        buildMenu();
        showMenu();
    }

    /**
     * The method can transform user's input
     * into index, in order to find
     * corresponding option.
     *
     * @author Zihong Yuan
     * @param command command name
     * @return index
     */
    public int getInput(String command) {
        switch (command) {
            case "1":
            case "new": return 0;
            case "2":
            case "load": return 1;
            case "3":
            case "exit": return 2;
            default: return 3;
        }
    }

}
