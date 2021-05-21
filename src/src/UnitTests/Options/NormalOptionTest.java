package Options;

import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import Player.Player;

import java.util.Scanner;

/**
 * The class is used to test the general Option Menu.
 */
public class NormalOptionTest {
    private static Player player;
    public static CommandTokenizer cmdTok;

    public static void initialize() {
        player = new Player("test");
    }

    public static void main(String[] args) throws Exception {
        initialize();
        Scanner s=new Scanner(System.in);
        System.out.print("Welcome to the world!"+"\n");
        System.out.println("What would you like to do?");
        StartOption start = new StartOption();
        String input = s.next();
        start.printOut();
        while(true) {
            int i = start.getInput(input);
            switch (i){
                case 0:
                    System.out.println("what is your name?");
                    String name = s.next();
                    player.setName(name);
                    Control c = new Control(player);
                    gameInteractionLoop(player, c);
                    break;
                case 1:
                    System.out.println("Here is all the players available");
                    PlayerOption playerOption = new PlayerOption(player);
                    playerOption.printOut();
                    while (true) {
                    }
                case 2:
                    System.exit(0);
                default:
                    System.out.println("I don't understand this command");
            }
        }
    }

    public static void gameInteractionLoop(Player player, Control c) throws Exception {
        ///testing
        boolean continueOn = true;
        Scanner s = new Scanner(System.in);
        while (continueOn){
            BasicOption current = c.currentOption;
            String out = s.nextLine();
            System.out.println(out);
            cmdTok = new CommandTokenizer(out);
            player.checkNPCs();
            if (current.chooseOp(cmdTok.current())) {
                cmdTok = current.convert(cmdTok);
                continueOn = new Parser(cmdTok, player).parseCommand();
                c.printRightOption();
            } else {
                continueOn = new Parser(new CommandTokenizer("error"), player).parseCommand();
            }
        }
    }
}
