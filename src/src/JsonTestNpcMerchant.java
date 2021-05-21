import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


/**
 *
 * The class is build to testify the TalkNPC.json file to see if it suits the hashmap<Coordinate,npc_Talk>
 * reference: https://www.cnblogs.com/myseries/p/10574184.html shows the basic usage of the fastjson lib
 *
 * I tried to build a jsonSchema once to do the same function, but I have trouble describing the tree structure.
 * I changed the way to operate on the string (from the very beginning)
 *
 * Npcjson structure
 *
 * A coordinate determines a object type (a)
 *
 * a must have the following characteristics: abnormalpointtype (must be NPC_MERCHANT),
 * name (string), Intro (string), maxhp (positive integer), HP (positive integer),
 * damage (positive integer), armour (integer), gold (integer), xpgain (integer),
 * critchance ([0,1] not so restrict,just a double), possible characteristics: npcbag
 *
 * Npcbag must have the following characteristics: currentweight (non negative integer),
 * maxweight (non negative integer), itemlist (unlimited length).
 *
 * If itemlist is not empty,
 * the object in npcbag should have the following characteristics:
 * ID (string), type (string), name (string), description (sring), properties (possibly empty, but should have),
 *
 * if any in properties, item characteristics: health (integer), weight (integer), Value (integer))
 *
 *
 * check the class first, some int range check remains undo
 *
 * @author Guanming Ou, modify on code from yitao chen's JsonTestNpcTalk
 */
public class JsonTestNpcMerchant {
    private static final Set<String> nullableParametersSet1 = new HashSet<>();
    private static final Set<String> optionalParametersSet1 = new HashSet<>();
    private static final Set<String> nullableParametersSet2 = new HashSet<>();
    private static final Set<String> nullableParametersSet3 = new HashSet<>();
    private static final Set<String> nullableParametersSet4 = new HashSet<>();

    static {
        nullableParametersSet1.add("abnormalPointType");
        nullableParametersSet1.add("name");
        nullableParametersSet1.add("intro");
        nullableParametersSet1.add("maxHP");
        nullableParametersSet1.add("HP");
        nullableParametersSet1.add("damage");
        nullableParametersSet1.add("armour");
        nullableParametersSet1.add("gold");
        nullableParametersSet1.add("xpGain");
        nullableParametersSet1.add("critChance");
        nullableParametersSet1.add("element");
        optionalParametersSet1.add("npcBag");

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
            JSONObject npc = jsonMap.getJSONObject(coordinate);
            Set<String> params = npc.keySet();
            for (String param : params) {
                if (!nullableParametersSet1.contains(param) & !optionalParametersSet1.contains(param)) {
                    throw new JSONException("bad param: " + param);
                } else {
                    for (String para : nullableParametersSet1) {
                        if (!params.contains(para)) {
                            throw new JSONException("miss coordinates param: " + para);
                        }
                    }
                }
            }

            try {
                if (!npc.getObject("abnormalPointType", String.class).equals("NPC_MERCHANT")) {
                    throw new JSONException("bad abnormalPointType: " + npc.getObject("abnormalPointType", String.class));
                }
            } catch (Exception e) {
                throw new JSONException("bad abnormalPointType: " + npc.getObject("abnormalPointType", String.class));
            }

            try {
                npc.getObject("name", String.class);
            } catch (Exception e) {
                throw new JSONException("bad name: " + npc.getObject("name", String.class));
            }

            try {
                npc.getObject("intro", String.class);
            } catch (Exception e) {
                throw new JSONException("bad intro: " + npc.getObject("intro", String.class));
            }

            try {
                npc.getObject("maxHP", int.class);
            } catch (Exception e) {
                throw new JSONException("bad maxHP: " + npc.getObject("maxHP", String.class));
            }
            // maxHP should be positive
            int maxHP = npc.getObject("maxHP", int.class);
            if(maxHP<0){
                throw new JSONException("maxHP out of range");
            }

            try {
                npc.getObject("HP", int.class);
            } catch (Exception e) {
                throw new JSONException("bad HP: " + npc.getObject("HP", String.class));
            }
            // HP should be positive
            int HP = npc.getObject("HP", int.class);
            if(HP<0){
                throw new JSONException("HP out of range");
            }
            // HP should be less than maxHP
            if(HP>maxHP){
                throw new JSONException("HP larger than maxHP");
            }

            try {
                npc.getObject("damage", int.class);
            } catch (Exception e) {
                throw new JSONException("bad damage: " + npc.getObject("damage", String.class));
            }
            // damage should be positive
            int damage = npc.getObject("damage", int.class);
            if(damage<=0){
                throw new JSONException("damage out of range");
            }

            try {
                npc.getObject("armour", int.class);
            } catch (Exception e) {
                throw new JSONException("bad armour: " + npc.getObject("armour", String.class));
            }

            try {
                npc.getObject("gold", int.class);
            } catch (Exception e) {
                throw new JSONException("bad gold: " + npc.getObject("gold", String.class));
            }

            try {
                npc.getObject("xpGain", int.class);
            } catch (Exception e) {
                throw new JSONException("bad xpGain: " + npc.getObject("xpGain", String.class));
            }

            try {
                npc.getObject("critChance", double.class);
            } catch (Exception e) {
                throw new JSONException("bad critChance: " + npc.getObject("critChance", String.class));
            }
            //critChance should be not negative, i think the limit can be removed
            double critChance = npc.getObject("critChance", double.class);
            if(critChance<0){
                throw new JSONException("critChance out of range");
            }

            if (params.contains("npcBag")) {

                JSONObject npcBag = npc.getJSONObject("npcBag");
                Set<String> npcBagParams = npcBag.keySet();
                for (String npcBagParam : npcBagParams) {
                    if (!nullableParametersSet2.contains(npcBagParam)) {
                        throw new JSONException("bad param in npcBag: " + npcBagParam);
                    } else {
                        for (String para : nullableParametersSet2) {
                            if (!npcBagParams.contains(para)) {
                                throw new JSONException("miss param in npcBag: " + para);
                            }
                        }
                    }
                }

                try {
                    npcBag.getObject("currentWeight", int.class);
                } catch (Exception e) {
                    throw new JSONException("bad currentWeight: " + npcBag.getObject("currentWeight", String.class));
                }
                //currentWeight should be not negative
                int currentWeight = npcBag.getObject("currentWeight", int.class);
                if(currentWeight<0){
                    throw new JSONException("currentWeight out of range");
                }

                try {
                    npcBag.getObject("maxWeight", int.class);
                } catch (Exception e) {
                    throw new JSONException("bad maxWeight: " + npcBag.getObject("maxWeight", String.class));
                }
                //maxWeight should be not negative
                int maxWeight = npcBag.getObject("maxWeight", int.class);
                if(maxWeight<0){
                    throw new JSONException("maxWeight out of range");
                }
                //maxWeight should be larger than currentWeight
                if (maxWeight<currentWeight){
                    throw new JSONException("maxWeight less than currentWeight");
                }

                JSONArray itemList = npcBag.getJSONArray("itemList");
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
    }

    @Test
    public void testIfJsonValid() {
        File file = new File("json_files/original_data/MerchantNPC.json");
        JsonTestNpcMerchant.checkJson(txt2String(file));
    }
}

