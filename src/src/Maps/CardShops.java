package Maps;

import Card.Cards;

import java.util.List;

/**
 * Shop sells cards that are in the "shop card pack"
 * - shop card pack contains some useful card for player and special cards
 * - Player need gold to buy these card
 */
public class CardShops extends Rooms{
    List<Goods> cardsForSell;

    public CardShops(String name, String description, List<Goods> cardsForSell) {
        super(name, description);
        this.cardsForSell = cardsForSell;
    }

    public List<Goods> getCardsForSell() {
        return cardsForSell;
    }

    public void setCardsForSell(List<Goods> cardsForSell) {
        this.cardsForSell = cardsForSell;
    }

    // TODO: Create method that randomly draw three goods from the cardsForSell



    // This is the tuple that bundle cards and value together
    class Goods<Cards, Integer> {
        public final Cards cards;
        public final Integer value;

        Goods(Cards cards, Integer value) {
            this.cards = cards;
            this.value = value;
        }

        public Cards getCards() {
            return cards;
        }

        public Integer getValue() {
            return value;
        }
    }
}
