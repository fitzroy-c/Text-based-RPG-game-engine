package Card;

import Career.PlayerCareer;

// Attributed by Guanming Ou
public class Cards {

    // variables for each cards
    int cardID;
    CardClass cardClass;                // the class of this class
    Rarity rarity;
    String name;
    String description;                 // the name and description of this card
    String impactDescription;           // the impact of this card after player plays
    Career.PlayerCareer career;         // enable for specific career
    int cardLevel;                      // upgradable cards
    int maxCardLevel;                   // maximum upgradable level

    // Attributes
    int movementCost;                   // cost of movement for playing this card
    int manaCost;                       // cost of mana for playing this card

    /**
     * Constructor of a card (will have extends in subCards)
     */
    public Cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description, String impactDescription, PlayerCareer career, int cardLevel, int maxCardLevel, int movementCost, int manaCost) {
        this.cardID = cardID;
        this.cardClass = cardClass;
        this.rarity = rarity;
        this.name = name;
        this.description = description;
        this.impactDescription = impactDescription;
        this.career = career;
        this.cardLevel = cardLevel;
        this.maxCardLevel = maxCardLevel;
        this.movementCost = movementCost;
        this.manaCost = manaCost;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public CardClass getCardClass() {
        return cardClass;
    }

    public void setCardClass(CardClass cardClass) {
        this.cardClass = cardClass;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpactDescription() {
        return impactDescription;
    }

    public void setImpactDescription(String impactDescription) {
        this.impactDescription = impactDescription;
    }

    public PlayerCareer getCareer() {
        return career;
    }

    public void setCareer(PlayerCareer career) {
        this.career = career;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public int getMaxCardLevel() {
        return maxCardLevel;
    }

    public void setMaxCardLevel(int maxCardLevel) {
        this.maxCardLevel = maxCardLevel;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public void setMovementCost(int movementCost) {
        this.movementCost = movementCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
