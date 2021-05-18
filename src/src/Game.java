

import AbnormalPoints.AbnormalPoint;
import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import Options.BasicOption;
import Options.Control;
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
import java.util.Map;
import java.util.Scanner;

public class Game {
    private static Player player;
    public static CommandTokenizer cmdTok;
    public Parser parser;

    /**
     * initial pre-game
     */
    public static void initialize() {
        player = new Player("");
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
        initialize();

//        StartOption start = new StartOption(player);
//        start.printOut(start.option);
//        gameInteractionLoop(player);

        Scanner s=new Scanner(System.in);
        System.out.print("Welcome to the world!"+"\n");
        System.out.println("What's your name?");
        String name = s.next();
        player.setName(name);
        /// test
        AbnormalPoint ab = new AbnormalPoint();
        Control c = new Control(ab ,player);
        ///
        gameInteractionLoop(player, c.currentOption);
//        gameInteractionLoop(player);
    }


    /**
     * This continuously ask for player's input to keep the game running,
     * It terminate when player input a 'exit | exit game' command
     * @param player
     */
    public static void gameInteractionLoop(Player player, BasicOption option) throws Exception {
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            cmdTok = new CommandTokenizer(s.next());
            if (option.chooseOp(option.option, cmdTok.current())) {
                continueOn = new Parser(cmdTok, player).parseCommand();
            } else {
                continueOn = new Parser(new CommandTokenizer("error"), player).parseCommand();
            }
//            continueOn = new Parser(cmdTok, player).parseCommand();
        }
    }

}
//public class Game {
//    static Deck.PlayerDeck pDeck; // Initialise the Wake Deck
//    static Deck.EnemyDeck dDeck; // Initialise the Daikaiju Deck
//    // current card(choice) in front of the user
//    Boolean isInCombatSystem;
//    Player player;
//
//
//    public int drawCards(int[] hands, String playerCareer){
//        pDeck = new Deck.PlayerDeck(hands);
//
//        // draws a random tile from the available
//        Random r = new Random();
//
//        //TODO: get the card id from certain range (for specific player/enemy class)
//
//        return pDeck.get(r.nextInt(pDeck.size())); // FIXME - Task 5a
//    }
//}
