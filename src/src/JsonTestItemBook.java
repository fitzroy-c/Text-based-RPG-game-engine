import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class JsonTestItemBook {
    private static final Set<String> nullableParametersSet2 = new HashSet<>();
    private static final Set<String> nullableParametersSet3 = new HashSet<>();
    private static final Set<String> nullableParametersSet4 = new HashSet<>();

    static {
        nullableParametersSet2.add("currentWeight");
        nullableParametersSet2.add("maxWeight");
        nullableParametersSet2.add("itemList");

        nullableParametersSet3.add("id");
        nullableParametersSet3.add("type");
        nullableParametersSet3.add("name");
        nullableParametersSet3.add("description");
        nullableParametersSet3.add("properties");

        nullableParametersSet4.add("health");
        nullableParametersSet4.add("weight");
        nullableParametersSet4.add("value");
    }


    public static String txt2String(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {//readLine  line by line
                result = result + "\n" + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void checkJson(String json) throws JSONException {
        JSONObject jsonMap = JSONObject.parseObject(json);
        Set<String> coordinates = jsonMap.keySet();

        for (String coordinate : coordinates) {
            if (coordinate.split(",").length!=2){
                throw new JSONException("bad coordinate: " + coordinate);
            }

            try {

                Integer.parseInt(coordinate.split(",")[0]);

                Integer.parseInt(coordinate.split(",")[1]);

            } catch (NumberFormatException e) {
                throw new JSONException("bad coordinate: " + coordinate);
            }

            //the coordinate is designed in a limit range 31*31
            Integer x = Integer.parseInt(coordinate.split(",")[0]);
            if(x<0||x>30){
                throw new JSONException("coordinate x out of range");
            }
            Integer y = Integer.parseInt(coordinate.split(",")[1]);
            if(y<0||y>30){
                throw new JSONException("coordinate y out of range");
            }
        }

        for (String coordinate : coordinates) {
            JSONObject bag = jsonMap.getJSONObject(coordinate);
            Set<String> params = bag.keySet();
            for (String param : params) {
                if (!nullableParametersSet2.contains(param) & !nullableParametersSet2.contains(param)) {
                    throw new JSONException("bad param: " + param);
                } else {
                    for (String para : nullableParametersSet2) {
                        if (!params.contains(para)) {
                            throw new JSONException("miss coordinates param: " + para);
                        }
                    }
                }
            }

            try {
                bag.getObject("currentWeight", int.class);
            } catch (Exception e) {
                throw new JSONException("bad currentWeight: " + bag.getObject("currentWeight", String.class));
            }
            //currentWeight should be not negative
            int currentWeight = bag.getObject("currentWeight", int.class);
            if(currentWeight<0){
                throw new JSONException("currentWeight out of range");
            }

            try {
                bag.getObject("currentWeight", int.class);
            } catch (Exception e) {
                throw new JSONException("bad currentWeight: " + bag.getObject("currentWeight", String.class));
            }
            //currentWeight should be not negative
            currentWeight = bag.getObject("currentWeight", int.class);
            if(currentWeight<0){
                throw new JSONException("currentWeight out of range");
            }

            try {
                bag.getObject("maxWeight", int.class);
            } catch (Exception e) {
                throw new JSONException("bad maxWeight: " + bag.getObject("maxWeight", String.class));
            }
            //maxWeight should be not negative
            int maxWeight = bag.getObject("maxWeight", int.class);
            if(maxWeight<0){
                throw new JSONException("maxWeight out of range");
            }
            //maxWeight should be larger than currentWeight
            if (maxWeight<currentWeight){
                throw new JSONException("maxWeight less than currentWeight");
            }

            JSONArray itemList = bag.getJSONArray("itemList");
            for (JSONObject item : itemList.toJavaList(JSONObject.class)) {

                Set<String> itemKeys = item.keySet();
                for (String itemKey : itemKeys) {
                    if (!nullableParametersSet3.contains(itemKey)) {
                        throw new JSONException("bad param: " + itemKey);
                    } else {
                        for (String para : nullableParametersSet3) {
                            if (!itemKeys.contains(para)) {
                                throw new JSONException("miss param on item: " + para);
                            }
                        }
                    }
                }

                try {
                    item.getObject("id", String.class);
                } catch (Exception e) {
                    throw new JSONException("bad id: " + item.getObject("id", String.class));
                }
                try {
                    item.getObject("type", String.class);
                } catch (Exception e) {
                    throw new JSONException("bad type: " + item.getObject("type", String.class));
                }
                try {
                    item.getObject("name", String.class);
                } catch (Exception e) {
                    throw new JSONException("bad name: " + item.getObject("name", String.class));
                }
                try {
                    item.getObject("description", String.class);
                } catch (Exception e) {
                    throw new JSONException("bad description: " + item.getObject("description", String.class));
                }

                JSONObject properties = item.getJSONObject("properties");
                Set<String> propertiesKeys = properties.keySet();
                if (!properties.keySet().isEmpty()) {
                    for (String propertiesKey : propertiesKeys) {
                        if (!nullableParametersSet4.contains(propertiesKey)) {
                            throw new JSONException("bad param: " + propertiesKey);
                        } else {
                            for (String para : nullableParametersSet4) {
                                if (!propertiesKeys.contains(para)) {
                                    throw new JSONException("miss param on properties: " + para);
                                }
                            }
                        }
                    }

                    try {
                        properties.getObject("health", int.class);
                    } catch (Exception e) {
                        throw new JSONException("bad health: " + properties.getObject("health", String.class));
                    }
                    try {
                        properties.getObject("weight", int.class);
                    } catch (Exception e) {
                        throw new JSONException("bad weight: " + properties.getObject("weight", String.class));
                    }
                    try {
                        properties.getObject("value", int.class);
                    } catch (Exception e) {
                        throw new JSONException("bad value: " + properties.getObject("value", String.class));
                    }
                }

            }

        }
    }

    @Test
    public void testIfJsonValid() {
        File file = new File("json_files/original_data/InitializedItem.json");
        JsonTestInitializedItem.checkJson(txt2String(file));
    }
}
