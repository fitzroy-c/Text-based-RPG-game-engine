import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import CommandParser.Token;
import Options.BasicOption;
import Options.Control;
import Options.PlayerOption;
import Options.StartOption;
import Player.Player;
import Player.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * before you use the function, you should test the originate_data format first
 * TalkNPC, MerchantNPC, item?
 * // TODO fix the monster generate at wrong place issue
 */
public class autoTest {
    private static Player player;
    public static CommandTokenizer cmdTok;
    public Parser parser;
    private List<Item> itemBook; // contain all possible items in the game(used for reference purposes)

    public void initialize() {
        itemBook = Item.initializeItemBook();
    }
    /*
    public static void autoCaseTest() throws Exception {
        Game g = new Game();
        g.initialize();
        StartOption start = new StartOption();
        boolean continueOn = true;
        while(continueOn) {
            player = new Player("test player");
            Random random = new Random();
            int i = random.nextInt(3)+1;
            switch (i){
                case 0:
                    Control.death=false;
                    Control c = new Control(player);
                    gameInteractionLoop(player, c);
                    break;
                case 1:
                    //System.out.println("Here is all the players available");
                    PlayerOption playerOption = new PlayerOption(player);
                    playerOption.printOut();
                    boolean b = true;
                    while (b) {
                        if(playerOption.chooseOp(new Token("test player",null))) {
                            player = playerOption.player;
                            b = false;
                        }
                    }
                    Control.death=false;
                    Control cL = new Control(player);
                    gameInteractionLoop(player, cL);
                    break;
                case 2:
                    System.exit(0);
                default:
            }
            if (player.getHP()<=0){
                continueOn = false;
            }
            if (player.getLevel()>=10){
                continueOn = false;
            }
        }

    }

     */

    /**
     * This continuously ask for player's input to keep the game running,
     * It terminate when player input a 'exit | exit game' command
     * @param player
     */
    public static void gameInteractionLoop(Player player, Control c) throws Exception {
        boolean continueOn = true;
        List<String> commandList = new ArrayList<String>();
        commandList.add("go north");
        commandList.add("go south");
        commandList.add("go west");
        commandList.add("go east");
        commandList.add("detect");
        commandList.add("detect");
        commandList.add("attack");
        commandList.add("attack");
        commandList.add("detect");
        commandList.add("detect");
        commandList.add("attack");
        commandList.add("attack");
        Random random = new Random();

        int length = commandList.size();

        while (continueOn){
            if (Control.death)
                return;
            BasicOption current = c.currentOption;

            cmdTok = new CommandTokenizer(commandList.get(random.nextInt(length)));
            player.checkNPCs();
            if (current.chooseOp(cmdTok.current())) {
                cmdTok = current.convert(cmdTok);
                continueOn = new Parser(cmdTok, player).parseCommand();
//                System.out.println(cmdTok.current().token());
//                if (cmdTok.current()!=null && cmdTok.current().type()== Token.Type.DIRECTION) { //TODO: no very good
//                    autoDetect();
//                    c.printRightOption();
//                }
//                c.printRightOption();
            } else {
                continueOn = new Parser(new CommandTokenizer("error"), player).parseCommand();
            }
        }
    }

    /**
     * This a second gameloop, using just parser
     * It terminate when player input a 'exit | exit game' command
     * @param player
     */
    public static void gameInteractionLoopParser(Player player) throws Exception {
        boolean continueOn = true;
        //Scanner s = new Scanner(System.in);
        List<String> commandList = new ArrayList<String>();
        commandList.add("go north");
        commandList.add("go south");
        commandList.add("go west");
        commandList.add("go east");
        commandList.add("detect");
        commandList.add("detect");
        commandList.add("attack");
        commandList.add("attack");
        commandList.add("detect");
        commandList.add("detect");
        commandList.add("attack");
        commandList.add("attack");
        int length = commandList.size();
        Random random = new Random();

        while (continueOn){
            cmdTok = new CommandTokenizer(commandList.get(random.nextInt(length)));
            continueOn = new Parser(cmdTok, player).parseCommand();
        }
    }

    public static void main(String[] args) throws Exception {
        long startTime=System.currentTimeMillis();
        Game g = new Game();
        g.initialize();
        StartOption start = new StartOption();
        boolean continueOn = true;
        while(continueOn) {
            player = new Player("test player");
            //Random random = new Random();
            //int i = random.nextInt(3)+1;
            Control.death=false;
            Control c = new Control(player);
            gameInteractionLoop(player, c);
            if (player.getHP()<=0){
                continueOn = false;
            }
            if (player.getLevel()>=5){
                continueOn = false;
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
