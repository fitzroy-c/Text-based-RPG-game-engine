package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

/**
 * The attack card that deal damage to opponent with the cost of action points
 */
public class Normal_Attack_cards extends Cards {
    int normalDamage;                   // normal attack damage (positive)
    int pierceDamage;                   // damage that penetrate armor
    int continuous;                     // (positive means healing, negative means damage)
    int effectiveRound;                 // determines how many rounds 'continous' is effective
    int selfDamage;                     // damage to self when playing this card

    /**
     * Constructor of Normal_Attack_cards extends from Card
     */
    public Normal_Attack_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                        String impactDescription, String career, int cardLevel,
                        int maxCardLevel, int actionCost, int manaCost, int normalDamage,
                        int pierceDamage, int continuous, int effectiveRound, int selfDamage) {

        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, actionCost, manaCost);
        this.normalDamage = normalDamage;
        this.pierceDamage = pierceDamage;
        this.continuous = continuous;
        this.effectiveRound = effectiveRound;
        this.selfDamage = selfDamage;
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

    public int getEffectiveRound() { return effectiveRound; }

    public void setEffectiveRound(int effectiveRound) { this.effectiveRound = effectiveRound; }

    public int getSelfDamage() {
        return selfDamage;
    }

    public void setSelfDamage(int selfDamage) {
        this.selfDamage = selfDamage;
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
                ", actionCost:" + getMovementCost() +
                ", manaCost:" + getManaCost() +
                ", normalDamage:" + normalDamage +
                ", pierceDamage:" + pierceDamage +
                ", continuous:" + continuous +
                ", selfDamage:" + selfDamage +
                '}';
    }
}
