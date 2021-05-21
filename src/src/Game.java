

import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import CommandParser.Token;
import Options.BasicOption;
import Options.Control;
import Options.PlayerOption;
import Options.StartOption;
import Player.Player;
import Player.Item;

import java.util.List;
import java.util.Scanner;

public class Game {
    private static Player player;
    public static CommandTokenizer cmdTok;
    public Parser parser;
    private List<Item> itemBook; // contain all possible items in the game(used for reference purposes)

    /**
     * This method wrap all the initialization and run them before
     * the user interface starts.
     */
    public void initialize() {
        itemBook = Item.initializeItemBook();
    }

    /**
     * This method is where user can start our game and it will display
     * user-interface in the console.
     * It loads various function in order to cleverly interact with user.
     *
     * @author Zihong Yuan, and modefied by Guanming Ou; Yitao Chen and Yixiang Yin.
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
                System.out.println("****Welcome to this world****");
                Control c = new Control(player);
                //TODO: He wants guide NPC here.
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
     *
     * @author Guanming Ou, and modefied by Zihong Yuan
     * @param player the current player.
     * @param c the menu control method.
     */
    public static void gameInteractionLoop(Player player, Control c) throws Exception {
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            if (Control.death)
                return;
            BasicOption current = c.currentOption;
            cmdTok = new CommandTokenizer(s.nextLine());
            Control.resetNPCs();
            if (current.chooseOp(cmdTok.current())) {
                cmdTok = current.convert(cmdTok);
                Token.Type typeOld = cmdTok.current().type();
                continueOn = new Parser(cmdTok, player).parseCommand();
                try {autoDetect(typeOld, c);}
                catch (Exception e) {
                    c.printRightOption();
                }
            } else {
                continueOn = new Parser(new CommandTokenizer("error"), player).parseCommand();
            }
        }
    }

    /**
     * This a second game loop, using just parser
     * It terminate when player input a 'exit | exit game' command
     *
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

    /**
     * The method can detect player's input and decide whether should
     * print out the option menu. This can reduce redundant information
     * for player.
     *
     * @author Zihong Yuan
     * @param typeOld User's input in form of token type.
     * @param c the menu control method.
     */
    public static void autoDetect(Token.Type typeOld, Control c) {
        if (cmdTok.hasNext()) {
            if (cmdTok.current().type()== Token.Type.DIRECTION || cmdTok.current().type()== Token.Type.RETREAT_ACTION
                    || cmdTok.current().type()== Token.Type.DETECT) {
                c.printRightOption();
            }
        } else {
            if (typeOld== Token.Type.DIRECTION || typeOld== Token.Type.RETREAT_ACTION
                    || typeOld== Token.Type.DETECT) {
                c.printRightOption();
            }
        }
    }
}