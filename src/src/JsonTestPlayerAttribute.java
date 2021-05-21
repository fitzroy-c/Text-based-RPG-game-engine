import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class JsonTestPlayerAttribute {
    private static final Set<String> nullableParametersSet = new HashSet<>();
    static {
        nullableParametersSet.add("maxHPIncreasePerLv");
        nullableParametersSet.add("armorIncreasePerLv");
        nullableParametersSet.add("damageIncreasePerLv");
        nullableParametersSet.add("initRandomMaxHP");
        nullableParametersSet.add("criticalChanceIncreasePerLv");

        nullableParametersSet.add("initRandomMaxHP");
        nullableParametersSet.add("initBaseMaxHP");
        nullableParametersSet.add("initRandomMoney");
        nullableParametersSet.add("initBaseMoney");
        nullableParametersSet.add("initMaxXP");

        nullableParametersSet.add("initXPPerLv");
        nullableParametersSet.add("initRandomArmor");
        nullableParametersSet.add("initBaseArmor");
        nullableParametersSet.add("initRandomDamage");
        nullableParametersSet.add("initBaseDamage");

        nullableParametersSet.add("initCriticalChance");
        nullableParametersSet.add("initMaxCriticalChance");
        nullableParametersSet.add("initBagWeight");
        nullableParametersSet.add("initXCoordinate");
        nullableParametersSet.add("initYCoordinate");
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
        Set<String> params = jsonMap.keySet();
        for (String param : params) {
            if (!nullableParametersSet.contains(param)) {
                throw new JSONException("bad param: " + param);
            } else {
                for (String para : nullableParametersSet) {
                    if (!params.contains(para)) {
                        throw new JSONException("miss coordinates param: " + para);
                    }
                }
            }
        }
        try {
            jsonMap.getObject("maxHPIncreasePerLv", int.class);
        } catch (Exception e) {
            throw new JSONException("bad maxHPIncreasePerLv: " + jsonMap.getObject("maxHPIncreasePerLv", String.class));
        }
        try {
            jsonMap.getObject("armorIncreasePerLv", int.class);
        } catch (Exception e) {
            throw new JSONException("bad armorIncreasePerLv: " + jsonMap.getObject("armorIncreasePerLv", String.class));
        }
        try {
            jsonMap.getObject("damageIncreasePerLv", int.class);
        } catch (Exception e) {
            throw new JSONException("bad damageIncreasePerLv: " + jsonMap.getObject("damageIncreasePerLv", String.class));
        }
        try {
            jsonMap.getObject("initRandomMaxHP", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initRandomMaxHP: " + jsonMap.getObject("initRandomMaxHP", String.class));
        }
        try {
            jsonMap.getObject("criticalChanceIncreasePerLv", int.class);
        } catch (Exception e) {
            throw new JSONException("bad criticalChanceIncreasePerLv: " + jsonMap.getObject("criticalChanceIncreasePerLv", String.class));
        }


        try {
            jsonMap.getObject("initRandomMaxHP", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initRandomMaxHP: " + jsonMap.getObject("initRandomMaxHP", String.class));
        }
        try {
            jsonMap.getObject("initBaseMaxHP", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initBaseMaxHP: " + jsonMap.getObject("initBaseMaxHP", String.class));
        }
        try {
            jsonMap.getObject("initRandomMoney", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initRandomMoney: " + jsonMap.getObject("initRandomMoney", String.class));
        }
        try {
            jsonMap.getObject("initBaseMoney", int.class);
        } catch (Exception e) {
            throw new JSONException("initBaseMoney: " + jsonMap.getObject("initBaseMoney", String.class));
        }
        try {
            jsonMap.getObject("initMaxXP", int.class);
        } catch (Exception e) {
            throw new JSONException("initMaxXP: " + jsonMap.getObject("initMaxXP", String.class));
        }


        try {
            jsonMap.getObject("initXPPerLv", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initXPPerLv: " + jsonMap.getObject("initXPPerLv", String.class));
        }
        try {
            jsonMap.getObject("initRandomArmor", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initRandomArmor: " + jsonMap.getObject("initRandomArmor", String.class));
        }
        try {
            jsonMap.getObject("initBaseArmor", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initBaseArmor: " + jsonMap.getObject("initBaseArmor", String.class));
        }
        try {
            jsonMap.getObject("initRandomDamage", int.class);
        } catch (Exception e) {
            throw new JSONException("initRandomDamage: " + jsonMap.getObject("initRandomDamage", String.class));
        }
        try {
            jsonMap.getObject("initBaseDamage", int.class);
        } catch (Exception e) {
            throw new JSONException("initBaseDamage: " + jsonMap.getObject("initBaseDamage", String.class));
        }


        try {
            jsonMap.getObject("initCriticalChance", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initCriticalChance: " + jsonMap.getObject("initCriticalChance", String.class));
        }
        try {
            jsonMap.getObject("initBagWeight", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initBagWeight: " + jsonMap.getObject("initBagWeight", String.class));
        }
        try {
            jsonMap.getObject("initMaxCriticalChance", int.class);
        } catch (Exception e) {
            throw new JSONException("bad initMaxCriticalChance: " + jsonMap.getObject("initMaxCriticalChance", String.class));
        }
        try {
            jsonMap.getObject("initXCoordinate", int.class);
        } catch (Exception e) {
            throw new JSONException("initXCoordinate: " + jsonMap.getObject("initXCoordinate", String.class));
        }
        try {
            jsonMap.getObject("initYCoordinate", int.class);
        } catch (Exception e) {
            throw new JSONException("initYCoordinate: " + jsonMap.getObject("initYCoordinate", String.class));
        }




    }
    @Test
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("json_files/original_data/Player_original_attributes.json");
        checkJson(txt2String(file));
    }
}
