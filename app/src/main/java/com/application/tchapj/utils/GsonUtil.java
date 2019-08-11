package com.application.tchapj.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Json工具
public class GsonUtil {
	private static String TAG = "GsonUtil";
//	private static Gson gson = new GsonBuilder()
//			.excludeFieldsWithoutExposeAnnotation().create();
	private static Gson gson = new GsonBuilder().create();



	public static String str2Json(Object obj){
		return gson.toJson(obj);
	}
	
	public static Object json2Object(String jsonStr, Class<?> cl) {
		Object obj = null;
		try {
			obj = gson.fromJson(jsonStr, cl);
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "------json  is   wrong----------");
		}
		
		return obj;
	}
	
	public static <T> T fromJson(String json, TypeToken<T> token) {
		try {
			return gson.fromJson(json, token.getType());
		} catch (Exception ex) {
			Log.e(TAG, "------json  is   wrong----------");
			return null;
		}
	}

	//得到JSON数据
	public static Map<String, Object> getJosn(String jsonStr) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject json = new JSONObject(jsonStr);// JSONObject.fromObject(jsonStr);

		Iterator i = json.keys();

		while (i.hasNext()) {
			String key = (String) i.next();
			String value = json.getString(key);
			if (value.indexOf("{") == 0) {
				map.put(key.trim(), getJosn(value));
			} else if (value.indexOf("[") == 0) {
				map.put(key.trim(), getList(value));
			} else {
				map.put(key.trim(), value.trim());
			}
		}
		return map;
	}

	//创建JSON数据列表方法
	public static List<Map<String, Object>> getList(String jsonStr) throws Exception {
		//创建数据列表中的数据
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//创建JSON数据对象
		JSONArray ja = new JSONArray(jsonStr);// JSONArray.fromObject(jsonStr);//

		for (int j = 0; j < ja.length(); j++) {
			String jm = ja.get(j) + "";
			if (jm.indexOf("{") == 0) {
				Map<String, Object> m = getJosn(jm);
				list.add(m);   //把JSON数据添加到数据列表中
			}
		}
		return list;
	}

}