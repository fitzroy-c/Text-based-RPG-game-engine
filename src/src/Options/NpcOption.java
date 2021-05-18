package Options;

import AbnormalPoints.AbnormalPoint;
import Player.Player;

public class NpcOption extends BasicOption{
    private void buildMenu() {
        this.option.add(new Option("pick", "Pick an item"));
        this.option.add(new Option("north", "Go north"));
        this.option.add(new Option("south", "Go south"));
        this.option.add(new Option("east", "Go east"));
        this.option.add(new Option("west", "Go west"));
    }

    public NpcOption(AbnormalPoint npc, Player player) {
        buildMenu();
        showMenu(this.option);
        //...//
    }

}
