package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Element;
import Card.Rarity;

/**
 * Prayer cards require rounds to take effective, it might heal, deal damage or draw cards
 */
public class Prayer_cards extends Cards {
    int roundToBeEffective; // round for this prayer card to take effective
    int magicDamage; // the value of element damage deal to opponent
    int continuousMagicDmg; // deal damage for every effectiveRound
    Element elementType; // declares which element damage type it is
    int heal; // heal instantly
    int continuousHeal; // heal self for every effectiveRound
    int effectiveRound; // rounds that continuous damage/healing is effective
    int drawCard; // draw number of cards to player's hands

    /**
     * Constructor of Prayer_cards extends from Card
     */
    public Prayer_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                        String impactDescription, String career, int cardLevel, int maxCardLevel, int actionCost,
                        int manaCost, int roundToBeEffective, int magicDamage, int continuousMagicDmg,
                        Element elementType, int heal, int continuousHeal, int drawCard, int effectiveRound) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, actionCost, manaCost);
        this.roundToBeEffective = roundToBeEffective;
        this.magicDamage = magicDamage;
        this.continuousMagicDmg = continuousMagicDmg;
        this.elementType = elementType;
        this.heal = heal;
        this.continuousHeal = continuousHeal;
        this.drawCard = drawCard;
        this.effectiveRound = effectiveRound;
    }

    public int getRoundToBeEffective() {
        return roundToBeEffective;
    }

    public void setRoundToBeEffective(int roundToBeEffective) {
        this.roundToBeEffective = roundToBeEffective;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getContinuousMagicDmg() {
        return continuousMagicDmg;
    }

    public void setContinuousMagicDmg(int continuousMagicDmg) {
        this.continuousMagicDmg = continuousMagicDmg;
    }

    public Element getElementType() {
        return elementType;
    }

    public void setElementType(Element elementType) {
        this.elementType = elementType;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getContinuousHeal() {
        return continuousHeal;
    }

    public void setContinuousHeal(int continuousHeal) {
        this.continuousHeal = continuousHeal;
    }

    public int getDrawCard() {
        return drawCard;
    }

    public void setDrawCard(int drawCard) {
        this.drawCard = drawCard;
    }

    public int getEffectiveRound() {
        return effectiveRound;
    }

    public void setEffectiveRound(int effectiveRound) {
        this.effectiveRound = effectiveRound;
    }
}
