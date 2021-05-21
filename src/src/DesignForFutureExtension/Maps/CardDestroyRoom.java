package DesignForFutureExtension.Maps;

import Card.Cards;

import java.util.ArrayList;

/**
 * This room allow players to destroy (remove card) from their currently card pool
 *  - The first time enter this room is free (per card destroy room)
 *  - More card destroy require increase gold
 *
 *  this class is for future development
 *  @author Guanming Ou
 */
public class CardDestroyRoom extends Rooms{
    int currentNumberOfDestroy;  // this indicate how many times a player Destroy their cards.
    int[] DestroyCostList;       // this indicate how much cost after every Destroy

    public CardDestroyRoom(String name, String description, int[] DestroyCostList) {
        super(name, description);
        this.currentNumberOfDestroy = 0;
        this.DestroyCostList = DestroyCostList;
    }

    public int getCurrentNumberOfDestroy() {
        return currentNumberOfDestroy;
    }

    public void setCurrentNumberOfDestroy(int currentNumberOfDestroy) {
        this.currentNumberOfDestroy = currentNumberOfDestroy;
    }

    public int[] getDestroyCostList() {
        return DestroyCostList;
    }

    public void setDestroyCostList(int[] DestroyCostList) {
        this.DestroyCostList = DestroyCostList;
    }

    /**
     * This destroy a player's card
     * @param card The card to be destroy
     * @param currentCardList The current set of card that the player holds
     * @return card that has been destroyed
     */
    public static void DestroyCard(Cards card, ArrayList<Cards> currentCardList){
//        int cardToDeleteID = card.getCardID();
//        int scanCardID;
//        int index = 0;
//
//        for (Cards c : currentCardList){
//            scanCardID = c.getCardID();
//            if (cardToDeleteID == scanCardID){
//                currentCardList.remove(index);
//                break;
//            }
//            scanCardID++;
//        }
        currentCardList.remove(card);

        // TODO: subtract gold from the player
    }

    /**
     * This check if this card can be Destroy
     * - False: This is the last card inside the card list
     * @param currentCardList The current set of card that the player holds
     * @return Boolean: True (not the last card, and card inside the list),
     *                  False (it is the last card or card not inside the list)
     */
    public static boolean canDestroy(Cards card, ArrayList<Cards> currentCardList) {
        int countTrue = 0;
        countTrue += (currentCardList.size() > 1 ? 1 : 0);
        countTrue += (currentCardList.contains(card) ? 1 : 0);
        return (countTrue == 2 ? true : false);
    }

    /**
     * This check if player have enough gold to Destroy the card
     * - False: Player cannot afford with current gold in wallet
     * @param wallet The player current number of gold
     * @param room The Destroy room that current player is in
     * @return Boolean: True (can afford), false (cannot afford)
     */
    public static boolean canAfford(int wallet, CardDestroyRoom room){
        int currentNumberOfDestroy = room.getCurrentNumberOfDestroy();
        int[] costList = room.getDestroyCostList();
        int listLength = costList.length;

        // To avoid outOfBound
        if (currentNumberOfDestroy > listLength)
            currentNumberOfDestroy = listLength;

        int cost = costList[currentNumberOfDestroy];

        return (wallet >= cost ? true : false);
    }
}
