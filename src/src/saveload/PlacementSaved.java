package saveload;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import navigation.Coordinate;
import navigation.Placement;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlacementSaved {

    private String fileName;
    private Map<Coordinate, Placement> locations;
    private static PlacementSaved instance;//

    private String getFileName() {
        return fileName;
    }

    public static PlacementSaved createSaved(String profileName) {
        if ("".equals(profileName)) {
            return instance;
        }
        if (instance == null) {
            instance = new PlacementSaved(profileName);
        } else if (!instance.getFileName().contains(profileName)) {
            instance = new PlacementSaved(profileName);
        }
        return instance;
    }

    public PlacementSaved(String profileName) {
        locations = new HashMap<>();
        fileName = "json_files/gameSaved/" + profileName + "/locations.json";
        load();
    }

    public void load(){

    }

    private void loadPlacementFile() {
    }

}
