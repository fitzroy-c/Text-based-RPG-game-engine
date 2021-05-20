import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

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
 * a must have the following characteristics: diagtree, blessaddmaxhp (positive integer), blessaddarmour (positive integer),
 * blessadddamage (positive integer), hasendedtalk (Boolean), abnormalpointtype (must be NPC_Talk),
 * name (string), Intro (string), maxhp (positive integer), HP (positive integer),
 * damage (positive integer), armour (positive integer), gold (positive integer), xpgain (positive integer),
 * critchance ([0,1], up to two decimal places -- still need to work on), element (must be "normal"), possible characteristics: npcbag
 *
 * Npcbag must have the following characteristics: currentweight (non negative integer),
 * maxweight (non negative integer), itemlist (unlimited length).
 *
 * If itemlist is not empty,
 * the object in npcbag should have the following characteristics:
 * ID (string), type (string), name (string), description (sring), properties (possibly empty, but should have),
 *
 * if any in properties, characteristics: health (integer), weight (integer), Value (integer))
 *
 * diagtree must have the characteristic root,
 *
 * root must have index (non negative integer), npcdialog (string), nextdialogs (), dtype (must be one from
 * ATTACK,END_ GIVE_ GOLD，END_ GIVE_ ITEM, END_ NONE, CONTINUE,END_ BLESS_ HP,END_ BLESS_ ARMOR,END_ BLESS_ Damage )
 *
 * If nextdialogs exists must follow the similar structure with root，
 * with an potential added characteristic: playerReply (string)
 *
 * @author yitao chen
 */



public class JsonTestNpcTalk {

    private static final Set<String> nullableParametersSet1 = new HashSet<>();
    private static final Set<String> optionalParametersSet1 = new HashSet<>();
    private static final Set<String> nullableParametersSet2 = new HashSet<>();
    private static final Set<String> nullableParametersSet3 = new HashSet<>();
    private static final Set<String> nullableParametersSet4 = new HashSet<>();
    private static final Set<String> nullableParametersSet5 = new HashSet<>();
    private static final Set<String> optionalParametersSet5 = new HashSet<>();
    private static final Set<String> dtypes = new HashSet<>();

