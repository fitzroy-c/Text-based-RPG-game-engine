package Options;

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.Monster;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void setCurrentOption(AbnormalPoint currentab) throws Exception {
        this.current = currentab;
        printRightOption();
    }

    public void printRightOption() throws Exception {
        if (isMonster && isNpc) {
            System.out.println("Control: case is NM");
            NMOption nm = new NMOption(monsters, npcs);
            nm.printOut();
            boolean b = true;
            while (b) {
                Scanner s = new Scanner(System.in);
                try {
                    int n = s.nextInt();
                    current = nm.verifyInt(n);
                    System.out.println("this is int "+n);
                    b = false;
                } catch (Exception e) {
                    System.out.println("I don't understand");
                    System.out.println("Please input number!");
                }
            }
        }
        System.out.println("Run print");
        switch (current.getString()) {
            case "NPC_TALK" :
            case "NPC_MERCHANT" :
                System.out.println("Control: case is NPC");
                NpcOption p = new NpcOption(current, player);
                this.currentOption = p;
                //this.option = currentOption.option;
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

    static public void setCurrentAb(AbnormalPoint a) {
        current = a;
    }

    static public void addMonsters(AbnormalPoint monster) {
        monsters.add(monster);
        isMonster = true;
    }

    static public void addNPCs(AbnormalPoint npc) {
        npcs.add(npc);
        isNpc = true;
    }

    static public void setDeath(boolean b) {
        death = b;
    }
}
