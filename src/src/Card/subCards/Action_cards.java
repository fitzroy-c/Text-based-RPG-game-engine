package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

public class Action_cards extends Cards {
    int normalDamage;                   // normal attack damage (positive)
    int pierceDamage;                   // damage that penetrate armor
    int continuous;                     // (positive means healing, negative means damage)
    int selfDamage;                     // damage to self when playing this card
    int drawCard;                       // draw card from desk

    /**
     * Constructor of Action_cards extends from Card
     */
    public Action_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                        String impactDescription, Career.PlayerCareer career, int cardLevel,
                        int maxCardLevel, int movementCost, int manaCost,
                        int normalDamage, int pierceDamage, int continuous, int selfDamage, int drawCard) {

        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, movementCost, manaCost);
        this.normalDamage = normalDamage;
        this.pierceDamage = pierceDamage;
        this.continuous = continuous;
        this.selfDamage = selfDamage;
        this.drawCard = drawCard;
    }

    public int getContinuous() { return continuous; }

    public int getDrawCard() { return drawCard; }

    public int getNormalDamage() { return normalDamage; }

    public int getPierceDamage() { return pierceDamage; }

    public int getSelfDamage() { return selfDamage; }




}
