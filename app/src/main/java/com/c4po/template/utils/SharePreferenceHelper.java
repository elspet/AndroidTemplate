package com.c4po.template.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.c4po.template.global.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharePreferenceHelper {

	public final static String CLIENTURLPATH = "ClientUrlPath";

	public static Boolean setAccountStringValue(Context context, String key,
                                                String value) {
		
		if (context == null) {
			return false;
		}
		
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		return editor.commit();
		
	}
	
	public static String getAccountStringValue(Context context, String key) {
		if (context == null) {
			return "";
		}
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_ACCOUNT_NAME, 0);
		return sharedPreferences.getString(key, "");
		
	}
	
	public static boolean clearAccountInfo(Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_ACCOUNT_NAME, 0);
		Editor edit = sharedPreferences.edit();
		return edit.clear().commit();
	}
	
	public static Boolean setStringValue(Context context, String key,
                                         String value) {

		if (context == null) {
			return false;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		return editor.commit();

	}

	public static String getStringValue(Context context, String key) {
		if (context == null) {
			return "";
		}
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		return sharedPreferences.getString(key, "");

	}

	public static Boolean setIntValue(Context context, String key, int value) {
		if (context == null) {
			return false;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		return editor.commit();
	}
	
	public static int getIntValue(Context context, String key) {

		if (context == null) {
			return 0;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		return sharedPreferences.getInt(key, 0);
	}
	public static Boolean setFloatValue(Context context, String key, Float value) {
		if (context == null) {
			return false;
		}
		
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putFloat(key, value);
		return editor.commit();
	}
	
	public static float getFloatValue(Context context, String key) {
		
		if (context == null) {
			return 0;
		}
		
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		return sharedPreferences.getFloat(key, 3);
	}

	public static Boolean setLongValue(Context context, String key, Long value) {

		if (context == null) {
			return false;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putLong(key, value);
		return editor.commit();
	}

	public static Long getLongValue(Context context, String key) {

		if (context == null) {
			return -1L;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		return sharedPreferences.getLong(key, -1);
	}

	public static Boolean setBooleanValue(Context context, String key,
                                          boolean value) {

		if (context == null) {
			return false;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}

	public static Boolean getBooleanValue(Context context, String key) {

		if (context == null) {
			return false;
		}

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				Constants.SHAREPREFENCE_NAME, 0);
		return sharedPreferences.getBoolean(key, false);
	}
	
	public static <T> boolean saveObj(Context context , String key, T obj){
		Gson gson = new Gson();

		if (obj == null) {
			SharePreferenceHelper.setStringValue(context,
					key, "");
		} else {
			SharePreferenceHelper.setStringValue(context,
					key, gson.toJson(obj));
		}

		return true;
	}
	
	public static <T> T get(Context context, String key, Class<T> type){
		String valueString = SharePreferenceHelper.getStringValue(context,
				key);

		if (valueString == "") {
			return null;
		}

		Gson gson = new Gson();

		T t = gson.fromJson(valueString, type);
		
		return t;
	}
	
	public static <T> boolean saveList(Context context , String key, List<T> list){
		Gson gson = new Gson();

		if (list == null) {
			SharePreferenceHelper.setStringValue(context,
					key, "");
		} else {
			SharePreferenceHelper.setStringValue(context,
					key, gson.toJson(list));
		}

		return true;
	}
	

	public static <T> List<T> getList(Context context, String key, Class<T> clazz){
		String valueString = SharePreferenceHelper.getStringValue(context,
				key);

		if (valueString == "") {
			return null;
		}

		Gson gson = new Gson();
		List<T> list = new ArrayList();

		JsonArray array = new JsonParser().parse(valueString.toString()).getAsJsonArray();
		for (final JsonElement elem : array)
		{
			list.add(gson.fromJson(elem,clazz));
		}

		return list;
	}

	public static boolean saveStrList(Context context , String key, List<String> list){
		Gson gson = new Gson();

		if (list == null) {
			SharePreferenceHelper.setStringValue(context,
					key, "");
		} else {
			SharePreferenceHelper.setStringValue(context,
					key, gson.toJson(list));
		}

		return true;
	}

	public static List<String> getStrList(Context context, String key){
		String valueString = SharePreferenceHelper.getStringValue(context,
				key);

		if (valueString == "") {
			return null;
		}

		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>() {
		}.getType();

		List<String> entity = gson.fromJson(valueString, type);


		return entity;
	}
	/**
	 * 保存创建群组的勾选状态
	 */
	public static void saveSelect_state(Map<String, Boolean> imGroups, Context context, String key) {
		Gson gson = new Gson();
		if (imGroups == null) {
			SharePreferenceHelper.setStringValue(context, key, "");
		} else {
			SharePreferenceHelper.setStringValue(context, key,
					gson.toJson(imGroups));
		}
	}

	/**
	 * 获取创建群组的勾选状态
	 */
	public static Map<String, Boolean> getSelect_state(Context context, String key) {
		String valueString = SharePreferenceHelper.getStringValue(context,
				key);
		if (valueString == "") {
			Map<String, Boolean> arrayList = new HashMap<String,Boolean>();
			return arrayList;
		}

		Type type = new TypeToken<Map<String, Boolean>>() {
		}.getType();

		Gson gson = new Gson();

		Map<String, Boolean> imGroups = gson.fromJson(valueString, type);

		return imGroups;
	}
	
}
