package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;
import Career.PlayerCareer;

public class Equipment_cards extends Cards {
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
    public Equipment_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description, String impactDescription, PlayerCareer career, int cardLevel, int maxCardLevel, int movementCost, int manaCost) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, movementCost, manaCost);
    }
}
