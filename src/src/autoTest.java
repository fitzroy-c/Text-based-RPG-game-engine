import AbnormalPoints.AbnormalPoint;
import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import CommandParser.Token;
import Options.BasicOption;
import Options.Control;
import Options.PlayerOption;
import Options.StartOption;
import Player.Bag;
import Player.Player;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import navigation.Coordinate;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
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
    //private List<Item> itemBook; // contain all possible items in the game(used for reference purposes)

    /**
     * initial pre-game
     */
    /*
    public void initialize() {
        itemBook = Item.initializeItemBook();
    }

/*    Test
        public static void loaditem() {
        File item = new File("json_files/Item2.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Item itemNew = createItem();
        String json = gson.toJson(itemNew);

        try (FileWriter writer = new FileWriter("json_files/Item2.json")) {
            gson.toJson(itemNew,writer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Item createItem() {
        HashMap<String,Integer> map = new HashMap();
        map.put("lala",12);
        Item item = new Item("c1", "test1", "fire", "lalala", map);
        return item;
    }*/



    /**
     * Is this our main game?
     *
     * @param args
     */

    public static void main(String[] args) throws Exception {
        Game g = new Game();
        g.initialize();
        Scanner s=new Scanner(System.in);
        System.out.print("Welcome to the world!"+"\n");
        System.out.println("What would you like to do?");
        StartOption start = new StartOption();
        start.printOut();
        while(true) {
            player = new Player("");
            int i = start.getInput(s.next());
            switch (i){
                case 0:
                    Control.death=false;
                    System.out.println("what is your name?");
                    String name = s.next();
                    player.setName(name);
                    Control c = new Control(player);
                    gameInteractionLoop(player, c);
//                gameInteractionLoopParser(player);
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


    /**
     * This continuously ask for player's input to keep the game running,
     * It terminate when player input a 'exit | exit game' command
     * @param player
     */
    public static void gameInteractionLoop(Player player, Control c) throws Exception {
        System.out.println(player.checkMonster());
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            if (Control.death)
                return;
            BasicOption current = c.currentOption;
            cmdTok = new CommandTokenizer(s.nextLine());
            player.checkNPCs();
            if (current.chooseOp(cmdTok.current())) {
                cmdTok = current.convert(cmdTok);
//                if (cmdTok.current()!=null && cmdTok.current().type()== Token.Type.DIRECTION) {
//                    autoDetect();
//                    c.printRightOption();
//                }
//                System.out.println(cmdTok.current().token());
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
        Scanner s = new Scanner(System.in);
        while (continueOn){
            cmdTok = new CommandTokenizer(s.nextLine());
            continueOn = new Parser(cmdTok, player).parseCommand();
        }
    }
}
