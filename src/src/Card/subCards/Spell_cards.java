package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Element;
import Card.Rarity;


/**
 * Deal burst damage to opponent with the cost of mana points
 * For element damages, it might deal increase or decrease damage to the enemy
 */
public class Spell_cards extends Cards {
    int magicDamage; // the value of element damage deal to opponent
    Element elementType; // declares which element damage type it is
    boolean tradeAllManaForDmg; // clear player's mana for every mana = 1 additional damage
    int hpCost;  // the card may cost HP instead of mana.

    /**
     * Constructor of Spell_cards extends from Card
     */
    public Spell_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                       String impactDescription, String career, int cardLevel, int maxCardLevel, int actionCost,
                       int manaCost, int magicDamage, Element elementType, boolean tradeAllManaForDmg, int hpCost) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, actionCost, manaCost);
        this.magicDamage = magicDamage;
        this.elementType = elementType;
        this.tradeAllManaForDmg = tradeAllManaForDmg;
        this.hpCost = hpCost;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public Element getElementType() {
        return elementType;
    }

    public void setElementType(Element elementType) {
        this.elementType = elementType;
    }

    public boolean isTradeAllManaForDmg() {
        return tradeAllManaForDmg;
    }

    public void setTradeAllManaForDmg(boolean tradeAllManaForDmg) {
        this.tradeAllManaForDmg = tradeAllManaForDmg;
    }

    public int getHpCost() {
        return hpCost;
    }

    public void setHpCost(int hpCost) {
        this.hpCost = hpCost;
    }
}
