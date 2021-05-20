package Options;

import AbnormalPoints.*;
import Card.Element;
import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import CommandParser.Token;
import Player.Player;

import java.util.Scanner;

public class NMOptionTest {
    private static Player player;
    public static CommandTokenizer cmdTok;

    static private void initialize() {
//        player = new Player("test");
        MonsterAttributes ma = new MonsterAttributes("Monster","Monster",100,10,12,12,12,12,
                12,12,12,12,12, Element.Normal);
        Monster monster = new Monster(ma, 5);
        player.getPlace().addAbnormalPoint(monster);

        NPC_TALK npcTalk = new NPC_TALK("npcTalk","npcTalk", 12, 12, 12, 12, 12, 12, 12, Element.Normal, new DialogTree(),
                12,12,12,null);
        player.getPlace().addAbnormalPoint(npcTalk);

        NPC_MERCHANT npcMerchant = new NPC_MERCHANT("npcMerchant","npcMerchant",12,12,12,12,12,12,12,null);
        player.getPlace().addAbnormalPoint(npcMerchant);
    }

    public static void main(String[] args) throws Exception {
//        initialize();
        Scanner s=new Scanner(System.in);
        System.out.print("Welcome to the world!"+"\n");
        System.out.println("What would you like to do?");
        StartOption start = new StartOption();
        start.printOut();
        while(true) {
            player = new Player("");
            initialize();
            int i = start.getInput(s.next());
            switch (i){
                case 0:
                    Control.death=false;
                    System.out.println("what is your name?");
                    String name = s.next();
                    player.setName(name);
                    /// test
                    //AbnormalPoint ab = new AbnormalPoint();
                    Control c = new Control(player);
                    gameInteractionLoop(player, c);
//                    gameInteractionLoopParser(player);
                    break;
                case 1:
                    System.out.println("Here is all the players available");
                    PlayerOption playerOption = new PlayerOption(player);
                    playerOption.printOut();
                    boolean b = true;
                    while (b) {
                        if(playerOption.chooseOp(new Token(s.next(),null))) {
                            player = playerOption.player;
                            b = false;
                        } else {
                            System.out.println("I don't understand this command");
                        }
                    }
                    Control.death=false;
                    Control cL = new Control(player);
                    gameInteractionLoop(player, cL);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("I don't understand this command");
            }
        }
    }

    public static void gameInteractionLoop(Player player, Control c) throws Exception {
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            if (Control.death)
                return;
            BasicOption current = c.currentOption;
            String out = s.nextLine();
            System.out.println(out);
            cmdTok = new CommandTokenizer(out);
            player.checkNPCs();
            if (current.chooseOp(cmdTok.current())) {
                cmdTok = current.convert(cmdTok);
                continueOn = new Parser(cmdTok, player).parseCommand();
                //c.setCurrentOption(monster);
                //System.out.println("change Option");
                c.printRightOption();
            } else {
                continueOn = new Parser(new CommandTokenizer("error"), player).parseCommand();
            }
//            continueOn = new Parser(cmdTok, player).parseCommand();
        }
    }

    public static void gameInteractionLoopParser(Player player) throws Exception {
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            cmdTok = new CommandTokenizer(s.nextLine());
            continueOn = new Parser(cmdTok, player).parseCommand();
        }
    }

}
