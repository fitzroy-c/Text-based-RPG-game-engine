package Card;

// Attributed by Guanming Ou
public enum CardClass {
    Normal_Attack,      // no cost restriction and their main goal is to cause damage
    Action,             // card draw, damage boost, halting enemy movements or generation of resources.
    Mana,               // no cost restriction and their main goal is to accumulate mana
    Spell,              // restricted by their mana cost, magical damage is proportional to its cost
    Counter,            // impact on enemy action and gain advantage from it
    Equipment,          // boost passive abilities: damage boosting, mana gain, direct damage or resource generation
    Special,            // Prank cards which in-utilize enemy card draw and pollute their deck
    Prayer;             // effects like healing, burst damage and card draw, to activate their effect they need to wait for rounds
}
