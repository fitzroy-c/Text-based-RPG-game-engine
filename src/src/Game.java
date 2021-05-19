

import AbnormalPoints.AbnormalPoint;
import AbnormalPoints.Monster;
import AbnormalPoints.MonsterAttributes;
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
import javafx.application.Platform;

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

}
//public class Game {
//    static DesignForFutureExtension.Deck.PlayerDeck pDeck; // Initialise the Wake DesignForFutureExtension.Deck
//    static DesignForFutureExtension.Deck.EnemyDeck dDeck; // Initialise the Daikaiju DesignForFutureExtension.Deck
//    // current card(choice) in front of the user
//    Boolean isInCombatSystem;
//    Player player;
//
//
//    public int drawCards(int[] hands, String playerCareer){
//        pDeck = new DesignForFutureExtension.Deck.PlayerDeck(hands);
//
//        // draws a random tile from the available
//        Random r = new Random();
//
//        //TODO: get the card id from certain range (for specific player/enemy class)
//
//        return pDeck.get(r.nextInt(pDeck.size())); // FIXME - Task 5a
//    }
//}
