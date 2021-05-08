package Maps;

import Card.Cards;

/**
 * This allow player to upgrade their cards
 * - The first time enter this room is free (per upgrade room)
 * - More upgrade require increase gold
 */
public class CardUpgradeRoom extends Rooms{
    int currentNumberOfUpgrade;  // this indicate how many times a player upgrade their cards.
    int[] upgradeCostList;       // this indicate how much cost after every upgrade

    public CardUpgradeRoom(String name, String description, int[] upgradeCostList) {
        super(name, description);
        this.currentNumberOfUpgrade = 0;
        this.upgradeCostList = upgradeCostList;
    }

    public int getCurrentNumberOfUpgrade() {
        return currentNumberOfUpgrade;
    }

    public void setCurrentNumberOfUpgrade(int currentNumberOfUpgrade) {
        this.currentNumberOfUpgrade = currentNumberOfUpgrade;
    }

    public int[] getUpgradeCostList() {
        return upgradeCostList;
    }

    public void setUpgradeCostList(int[] upgradeCostList) {
        this.upgradeCostList = upgradeCostList;
    }

    /**
     * This upgrade a player's card
     * @param card The card to be upgrade
     * @return card that has been upgraded
     */
    public static Cards upgradeCard(Cards card){
        int currentlevel = card.getCardLevel();
        card.setCardLevel(currentlevel + 1);

        // TODO: subtract gold from the player
        return card;
    }

    /**
     * This check if this card can be upgrade
     * - False: This card has already reached max level
     * @param card The card to be upgrade
     * @return Boolean: True (can upgrade), false (cannot upgrade)
     */
    public static boolean canUpgrade(Cards card) {
        return (card.getCardLevel() < card.getMaxCardLevel() ? true : false);
    }

    /**
     * This check if player have enough gold to upgrade the card
     * - False: Player cannot afford with current gold in wallet
     * @param wallet The player current number of gold
     * @param room The upgrade room that current player is in
     * @return Boolean: True (can afford), false (cannot afford)
     */
    public static boolean canAfford(int wallet, CardUpgradeRoom room){
        int currentNumberOfUpgrade = room.getCurrentNumberOfUpgrade();
        int[] costList = room.getUpgradeCostList();
        int listLength = costList.length;

        // To avoid outOfBound
        if (currentNumberOfUpgrade > listLength)
            currentNumberOfUpgrade = listLength;

        int cost = costList[currentNumberOfUpgrade];

        return (wallet >= cost ? true : false);
    }

}
