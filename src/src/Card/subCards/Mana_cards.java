package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

/**
 * Gain mana by playing this card, either continously gain or instant gain mana.
 * Main purpose as to recover mana for spell cards.
 * @author Guanming Ou
 */
public class Mana_cards extends Cards {
    int instantGainMana;
    int continousGainMana;
    int effectiveRound;

    /**
     * Constructor of Mana_cards extends from Card
     */
    public Mana_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                      String impactDescription, String career, int cardLevel, int maxCardLevel,
                      int actionCost, int manaCost, int instantGainMana, int continousGainMana, int effectiveRound) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, actionCost, manaCost);
        this.instantGainMana = instantGainMana;
        this.continousGainMana = continousGainMana;
        this.effectiveRound = effectiveRound;
    }

    public int getInstantGainMana() {
        return instantGainMana;
    }

    public void setInstantGainMana(int instantGainMana) {
        this.instantGainMana = instantGainMana;
    }

    public int getContinousGainMana() {
        return continousGainMana;
    }

    public void setContinousGainMana(int continousGainMana) {
        this.continousGainMana = continousGainMana;
    }

    public int getEffectiveRound() {
        return effectiveRound;
    }

    public void setEffectiveRound(int effectiveRound) {
        this.effectiveRound = effectiveRound;
    }
}
