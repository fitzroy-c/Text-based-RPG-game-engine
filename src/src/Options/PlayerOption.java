package Options;

import java.io.File;

public class PlayerOption extends BasicOption{
    File playerFolder = new File("json_files/player_save/");
    File[] playerList = playerFolder.listFiles();

    public void buildMenu() {
        for (File file : playerList) {
            this.option.add(new Option("Player", file.getName()));
        }
    }

    public PlayerOption() {
        buildMenu();
    }
}
