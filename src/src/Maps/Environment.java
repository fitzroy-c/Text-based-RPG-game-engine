package Maps;

import java.util.List;

//this function is for future development

public class Environment {
    String name;
    String description;
    List<Rooms> listOfRooms;
    // next environment


    public Environment(String name, String description, List<Rooms> listOfRooms) {
        this.name = name;
        this.description = description;
        this.listOfRooms = listOfRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Rooms> getListOfRooms() {
        return listOfRooms;
    }

    public void setListOfRooms(List<Rooms> listOfRooms) { this.listOfRooms = listOfRooms; }
}
