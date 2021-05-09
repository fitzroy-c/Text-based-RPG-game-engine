package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

/**
 * Action cards give buffs to player/instance, and or deal damage while gain action points
 * or draw cards with the cost of action points.
 */
public class Action_cards extends Cards {
    int normalDamage;
    int pierceDamage;
    int gainActionPoint;  // gain action point instantly
    int drawCards;        // draw this number of card to player/monster's current hand
    // buffs to self
    float normalDmgBuff;      // multiply player's normal damage by effective round
    float magicDmgBuff;       // multiply player's magic damage by effective round
    float normalArmorMulBuff; // multiply player's armor by effective round
    float magicArmorMulBuff;  // multiply player's armor by effective round

    // de-buff to enemy
    float normalDmgDeBuff;      // divide monster's normal damage by effective round
    float magicDmgDeBuff;       // divide monster's magic damage by effective round
    float normalArmorMulDeBuff; // divide monster's armor by effective round
    float magicArmorMulDeBuff;  // divide monster's armor by effective round

    int   effectiveRound;     // determines how many rounds this card (buffs) is effective


    /**
     * Constructor of Action_cards extends from Card
     */
    public Action_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                        String impactDescription, String career, int cardLevel, int maxCardLevel, int actionCost,
                        int manaCost, int normalDamage, int pierceDamage, int gainActionPoint, int drawCards,
                        float normalDmgBuff, float magicDmgBuff, float normalArmorMulBuff, float magicArmorMulBuff,
                        float normalDmgDeBuff, float magicDmgDeBuff, float normalArmorMulDeBuff,
                        float magicArmorMulDeBuff, int effectiveRound) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, actionCost, manaCost);
        this.normalDamage = normalDamage;
        this.pierceDamage = pierceDamage;
        this.gainActionPoint = gainActionPoint;
        this.drawCards = drawCards;
        this.normalDmgBuff = normalDmgBuff;
        this.magicDmgBuff = magicDmgBuff;
        this.normalArmorMulBuff = normalArmorMulBuff;
        this.magicArmorMulBuff = magicArmorMulBuff;
        this.normalDmgDeBuff = normalDmgDeBuff;
        this.magicDmgDeBuff = magicDmgDeBuff;
        this.normalArmorMulDeBuff = normalArmorMulDeBuff;
        this.magicArmorMulDeBuff = magicArmorMulDeBuff;
        this.effectiveRound = effectiveRound;
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

    public int getGainActionPoint() {
        return gainActionPoint;
    }

    public void setGainActionPoint(int gainActionPoint) {
        this.gainActionPoint = gainActionPoint;
    }

    public int getDrawCards() {
        return drawCards;
    }

    public void setDrawCards(int drawCards) {
        this.drawCards = drawCards;
    }

    public float getNormalDmgBuff() {
        return normalDmgBuff;
    }

    public void setNormalDmgBuff(float normalDmgBuff) {
        this.normalDmgBuff = normalDmgBuff;
    }

    public float getMagicDmgBuff() {
        return magicDmgBuff;
    }

    public void setMagicDmgBuff(float magicDmgBuff) {
        this.magicDmgBuff = magicDmgBuff;
    }

    public float getNormalArmorMulBuff() {
        return normalArmorMulBuff;
    }

    public void setNormalArmorMulBuff(float normalArmorMulBuff) {
        this.normalArmorMulBuff = normalArmorMulBuff;
    }

    public float getMagicArmorMulBuff() {
        return magicArmorMulBuff;
    }

    public void setMagicArmorMulBuff(float magicArmorMulBuff) {
        this.magicArmorMulBuff = magicArmorMulBuff;
    }

    public float getNormalDmgDeBuff() {
        return normalDmgDeBuff;
    }

    public void setNormalDmgDeBuff(float normalDmgDeBuff) {
        this.normalDmgDeBuff = normalDmgDeBuff;
    }

    public float getMagicDmgDeBuff() {
        return magicDmgDeBuff;
    }

    public void setMagicDmgDeBuff(float magicDmgDeBuff) {
        this.magicDmgDeBuff = magicDmgDeBuff;
    }

    public float getNormalArmorMulDeBuff() {
        return normalArmorMulDeBuff;
    }

    public void setNormalArmorMulDeBuff(float normalArmorMulDeBuff) {
        this.normalArmorMulDeBuff = normalArmorMulDeBuff;
    }

    public float getMagicArmorMulDeBuff() {
        return magicArmorMulDeBuff;
    }

    public void setMagicArmorMulDeBuff(float magicArmorMulDeBuff) {
        this.magicArmorMulDeBuff = magicArmorMulDeBuff;
    }

    public int getEffectiveRound() {
        return effectiveRound;
    }

    public void setEffectiveRound(int effectiveRound) {
        this.effectiveRound = effectiveRound;
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

