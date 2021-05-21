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
 * before you use the function, you should test the 5 originate_data format first
 * using 5 related test.
 *
 */
public class autoTest {
    private static Player player;
    public static CommandTokenizer cmdTok;
    public Parser parser;
    private List<Item> itemBook; // contain all possible items in the game(used for reference purposes)

    public void initialize() {
        itemBook = Item.initializeItemBook();
    }

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
        commandList.add("retreat");
        commandList.add("detect");
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
            } else {
                continueOn = new Parser(new CommandTokenizer("error"), player).parseCommand();
            }
            if (player.getHP()<=0){
                continueOn = false;
            }
            if (player.getLevel()>=20){
                //System.out.println("You have won the trial");
                continueOn = false;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        int i = 0; // number of trial
        int j = 0; // number of win
        List<Long> result = new ArrayList<Long>();
        List<Long> resultWin = new ArrayList<Long>();
        while(i<100){
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
                if (player.getLevel()>=20){
                    System.out.println("You have won the trial");
                    continueOn = false;
                    j++;
                    long endTime1=System.currentTimeMillis();
                    resultWin.add(endTime1-startTime);
                }
            }
            long endTime=System.currentTimeMillis();
            result.add(endTime-startTime);
            i++;
        }

        System.out.println("Number of won auto test: "+j);
        System.out.println("The time usages are: \n");
        System.out.println(resultWin);
        double wonAvg =0.0;
        for(int m=0;m<j;m++){
            wonAvg+=resultWin.get(m);
        }
        wonAvg = wonAvg/j;
        System.out.println("Average time usage: "+wonAvg);
        System.out.println();


        System.out.println("Number of auto test: "+i);
        System.out.println("The time usages are: \n");
        System.out.println(result);
        double Avg =0.0;
        for(int n=0;n<i;n++){
            Avg+=result.get(n);
        }
        Avg = Avg/i;
        System.out.println("Average time usage: "+Avg);

    }
}
