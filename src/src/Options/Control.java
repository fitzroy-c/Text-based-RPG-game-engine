package Options;

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.NPC_MERCHANT;
import AbnormalPoints.NPC_TALK;
import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import CommandParser.Token;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *The class can switch between different option
 * menus at different situations.
 *
 * @author Zihong Yuan
 */
public class Control extends BasicOption {
    static AbnormalPoint current;
    Player player;
    public BasicOption currentOption;
    static List<AbnormalPoint> monsters = new ArrayList<>();
    static List<AbnormalPoint> npcs = new ArrayList<>();
    static boolean isMonster = false;
    static boolean isNpc = false;
    static public boolean death = false;

    public Control(Player player) throws Exception {
        current = new AbnormalPoint();
        this.player = player;
        resetNPCs();
        resetMonsters();
        printRightOption();
    }

    /**
     * The method can set the current AbnormalPoint.
     *
     * @author Zihong Yuan
     * @param currentAP the AbnormalPoint input
     */
    public void setCurrentOption(AbnormalPoint currentAP) {
        this.current = currentAP;
        printRightOption();
    }

    static public void resetCurrent() {
        AbnormalPoint ab = new AbnormalPoint();
        current = ab;
    }

    static public boolean isNPC() {
        return (current.getClass()== NPC_MERCHANT.class || current.getClass()== NPC_TALK.class);
    }

    /**
     * The method can print out correct option menu in the console.
     * It can choose from different Monsters and NPCs.
     *
     * @author Zihong Yuan
     */
    public void printRightOption() { //TODO make it can go back to the NMOption when use BACK
        if (player.getHP() <= 0) {
            System.out.println("YOU DIE");
            System.out.println("What would you like to do?");
            StartOption start = new StartOption();
            start.printOut();
            Control.death = true;
            return;
        }
        resetCurrent();
        if (isMonster || isNpc) {
            System.out.print("\n");
            NMOption nm = new NMOption(monsters, npcs);
            nm.printOut();
            boolean b = true;
            while (b) {
                Scanner s = new Scanner(System.in);
                String input = s.nextLine();
                CommandTokenizer cmd = nm.convert(new CommandTokenizer(input));
                try {
                    int n = Integer.parseInt(input);
                    current = nm.verifyInt(n);
                    b = false;
                } catch (Exception e) {
                    if (nm.optionInterface.containsKey(input)) {
                        for (AbnormalPoint abnormalPoint : nm.total) {
                            if (abnormalPoint.getName().equals(nm.optionInterface.get(input)))
                                current = abnormalPoint;
                        }
                        b = false;
                    } else {
                        if (cmd.current().type()== Token.Type.DIRECTION) {
                            b = false;// no valid choice
                            resetNPCs();
                            resetMonsters();
                        }
                        if (! new Parser(cmd, player).parseCommand()) {
                            System.out.println("Please choose correct command");
                        }
                    }
                }
//                    b = false;
//                } else {
//                    System.out.println("Please choose correct one");
//                }
            }
        }
        switch (current.getString()) {
            case "NPC_TALK" :
            case "NPC_MERCHANT" :
                System.out.print("\n");
                NpcOption p = new NpcOption(current, player);
                this.currentOption = p;
                p.printOut();
                break;
            case "MONSTER" :
                System.out.print("\n");
                BattleOption b = new BattleOption(current, player);
                this.currentOption = b;
                b.printOut();
                break;
            default:
                System.out.print("\n");
                NormalOption n = new NormalOption(player);
                this.currentOption = n;
                n.printOut();
                break;
        }
    }

    /**
     * The method can set current AbnormalPoint.
     *
     * @author Zihong Yuan
     * @param ab wanted AbnormalPoint
     */
    static public void setCurrentAb(AbnormalPoint ab) {
        current = ab;
    }

    /**
     * The method can add monster into monsters list.
     *
     * @author Zihong Yuan
     * @param monster
     */
    static public void addMonsters(AbnormalPoint monster) {
        monsters.add(monster);
        isMonster = true;
    }

    static public void resetMonsters() {
        monsters = new ArrayList<>();
        isMonster = false;
    }

    /**
     * The method can add NPC into npcs list.
     *
     * @author Zihong Yuan
     * @param npc
     */
    static public void addNPCs(AbnormalPoint npc) {
        if (npc==null)
            return;
        npcs.add(npc);
        isNpc = true;
    }

    static public void resetNPCs() {
        npcs = new ArrayList<>();
        isNpc = false;
    }

    /**
     * The method can set the death to wanted boolean.
     *
     * @author Zihong Yuan
     * @param b
     */
    static public void setDeath(boolean b) {
        death = b;
    }
}
