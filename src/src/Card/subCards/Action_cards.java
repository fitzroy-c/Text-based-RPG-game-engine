package Card.subCards;

import Card.CardClass;
import Card.Cards;
import Card.Rarity;

public class Action_cards extends Cards {


    /**
     * Constructor of Action_cards extends from Card
     */
    public Action_cards(int cardID, CardClass cardClass, Rarity rarity, String name, String description,
                        String impactDescription, String career, int cardLevel,
                        int maxCardLevel, int movementCost, int manaCost,
                        int normalDamage, int pierceDamage, int continuous, int selfDamage, int drawCard) {

        super(cardID, cardClass, rarity, name, description, impactDescription, career, cardLevel, maxCardLevel, movementCost, manaCost);

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

