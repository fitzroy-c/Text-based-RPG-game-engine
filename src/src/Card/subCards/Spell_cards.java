package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;
import Career.PlayerCareer;

public class Spell_cards extends Cards {
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
    public Spell_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description, String impactDescription, PlayerCareer career, int cardLevel, int maxCardLevel, int movementCost, int manaCost) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, movementCost, manaCost);
    }

//    // special card
//    int effectiveRound;                 // how many round it is effective (poison, healing)
//    int drawCard;                       // draw card from desk
//
//    // To enemy
//    int normalDamage;                   // normal attack damage (positive)
//    int magicDamage;                    // magic damage (positive)
//    int continuous;                     // (positive means healing, negative means damage)
//
//    // To self
//    int normalDamageSelf;               // normal attack damage (positive)
//    int magicDamageSelf;                // magic damage (positive)
//    int continuousSelf;                 // (positive means healing, negative means damage)
}
