package com.example.xjc.work1;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 配置工具类
 */
public class ConfigUtils {
    public static final String FILE_NAME = "config_sp";
    public static SimpleDateFormat format;
    public static Date data;

    public static String getTime() {
        data = new Date(System.currentTimeMillis());
        format = new SimpleDateFormat("yyyy-MM-dd");
        String string = format.format(data);
        return string;
    }

    /**
     * 保存备忘录
     *
     * @param context
     * @param title
     */
    public static void saveMemo(Context context, String title, String time) {
        JSONObject jo = new JSONObject();
        String con = (String) get(context, "memo", "[]");
        try {
            jo.put("title", title);
            jo.put("time", time);
            JSONArray ja = new JSONArray(con);
            ja.put(jo);
            put(context, "memo", ja.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取备忘录
     *
     * @param context
     */
    public static JSONArray readMemo(Context context) throws JSONException {
        String con = (String) get(context, "memo", "[]");
        return new JSONArray(con);
    }

    /**
     * 获取指定数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(Context context, String key, Object defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultValue instanceof Boolean) {//判断对象类型
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        }
        return null;
    }

    /**
     * 保存指定数据
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, (String) value);
        }
        editor.commit();
    }

    /**
     * 删除指定数据
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);//删除
        editor.commit();//提交
    }

    /**
     * 检查对应数据是否存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 获取所有的数据
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 删除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
