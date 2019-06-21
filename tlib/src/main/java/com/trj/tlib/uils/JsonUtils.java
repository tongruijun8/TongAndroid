package com.trj.tlib.uils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Deprecated
public abstract class JsonUtils {
    /**
     * 解析是否存在
     */
    public static boolean has(JSONObject j, String... str) throws JSONException {
        if (str == null || j == null) {
            return false;
        }
        int l = str.length;
        for (int i = 0; i < l - 1; i++) {
            if (j == null || !j.has(str[i]))
                return false;
            Object obj = j.get(str[i]);
            if (obj instanceof JSONObject) {
                j = (JSONObject) obj;
            } else {
                return false;
            }
        }
        if (j == null || !j.has(str[l - 1])) {
            return false;
        } else {
            return true;
        }
    }

    public static JSONArray getJArray(JSONObject j, String... str) throws JSONException {
        return getValue(new JSONArray(), j, str);
    }

    public static JSONObject getJObject(JSONObject j, String... str) throws JSONException {
        return getValue(new JSONObject(), j, str);
    }

    public static String getValue(JSONObject j, String... str) throws JSONException {
        return getValue("", j, str);
    }

    /**
     * 解析Json
     *
     * @param j
     * @param str
     * @return
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static <E> E getValue(E defult, JSONObject j, String... str) throws JSONException {
        if (str == null || j == null) {
            return defult;
        }
        int l = str.length;
        for (int i = 0; i < l - 1; i++) {
            if (j == null || !j.has(str[i]))
                return defult;
            Object obj = j.get(str[i]);
            if (obj instanceof JSONObject) {
                j = (JSONObject) obj;
            } else {
                return defult;
            }
        }
        if (j == null || !j.has(str[l - 1])) {
            return defult;
        } else if (defult instanceof String) {
            String s = j.getString(str[l - 1]);
            if (s == null || s.equals(null) || s.equals("")) {
                return defult;
            }
            return (E) s;
        } else if (defult instanceof Integer) {
            return (E) Integer.valueOf(j.getInt(str[l - 1]));
        } else if (defult instanceof Boolean) {
            return (E) Boolean.valueOf(j.getBoolean(str[l - 1]));
        } else if (defult instanceof Long) {
            return (E) Long.valueOf(j.getLong(str[l - 1]));
        } else if (defult instanceof Double) {
            return (E) Double.valueOf(j.getDouble(str[l - 1]));
        } else if (defult instanceof JSONObject) {
            return (E) j.getJSONObject(str[l - 1]);
        } else if (defult instanceof JSONArray) {
            return (E) j.getJSONArray(str[l - 1]);
        }
        return defult;
    }

    //json数组转list
    public static ArrayList<HashMap<String, String>> array2list(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject j = array.getJSONObject(i);
            HashMap<String, String> item = new HashMap<>();
            Iterator<String> keys = j.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                item.put(key, j.getString(key));
            }
            list.add(item);
        }
        return list;
    }

    public static String setSuccess() {
        return "{\"RetCode\":0}";
    }

    public static String setError(String code, String errorMsg) {
        return "{\"RetCode\":" + code + ",\"RetMsg\":" + errorMsg + "}";
    }
}
