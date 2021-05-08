package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

public class Special_cards extends Cards {
    /**
     * Constructor of a card (will have extends in subCards)
     *
     * @param cardID
     * @param cardClass
     * @param rarity
     * @param name
     * @param description
     * @param impactDescription
     * @param career
     * @param cardLevel
     * @param maxCardLevel
     * @param movementCost
     * @param manaCost
     */
    public Special_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description, String impactDescription, String career, int cardLevel, int maxCardLevel, int movementCost, int manaCost) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, movementCost, manaCost);
    }
}
