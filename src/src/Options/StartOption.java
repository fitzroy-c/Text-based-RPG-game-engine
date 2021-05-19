package Options;

import Player.Player;

public class StartOption extends BasicOption{
    private void buildMenu() {
        this.option.add(new Option("new", "start the new game"));
        this.option.add(new Option("load", "load the game"));
        this.option.add(new Option("exit", "exit the game"));
    }

    public StartOption() {
        buildMenu();
        showMenu();
    }

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
