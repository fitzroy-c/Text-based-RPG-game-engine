package Card;

// Attributed by Guanming Ou
public class Cards {

    // variables for each cards
    CardClass cardClass;                // the class of this class
    Rarity rarity;
    String name;                        // the name of this card
    String description;                 // the description of this card
    Career.PlayerCareer career;         // enable for specific career

    // Attributes
    int baseCost;                       // cost of playing this card
    int effectiveRound;                 // how many round it is effective (poison, healing)
    int drawCard;                       // draw card from desk

    // To enemy
    int normalDamage;                   // normal attack damage (positive)
    int magicDamage;                    // magic damage (positive)
    int continuous;                     // (positive means healing, negative means damage)

    // To self
    int normalDamageSelf;               // normal attack damage (positive)
    int magicDamageSelf;                // magic damage (positive)
    int continuousSelf;                 // (positive means healing, negative means damage)


}
