package Card;

/**
 * Element effect of a card or monster, a potential future development
 * this class is for future development
 * @author Guanming Ou
 */
public enum Element {
    // Basic Elements
    Normal,     // Counters:                      Counter by: None

    // Magic Elements
    Water,      // Counters: Fire                 Counter by: Electric
    Fire,       // Counters: Nature               Counter by: Water
    Nature,     // Counters: Earth                Counter by: Fire
    Earth,      // Counters: Electric             Counter by: Nature
    Electric,   // Counters: Water                Counter by: Earth

    // Special Elements
    Light,      // Counters: Dark                 Counter by: Dark
    Dark,       // Counters: Light                Counter by: Light
    Psychic;    // Counters: all magic elements   Counter by: Normal

    /**
     * Calculate if Element 1 is counter by Element 2
     * @param a Element 1
     * @param b Element 2
     * @return boolean (True/False)
     */
    public boolean isCounter (Element a, Element b){
        // TODO: Finish this function to calculate is a counter by b.
        return false;
    }
}
