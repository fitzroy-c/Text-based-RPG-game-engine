package Player;

import java.util.Collections;
import java.util.List;
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
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getDescription(){ return description; }
    public Integer getWeight() {
        if (properties.containsKey("weight")) {
            return properties.get("weight");
        }
        return Integer.valueOf(0);//in case some special item don't have weight(ability card)
    }

    public Integer getDamage() {
        if (properties.containsKey("damage")) {
            return properties.get("damage");
        }
        return Integer.valueOf(0);//in case some special item don't have weight(gold,treasure,xp spring)
    }
    public int getProperty(String property) {
        if (! properties.containsKey(property)) {
            return 0;
        }
        return properties.get(property);
    }
    public Map<String, Integer> getProperties() {
        return Collections.unmodifiableMap(properties);// for safe reason unchangeable map
    }
    public boolean containsProperty(String key) {
        return properties.containsKey(key);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Item) {
            Item i = (Item) obj;
            return name.equals(i.name);
        }
        return false;
    }
    public void print() {
        // TODO: need a function to print out all we have in this real location (name + description?)
    }
}
