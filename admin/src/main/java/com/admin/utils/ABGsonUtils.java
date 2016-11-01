package com.admin.utils;

import android.util.Log;

import com.admin.mine.BaseEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类描述：ABGsonUtils Gson工具类
 * 创建人：Michael
 * 创建时间：2015/12/15 11:39
 * 修改人：Michael
 * 修改时间：2015/12/15 11:39
 * 修改备注：
 */
public class ABGsonUtils {

    public static <T> T getPerson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
            Log.d("ABGsonUtils", "" + t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> List<T> getPersons(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getList(String jsonString) {
        List<String> list = new ArrayList<String>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;

    }

    public List<BaseEntity> getListPerson(String jsonString) {
        List<BaseEntity> list = new ArrayList<BaseEntity>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<BaseEntity>>() {
        }.getType());
        return list;
    }

    public List<Map<String, Object>> listKeyMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;
    }


}  
