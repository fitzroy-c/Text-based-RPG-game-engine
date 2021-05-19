import java.io.*;
import java.util.*;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//
//public class JsonTest {
//
//    private static final Set<String> nullableParametersSet1 = new HashSet<>();
//    private static final Set<String> optionalParametersSet1 = new HashSet<>();
//    private static final Set<String> nullableParametersSet2 = new HashSet<>();
//    private static final Set<String> nullableParametersSet3 = new HashSet<>();
//    private static final Set<String> nullableParametersSet4 = new HashSet<>();
//    private static final Set<String> nullableParametersSet5 = new HashSet<>();
//    private static final Set<String> optionalParametersSet5 = new HashSet<>();
//    private static final Set<String> dtypes = new HashSet<>();
//
//    static {
//        nullableParametersSet1.add("dialogTree");
//        nullableParametersSet1.add("blessAddMaxHP");
//        nullableParametersSet1.add("blessAddArmour");
//        nullableParametersSet1.add("blessAddDamage");
//        nullableParametersSet1.add("hasEndedTalk");
//        nullableParametersSet1.add("abnormalPointType");
//        nullableParametersSet1.add("name");
//        nullableParametersSet1.add("intro");
//        nullableParametersSet1.add("maxHP");
//        nullableParametersSet1.add("HP");
//        nullableParametersSet1.add("damage");
//        nullableParametersSet1.add("armour");
//        nullableParametersSet1.add("gold");
//        nullableParametersSet1.add("xpGain");
//        nullableParametersSet1.add("critChance");
//        nullableParametersSet1.add("element");
//        optionalParametersSet1.add("npcBag");
//
//        nullableParametersSet2.add("currentWeight");
//        nullableParametersSet2.add("maxWeight");
//        nullableParametersSet2.add("itemList");
//
//        nullableParametersSet3.add("id");
//        nullableParametersSet3.add("type");
//        nullableParametersSet3.add("name");
//        nullableParametersSet3.add("description");
//        nullableParametersSet3.add("properties");
//
//        nullableParametersSet4.add("health");
//        nullableParametersSet4.add("weight");
//        nullableParametersSet4.add("value");
//
//        nullableParametersSet5.add("index");
//        nullableParametersSet5.add("npcDialog");
//        nullableParametersSet5.add("dtype");
//
//        optionalParametersSet5.add("nextDialogs");
//        optionalParametersSet5.add("playerReply");
//
//        dtypes.add("END_ATTACK");
//        dtypes.add("END_GIVE_GOLD");
//        dtypes.add("END_GIVE_ITEM");
//        dtypes.add("END_NONE");
//        dtypes.add("CONTINUE");
//        dtypes.add("END_BLESS_HP");
//        dtypes.add("END_BLESS_ARMOR");
//        dtypes.add("END_BLESS_DAMAGE");
//
//
//    }
//
//
//    public static String txt2String(File file) {
//        String result = "";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//            String s = null;
//            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                result = result + "\n" + s;
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    public static void checkJson(String json) throws JSONException {
//        JSONObject jsonMap = JSONObject.parseObject(json);
//        Set<String> coordinates = jsonMap.keySet();
//
//        for (String coordinate : coordinates) {
//            try {
//                Integer.parseInt(coordinate.split(",")[0]);
//                Integer.parseInt(coordinate.split(",")[1]);
//            } catch (NumberFormatException e) {
//                throw new JSONException("bad ordinate: " + coordinate);
//            }
//        }
//
//        for (String coordinate : coordinates) {
//            JSONObject npc = jsonMap.getJSONObject(coordinate);
//            Set<String> params = npc.keySet();
//            for (String param : params) {
//                if (!nullableParametersSet1.contains(param) & !optionalParametersSet1.contains(param)) {
//                    throw new JSONException("bad param: " + param);
//                } else {
//                    for (String para : nullableParametersSet1) {
//                        if (!params.contains(para)) {
//                            throw new JSONException("no param: " + para);
//                        }
//                    }
//                }
//            }
//
//            try {
//                npc.getObject("blessAddMaxHP", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad blessAddMaxHP: " + npc.getObject("blessAddMaxHP", String.class));
//            }
//            try {
//                npc.getObject("blessAddDamage", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad blessAddMaxHP: " + npc.getObject("blessAddMaxHP", String.class));
//            }
//            try {
//                npc.getObject("blessAddMaxHP", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad blessAddMaxHP: " + npc.getObject("blessAddMaxHP", String.class));
//            }
//            try {
//                npc.getObject("hasEndedTalk", boolean.class);
//            } catch (Exception e) {
//                throw new JSONException("bad hasEndedTalk: " + npc.getObject("hasEndedTalk", String.class));
//            }
//            try {
//                if (!npc.getObject("abnormalPointType", String.class).equals("NPC_TALK")) {
//                    throw new JSONException("bad abnormalPointType: " + npc.getObject("abnormalPointType", String.class));
//                }
//            } catch (Exception e) {
//                throw new JSONException("bad abnormalPointType: " + npc.getObject("abnormalPointType", String.class));
//            }
//
//            try {
//                npc.getObject("name", String.class);
//            } catch (Exception e) {
//                throw new JSONException("bad name: " + npc.getObject("name", String.class));
//            }
//            try {
//                npc.getObject("intro", String.class);
//            } catch (Exception e) {
//                throw new JSONException("bad intro: " + npc.getObject("intro", String.class));
//            }
//            try {
//                npc.getObject("maxHP", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad maxHP: " + npc.getObject("maxHP", String.class));
//            }
//            try {
//                npc.getObject("HP", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad HP: " + npc.getObject("HP", String.class));
//            }
//            try {
//                npc.getObject("damage", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad damage: " + npc.getObject("damage", String.class));
//            }
//            try {
//                npc.getObject("armour", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad armour: " + npc.getObject("armour", String.class));
//            }
//            try {
//                npc.getObject("gold", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad gold: " + npc.getObject("gold", String.class));
//            }
//            try {
//                npc.getObject("xpGain", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad xpGain: " + npc.getObject("xpGain", String.class));
//            }
//            try {
//                npc.getObject("critChance", int.class);
//            } catch (Exception e) {
//                throw new JSONException("bad critChance: " + npc.getObject("critChance", String.class));
//            }
//            try {
//                if (!npc.getObject("element", String.class).equals("Normal")) {
//                    throw new JSONException("bad element: " + npc.getObject("element", String.class));
//                }
//            } catch (Exception e) {
//                throw new JSONException("bad element: " + npc.getObject("element", String.class));
//            }
//
//
//            if (params.contains("npcBag")) {//TODO
//
//            }
//
//        }
//
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("json_files/original_data/TalkNPC.json");
//        JsonTest.checkJson(txt2String(file));
//    }
//}
