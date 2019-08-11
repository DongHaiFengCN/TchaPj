package com.application.tchapj.utils2;

import android.content.Context;
import android.content.SharedPreferences;

public class PreUtils {

    public static final String CONFIG_FILE_NAME = "config";

    public static void putString(String key, String value){
        SharedPreferences sp = UIUtils.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static String getString(String key, String defValue){
        SharedPreferences sp = UIUtils.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }


}
