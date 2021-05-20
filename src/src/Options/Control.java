package Options;

import AbnormalPoints.AbnormalPoint;
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

    /**
     * The method can print out correct option menu in the console.
     * It can choose from different Monsters and NPCs.
     *
     * @author Zihong Yuan
     */
    public void printRightOption() { //TODO make it can go back to the NMOption when use BACK
        if (isMonster && isNpc) {
            System.out.println("Control: case is NM");
            NMOption nm = new NMOption(monsters, npcs);
            nm.printOut();
            boolean b = true;
            while (b) {
                Scanner s = new Scanner(System.in);
                String input = s.nextLine();
                if (nm.chooseOp(new Token(input, null))) {
//                    CommandTokenizer cmd = nm.convert(new CommandTokenizer(input));
//                    System.out.println(cmd.current().token());

                    try {
                        int n = Integer.parseInt(input);
                        current = nm.verifyInt(n);
                        b = false;
                    } catch (Exception e) {
                        CommandTokenizer cmd = nm.convert(new CommandTokenizer(input));
                        if (! new Parser(cmd, player).parseCommand()) {
                            System.out.println("I don't understand");
                            System.out.println("Please choose correct one");
                        }


//                        cmdTok = current.convert(cmdTok);
//                        continueOn = new Parser(current.convert(cmdTok), player).parseCommand();
//
//
//                        System.out.println("I don't understand");
//                        System.out.println("Please input number!");
                    }
                } else {
                    System.out.println("I don't understand");
                    System.out.println("Please choose correct one");
                }
            }
        }
        switch (current.getString()) {
            case "NPC_TALK" :
            case "NPC_MERCHANT" :
                System.out.println("Control: case is NPC");
                NpcOption p = new NpcOption(current, player);
                this.currentOption = p;
                p.printOut();
                break;
            case "MONSTER" :
                System.out.println("Control: case is Monster");
                BattleOption b = new BattleOption(current, player);
                this.currentOption = b;
                b.printOut();
                break;
            default:
                System.out.println("Control: case is normal");
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

    /**
     * The method can add NPC into npcs list.
     *
     * @author Zihong Yuan
     * @param npc
     */
    static public void addNPCs(AbnormalPoint npc) {
        npcs.add(npc);
        isNpc = true;
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
