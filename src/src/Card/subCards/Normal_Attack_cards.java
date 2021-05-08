package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

public class Normal_Attack_cards extends Cards {
    int normalDamage;                   // normal attack damage (positive)
    int pierceDamage;                   // damage that penetrate armor
    int continuous;                     // (positive means healing, negative means damage)
    int selfDamage;                     // damage to self when playing this card
    int drawCard;                       // draw card from desk

    /**
     * Constructor of Normal_Attack_cards extends from Card
     */
    public Normal_Attack_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                        String impactDescription, String career, int cardLevel,
                        int maxCardLevel, int movementCost, int manaCost,
                        int normalDamage, int pierceDamage, int continuous, int selfDamage, int drawCard) {

        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, movementCost, manaCost);
        this.normalDamage = normalDamage;
        this.pierceDamage = pierceDamage;
        this.continuous = continuous;
        this.selfDamage = selfDamage;
        this.drawCard = drawCard;
    }

    public int getNormalDamage() {
        return normalDamage;
    }

    public void setNormalDamage(int normalDamage) {
        this.normalDamage = normalDamage;
    }

    public int getPierceDamage() {
        return pierceDamage;
    }

    public void setPierceDamage(int pierceDamage) {
        this.pierceDamage = pierceDamage;
    }

    public int getContinuous() {
        return continuous;
    }

    public void setContinuous(int continuous) {
        this.continuous = continuous;
    }

    public int getSelfDamage() {
        return selfDamage;
    }

    public void setSelfDamage(int selfDamage) {
        this.selfDamage = selfDamage;
    }

    public int getDrawCard() {
        return drawCard;
    }

    public void setDrawCard(int drawCard) {
        this.drawCard = drawCard;
    }

    @Override
    public String toString() {
        return "Normal_Attack_cards{" +
                "  cardID:" + getCardID() +
                ", cardClass:" + getCardClass() +
                ", rarity:" + getRarity() +
                ", name:" + getName() +
                ", description:" + getDescription() +
                ", impactDescription:" + getImpactDescription() +
                ", career:" + getCareer() +
                ", cardLevel:" + getCardLevel() +
                ", maxCardLevel:" + getMaxCardLevel() +
                ", movementCost:" + getMovementCost() +
                ", manaCost:" + getManaCost() +
                ", normalDamage:" + normalDamage +
                ", pierceDamage:" + pierceDamage +
                ", continuous:" + continuous +
                ", selfDamage:" + selfDamage +
                ", drawCard:" + drawCard +
                '}';
    }
}
