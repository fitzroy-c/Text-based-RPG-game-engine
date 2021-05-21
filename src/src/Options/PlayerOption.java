package Options;

import CommandParser.Token;
import Player.Player;

import java.io.File;

/**
 * The class is the Option Menu when user wants
 * to load player profile before they start the game.
 *
 * @author Zihong Yuan
 */
public class PlayerOption extends BasicOption{
    File playerFolder = new File("json_files/player_save/"); // player profiles store in this location.
    File[] playerList = playerFolder.listFiles();
    public Player player;

    /**
     * The method will add existing player profiles into option menu.
     *
     * @author Zihong Yuan
     */
    public void buildMenu() {
        for (File file : playerList) {
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            if (fileName.isEmpty())
                continue;
            this.option.add(new Option(fileName, null));
        }
    }

    /**
     * The constructor method initialize player option and
     * display all existing player profiles' player name to the user.
     *
     * @author Zihong Yuan
     * @param playerOld
     */
    public PlayerOption(Player playerOld) {
        this.player = playerOld;
        buildMenu();
        showMenu();
    }

    /**
     * The method can detect whether user input the correct name.
     *
     * @author Zihong Yuan
     * @param token user's input after tokenize.
     * @return true if the user input is valid.
     */
    @Override
    public boolean chooseOp(Token token) {
        String playerN = token.token();
        if (optionInterface.containsKey(playerN)) {
            playerN = optionInterface.get(playerN).getCommand();
            player = Player.load(playerN);
            System.out.println("player name is "+player.getName());
            return true;
        } else {return false;}
    }
}