    static {
        nullableParametersSet1.add("dialogTree");
        nullableParametersSet1.add("blessAddMaxHP");
        nullableParametersSet1.add("blessAddArmour");
        nullableParametersSet1.add("blessAddDamage");
        nullableParametersSet1.add("hasEndedTalk");
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

        nullableParametersSet5.add("index");
        nullableParametersSet5.add("npcDialog");
        nullableParametersSet5.add("dtype");

        optionalParametersSet5.add("nextDialogs");
        optionalParametersSet5.add("playerReply");

        dtypes.add("END_ATTACK");
        dtypes.add("END_GIVE_GOLD");
        dtypes.add("END_GIVE_ITEM");
        dtypes.add("END_NONE");
        dtypes.add("CONTINUE");
        dtypes.add("END_BLESS_HP");
        dtypes.add("END_BLESS_ARMOR");
        dtypes.add("END_BLESS_DAMAGE");


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

    private static void checkDialog(JSONObject jsonObject) throws JSONException{
        Set<String> dialogParams = jsonObject.keySet();

        for (String param : dialogParams) {
            if (!nullableParametersSet5.contains(param) & !optionalParametersSet5.contains(param)) {
                throw new JSONException("bad param on dialogTree: " + param);
            } else {
                for (String para : nullableParametersSet5) {
                    if (!dialogParams.contains(para)) {
                        throw new JSONException("miss param on dialogTree: " + para);
                    }
                }
            }
        }

        try {
            jsonObject.getObject("index", int.class);
        } catch (Exception e) {
            throw new JSONException("bad index: " + jsonObject.getObject("index", String.class));
        }

        try {
            jsonObject.getObject("npcDialog", String.class);
        } catch (Exception e) {
            throw new JSONException("bad npcDialog: " + jsonObject.getObject("npcDialog", String.class));
        }

        try {
            if (!dtypes.contains(jsonObject.getObject("dtype", String.class))) {
                throw new JSONException("bad dialogTree node dtype: " + jsonObject.getObject("dtype", String.class));
            }
        } catch (Exception e) {
            throw new JSONException("bad dialogTree node dtype: " + jsonObject.getObject("dtype", String.class));
        }

        if(dialogParams.contains("playerReply")){
            try {
                jsonObject.getObject("playerReply", String.class);
            } catch (Exception e) {
                throw new JSONException("bad dialogTree node playerReply: " + jsonObject.getObject("playerReply", String.class));
            }
        }

        //recursion
        if(dialogParams.contains("nextDialogs")){
            for (JSONObject obj:
                    jsonObject.getJSONArray("nextDialogs").toJavaList(JSONObject.class)) {
                checkDialog(obj);
            }
        }

    }

    public static void checkJson(String json) throws JSONException {
        JSONObject jsonMap = JSONObject.parseObject(json);
        Set<String> coordinates = jsonMap.keySet();

        for (String coordinate : coordinates) {
            try {
                Integer.parseInt(coordinate.split(",")[0]);
                Integer.parseInt(coordinate.split(",")[1]);
            } catch (NumberFormatException e) {
                throw new JSONException("bad coordinate: " + coordinate);
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
                npc.getObject("blessAddMaxHP", int.class);
            } catch (Exception e) {
                throw new JSONException("bad blessAddMaxHP: " + npc.getObject("blessAddMaxHP", String.class));
            }
            try {
                npc.getObject("blessAddDamage", int.class);
            } catch (Exception e) {
                throw new JSONException("bad blessAddMaxHP: " + npc.getObject("blessAddMaxHP", String.class));
            }
            try {
                npc.getObject("blessAddMaxHP", int.class);
            } catch (Exception e) {
                throw new JSONException("bad blessAddMaxHP: " + npc.getObject("blessAddMaxHP", String.class));
            }
            try {
                npc.getObject("hasEndedTalk", boolean.class);
            } catch (Exception e) {
                throw new JSONException("bad hasEndedTalk: " + npc.getObject("hasEndedTalk", String.class));
            }
            try {
                if (!npc.getObject("abnormalPointType", String.class).equals("NPC_TALK")) {
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
            try {
                npc.getObject("HP", int.class);
            } catch (Exception e) {
                throw new JSONException("bad HP: " + npc.getObject("HP", String.class));
            }
            try {
                npc.getObject("damage", int.class);
            } catch (Exception e) {
                throw new JSONException("bad damage: " + npc.getObject("damage", String.class));
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
                npc.getObject("critChance", int.class);
            } catch (Exception e) {
                throw new JSONException("bad critChance: " + npc.getObject("critChance", String.class));
            }
            try {
                if (!npc.getObject("element", String.class).equals("Normal")) {
                    throw new JSONException("bad element: " + npc.getObject("element", String.class));
                }
            } catch (Exception e) {
                throw new JSONException("bad element: " + npc.getObject("element", String.class));
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
                try {
                    npcBag.getObject("maxWeight", int.class);
                } catch (Exception e) {
                    throw new JSONException("bad maxWeight: " + npcBag.getObject("maxWeight", String.class));
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

            JSONObject diagTree = npc.getJSONObject("dialogTree");
            Set<String> diagTreeParams = diagTree.keySet();

            if (diagTreeParams.isEmpty()) {
                throw new JSONException("request dialogTree param:  root");
            }

            for (String diagTreeParam :
                    diagTreeParams) {
                if (!diagTreeParam.equals("root")) {
                    throw new JSONException("bad param: " + diagTreeParam);
                }
            }
            checkDialog(diagTree.getJSONObject("root"));
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("json_files/original_data/TalkNPC.json");
        JsonTestNpcTalk.checkJson(txt2String(file));
    }
}
