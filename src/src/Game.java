

import AbnormalPoints.AbnormalPoint;
import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import Options.BasicOption;
import Options.Control;
import Options.StartOption;
import Player.Player;
import Player.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private static Player player;
    public static CommandTokenizer cmdTok;
    public Parser parser;
    private List<Item> itemBook; // contain all possible items in the game(used for reference purposes)

    /**
     * initial pre-game
     */
    public void initialize() {
        player = new Player("");
        itemBook = Item.initializeItems();
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

//        StartOption start = new StartOption(player);
//        start.printOut(start.option);
//        gameInteractionLoop(player);

        Scanner s=new Scanner(System.in);
        System.out.print("Welcome to the world!"+"\n");
        System.out.println("What would you like to do?");
        StartOption start = new StartOption();
        start.printOut();
        int i = start.getInput(s.next());
        switch (i){
            case 0:
                System.out.println("what is your name?");
                String name = s.next();
                player.setName(name);
                /// test
                //AbnormalPoint ab = new AbnormalPoint();
                Control c = new Control(player);
                ///
                gameInteractionLoop(player, c);
                break;
            case 1:
                System.out.println("here is all player available");
                while (true) {
                    ///test purpose
                }
                //break;
            case 2:
                System.exit(0);
            default:
                System.out.println("I don't understand this command");
        }
//        gameInteractionLoop(player);
    }


    /**
     * This continuously ask for player's input to keep the game running,
     * It terminate when player input a 'exit | exit game' command
     * @param player
     */
    public static void gameInteractionLoop(Player player, Control c) throws Exception {
        boolean continueOn = true;
//        MonsterAttributes mo = new MonsterAttributes();
//        BasicOption current = c.currentOption;
//        Monster monster = new Monster(mo, 12);
        Scanner s = new Scanner(System.in);
        while (continueOn){
            BasicOption current = c.currentOption;
            cmdTok = new CommandTokenizer(s.next());
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

    /**
     * This a second gameloop, using just parser
     * It terminate when player input a 'exit | exit game' command
     * @param player
     */
    public static void gameInteractionLoopParser(Player player) throws Exception {
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            cmdTok = new CommandTokenizer(s.next());
            continueOn = new Parser(cmdTok, player).parseCommand();
        }
    }
}