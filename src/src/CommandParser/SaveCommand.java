package CommandParser;

import Player.Player;

public class SaveCommand extends Command{

    private Command cmd;
    public Player player;

    public SaveCommand(Command cmd, Player player){
        this.cmd = cmd;
        this.player = player;
    }

    @Override
    public String show() {
        return "the game has been saved!";
    }

    @Override
    public void evaluate() {
        player.save();
//        return player.save();
    }
}
