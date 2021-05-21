import Player.Bag;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonTestItemBook {
    private static final Set<String> nullableParametersSet3 = new HashSet<>();
    private static final Set<String> nullableParametersSet4 = new HashSet<>();

    static {
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
        JSONArray itemList = bag.getJSONArray("json");
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
        File file = new File("json_files/original_data/ItemBook.json");
        JsonTestItemBook.checkJson(txt2String(file));
    }
}
