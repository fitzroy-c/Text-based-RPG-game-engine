package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

/**
 * The equipment that player can play to wear it during battle, enhance the player's attribute.
 */
public class Equipment_cards extends Cards {
    int armorGain; // increase the normalArmor of the character
    int magicArmorGain;  // increase the magicArmor of the character
    int normalAtkBoost; // increase the value of normal attack damage for normal attack card you play
    int magicAtkBoost; // increase the value of magical damage (add to the element damage depends on type)
    int actionPointGainBoost; // increase the value of action point you gain each round
    int manaPointGainBoost; // increase the value of mana you gain

    /**
     * Constructor of Equipment_cards extends from Card
     */
    public Equipment_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                           String impactDescription, String career, int cardLevel, int maxCardLevel,
                           int actionCost, int manaCost, int armorGain, int magicArmorGain, int normalAtkBoost,
                           int magicAtkBoost, int actionPointGainBoost, int manaPointGainBoost) {
        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, actionCost, manaCost);
        this.armorGain = armorGain;
        this.magicArmorGain = magicArmorGain;
        this.normalAtkBoost = normalAtkBoost;
        this.magicAtkBoost = magicAtkBoost;
        this.actionPointGainBoost = actionPointGainBoost;
        this.manaPointGainBoost = manaPointGainBoost;
    }

    public int getArmorGain() {
        return armorGain;
    }

    public void setArmorGain(int armorGain) {
        this.armorGain = armorGain;
    }

    public int getMagicArmorGain() {
        return magicArmorGain;
    }

    public void setMagicArmorGain(int magicArmorGain) {
        this.magicArmorGain = magicArmorGain;
    }

    public int getNormalAtkBoost() {
        return normalAtkBoost;
    }

    public void setNormalAtkBoost(int normalAtkBoost) {
        this.normalAtkBoost = normalAtkBoost;
    }

    public int getMagicAtkBoost() {
        return magicAtkBoost;
    }

    public void setMagicAtkBoost(int magicAtkBoost) {
        this.magicAtkBoost = magicAtkBoost;
    }

    public int getActionPointGainBoost() {
        return actionPointGainBoost;
    }

    public void setActionPointGainBoost(int actionPointGainBoost) {
        this.actionPointGainBoost = actionPointGainBoost;
    }

    public int getManaPointGainBoost() {
        return manaPointGainBoost;
    }

    public void setManaPointGainBoost(int manaPointGainBoost) {
        this.manaPointGainBoost = manaPointGainBoost;
    }
}
