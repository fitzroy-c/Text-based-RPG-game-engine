

import Player.Player;

import java.util.Scanner;

public class Game {
    private static Player player;

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.print("Welcome to the world!"+"\n");
        System.out.println("What's your name?");
        String name = s.next();
        player.setName(name);
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
