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
        return "Action_cards{" +
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




//hand: [0,1,2]    size 5
//Deck: [0,1,2,3,4,5];
//
//hand: [0,1,2]    size 5;
//Deck: [0,1,2,3,4,5]   ;
//
//arraylist: []
//arraylist.add(3);
//
//randome.toInt(0,5) = 3       ;
//arraylist.add(3);
//arraylist.remove(2);
//
//Card 1   id 1   Rarity Normal
//Card 2   id 2   Rarity Leng
//
//
//randome.toInt(cardset.length)
//
//Normal Card set {1,1,1,1,1,1,1,1,1,2}
//Knight Card set {2,2,2,3,4}

