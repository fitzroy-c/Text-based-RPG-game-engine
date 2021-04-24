import Career.Careers;

import java.util.Random;

public class Game {
    static Deck.PlayerDeck pDeck; // Initialise the Wake Deck
    static Deck.EnemyDeck dDeck; // Initialise the Daikaiju Deck






    public int drawCards(int[] hands, Careers playerCareer){
        pDeck = new Deck.PlayerDeck(hands);

        // draws a random tile from the availables
        Random r = new Random();

        //TODO: get the card id from certain range (for specific player/enemy class)

        return pDeck.get(r.nextInt(pDeck.size())); // FIXME - Task 5a
    }
}
