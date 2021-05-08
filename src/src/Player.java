import Card.Cards;

import java.util.ArrayList;

public class Player {
    String name;
    String career;

    // Attributes
    int currentHp;
    int maxHP;
    int currentMana;
    int maxMana;
    int currentActionPoint;
    int maxActionPoint;

    int normalAtkBoost;
    int magicAtkBoost;

    int normalArmor;
    int magicArmor;

    // Cards
    ArrayList<Cards> currentHand;       // the cards on the hands, during battle maximum 6 cards
    ArrayList<Cards> currentCardSet;    // the cards that player owns

    public Player(String name, String career, int currentHp, int maxHP, int currentMana, int maxMana,
                  int currentActionPoint, int maxActionPoint, int normalAtkBoost, int magicAtkBoost,
                  int normalArmor, int magicArmor, ArrayList<Cards> currentHand, ArrayList<Cards> currentCardSet) {
        this.name = name;
        this.career = career;
        this.currentHp = currentHp;
        this.maxHP = maxHP;
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.currentActionPoint = currentActionPoint;
        this.maxActionPoint = maxActionPoint;
        this.normalAtkBoost = normalAtkBoost;
        this.magicAtkBoost = magicAtkBoost;
        this.normalArmor = normalArmor;
        this.magicArmor = magicArmor;
        this.currentHand = currentHand;
        this.currentCardSet = currentCardSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getCurrentActionPoint() {
        return currentActionPoint;
    }

    public void setCurrentActionPoint(int currentActionPoint) {
        this.currentActionPoint = currentActionPoint;
    }

    public int getMaxActionPoint() {
        return maxActionPoint;
    }

    public void setMaxActionPoint(int maxActionPoint) {
        this.maxActionPoint = maxActionPoint;
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

    public int getNormalArmor() {
        return normalArmor;
    }

    public void setNormalArmor(int normalArmor) {
        this.normalArmor = normalArmor;
    }

    public int getMagicArmor() {
        return magicArmor;
    }

    public void setMagicArmor(int magicArmor) {
        this.magicArmor = magicArmor;
    }

    public ArrayList<Cards> getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(ArrayList<Cards> currentHand) {
        this.currentHand = currentHand;
    }

    public ArrayList<Cards> getCurrentCardSet() {
        return currentCardSet;
    }

    public void setCurrentCardSet(ArrayList<Cards> currentCardSet) {
        this.currentCardSet = currentCardSet;
    }
}
