

import java.util.Random;

public class Game {
    static Deck.PlayerDeck pDeck; // Initialise the Wake Deck
    static Deck.EnemyDeck dDeck; // Initialise the Daikaiju Deck
    // current card(choice) in front of the user
    Boolean isInCombatSystem;
    Player player;
    






    public int drawCards(int[] hands, String playerCareer){
        pDeck = new Deck.PlayerDeck(hands);

        // draws a random tile from the available
        Random r = new Random();

        //TODO: get the card id from certain range (for specific player/enemy class)

        return pDeck.get(r.nextInt(pDeck.size())); // FIXME - Task 5a
    }
}
