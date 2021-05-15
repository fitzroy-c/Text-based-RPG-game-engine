package Player;

import java.util.Map;
// todo item.json
// todo test for these functions in player, bag,item.class
public class Item {
    public final String id;
    public final String type;
    public final String name;
    private final String description;
    public final Map<String, Integer> properties; // may contain price, effect, weight of this item

    public Item(String id, String type, String name, String description, Map<String, Integer> properties) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.properties = properties;
    }

    public String getDescription(){
        return description;
    }
}
