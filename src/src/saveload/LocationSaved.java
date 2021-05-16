package saveload;

import AbnormalPoints.AbnormalPoint;
import Player.Item;
import com.google.gson.*;
import navigation.Coordinate;
import navigation.Placement;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationSaved {

    private String fileName;
    private Map<Coordinate, Placement> locations;
    private static LocationSaved instance;//

    private String getFileName() {
        return fileName;
    }

    public static LocationSaved createSaved(String profileName) {
        if ("".equals(profileName)) {
            return instance;
        }
        if (instance == null) {
            instance = new LocationSaved(profileName);
        } else if (!instance.getFileName().contains(profileName)) {
            instance = new LocationSaved(profileName);
        }
        return instance;
    }

    public LocationSaved(String profileName) {
        locations = new HashMap<>();
        fileName = "json_files/gameSaved/" + profileName + "/locations.json";
        load();
    }

    public void load(){

    }
    public void writeLocations() {
        try {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<Coordinate, Placement> entry : locations.entrySet()) {
                Placement location = entry.getValue();
                JsonObject locationJsonElement = new JsonObject();
                locationJsonElement.addProperty("coordinate",
                        location.getCoordinate().toString());
                locationJsonElement.addProperty("description",
                        location.getDescription());
                locationJsonElement.addProperty("danger",
                        String.valueOf(location.getDangerRate()));
                JsonArray itemList = new JsonArray();
                List<Item> items = location.getItems();
                if (items.size() > 0) {
                    for (Item item : items) {
                        JsonPrimitive itemJson =
                                new JsonPrimitive(item.getId());
                        itemList.add(itemJson);
                    }
                    locationJsonElement.add("item", itemList);
                }
                JsonArray abnormalPointsList = new JsonArray();
                List<AbnormalPoint> abnormalPoints = location.getAbnormalPoints();
                if (abnormalPoints.size() > 0) {
                    for (AbnormalPoint abnormalPoint : abnormalPoints) {
                        JsonPrimitive abnormalPointsJson = new JsonPrimitive(abnormalPoint.getId());
                        abnormalPointsList.add(abnormalPointsJson);
                    }
                    locationJsonElement.add("abnormal", abnormalPointsList);
                }
                jsonObject.add(location.getCoordinate().toString(),
                        locationJsonElement);
            }
            Writer writer = new FileWriter(fileName);
            Gson gson = new Gson();
            gson.toJson(jsonObject, writer);
            writer.close();
            System.out.println("Game locations saved.");
        } catch (IOException i) {
            System.out.println("Failed saving.");
        }
    }
}
