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

    public CardClass getCardClass() { return cardClass; }

    public int getCardID() { return cardID; }

    public int getCardLevel() { return cardLevel; }

    public int getManaCost() { return manaCost; }

    public int getMaxCardLevel() { return maxCardLevel; }

    public int getMovementCost() { return movementCost; }

    public PlayerCareer getCareer() { return career; }

    public Rarity getRarity() { return rarity; }

    public String getDescription() { return description; }

    public String getImpactDescription() { return impactDescription; }
}
