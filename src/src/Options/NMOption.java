package Options;

import AbnormalPoints.AbnormalPoint;
import CommandParser.Token;

import java.util.ArrayList;
import java.util.List;

public class NMOption extends BasicOption{
    List<AbnormalPoint> monsters = new ArrayList<>();
    List<AbnormalPoint> npcs = new ArrayList<>();
    List<AbnormalPoint> total = new ArrayList<>();


    private void buildMenu(List<AbnormalPoint> monsters, List<AbnormalPoint> npcs) {
        this.monsters = monsters;
        this.npcs = npcs;
        total.addAll(monsters);
        total.addAll(npcs);

        for (AbnormalPoint monster : monsters) {
            this.option.add(new Option( monster.getName(), monster.getIntro()));
        }
        for (AbnormalPoint npc : npcs) {
            this.option.add(new Option( npc.getName(), npc.getIntro()));
        }
        this.option.add(new Option("pick", "Pick an item"));
        this.option.add(new Option("drop", "drop an item"));
        this.option.add(new Option("save", "Save game"));
        this.option.add(new Option("exit", "Exit game"));
        this.option.add(new Option("help", "get help"));

        this.tokenType.add(Token.Type.DETECT);
        this.tokenType.add(Token.Type.TAKE_ACTION);
        this.tokenType.add(Token.Type.DROP_ACTION);
        this.tokenType.add(Token.Type.SAVE);
        this.tokenType.add(Token.Type.EXIT);
        this.tokenType.add(Token.Type.DIRECTION);
        this.tokenType.add(Token.Type.DIRECTION_ACTION);

    }

    public NMOption(List<AbnormalPoint> monsters, List<AbnormalPoint> npcs) {
        buildMenu(monsters,npcs);
        showMenu();
    }

    @Override
    public void printOut() {
        System.out.print("Here are Monsters and NPCs. \n");
        System.out.println("Which one do you want to go first?");
        for (int i = 1; i < total.size()+1; i++) {
            Option op = option.get(i - 1);
            if (op.getDescription() != null) {
                System.out.println("[" + i + "] " + op.getCommand() + " : " + op.getDescription());
            } else {
                System.out.println("[" + i + "] " + op.getCommand());
            }
        }
        System.out.println("Or you can:");
        for (int i = total.size()+1; i < option.size()+1; i++) {
            Option op = option.get(i - 1);
            if (op.getDescription() != null) {
                System.out.println("[" + i + "] " + op.getCommand() + " : " + op.getDescription());
            } else {
                System.out.println("[" + i + "] " + op.getCommand());
            }
        }

    }

    public AbnormalPoint verifyInt(int n) {
            return this.total.get(n-1);
    }
}
