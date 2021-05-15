package Player;

public class Player {
    String name;
    int HP;
    int maxHP;
    int money; // (how many gold he has)
    Bag bag;
    public Player(String name, int HP, int maxHP, int money){
        this.name = name;
        this.HP = HP;
        this.maxHP = maxHP;
        this.money = money;
        bag = new Bag();
    }

    /*
    Consume an consumable item
     */
    public String consume(Item i){
        if (i.type.equals("consumable") && bag.inMyBag(i)){
            HP+=i.properties.get("health").intValue();
            bag.drop(i);
            return "You've successfully consume "+i.name+" .";
        }
        else if (!i.type.equals("consumable")) return "You can't consume "+i.name+" .";
        else return "You don't have "+i.name+" .";
    }
}
