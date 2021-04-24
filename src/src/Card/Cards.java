package Card;

// Attributed by Guanming Ou
public class Cards {

    // variables for each cards
    int cardID;
    CardClass cardClass;                // the class of this class
    Rarity rarity;
    String description;                 // the name and description of this card
    String impactDescription;           // the impact of this card after player plays
    Career.PlayerCareer career;         // enable for specific career
    int cardLevel;                      // upgradable cards
    int maxCardLevel;                   // maximum upgradable level

    // Attributes
    int movementCost;                   // cost of movement for playing this card
    int manaCost;                       // cost of mana for playing this card
}
