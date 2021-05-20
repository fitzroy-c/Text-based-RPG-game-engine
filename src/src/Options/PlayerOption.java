package Options;

import CommandParser.Token;
import Player.Player;

import java.io.File;

public class PlayerOption extends BasicOption{
    File playerFolder = new File("json_files/player_save/");
    File[] playerList = playerFolder.listFiles();
    public Player player;

    public void buildMenu() {
        for (File file : playerList) {
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            if (fileName.isEmpty())
                continue;
            this.option.add(new Option(fileName, null));
        }
    }

    public PlayerOption(Player player) {
        this.player = player;
        buildMenu();
        showMenu();
    }

    @Override
    public boolean chooseOp(Token token) {
        String playerN = token.token();
        if (optionInterface.containsKey(playerN)) {
            playerN = optionInterface.get(playerN).getCommand();
            this.player = Player.load(playerN);
            return true;
        } else {return false;}
    }
}
