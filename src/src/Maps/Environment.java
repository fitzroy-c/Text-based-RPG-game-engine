package Maps;

import java.util.List;

public class Environment {
    String name;
    String description;
    List<Rooms> listOfRooms;


    public Environment(String name, String description, List<Rooms> listOfRooms) {
        this.name = name;
        this.description = description;
        this.listOfRooms = listOfRooms;
    }
}
