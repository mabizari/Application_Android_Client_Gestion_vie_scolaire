package com.zerophi.gestionvie;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedpref {
    final static String filename ="isTrue";
    final static String filename2 = "dd";
    public static String readSharedSetting(Context context, String settingName, String defaultValue){
        SharedPreferences sharedPref = context.getSharedPreferences(filename2,Context.MODE_PRIVATE);
        return sharedPref.getString(settingName,defaultValue);
    }

    public static int readSharedSettingint(Context context, String settingName, int defaultValue){
        SharedPreferences sharedPref = context.getSharedPreferences(filename2,Context.MODE_PRIVATE);
        return sharedPref.getInt(settingName,defaultValue);
    }

    public static void saveSharedSetting(Context context, String settingName, String defaultValue){
        SharedPreferences sharedPref = context.getSharedPreferences(filename2,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName,defaultValue);
        editor.apply();
    }

    public static void saveSharedSettingint (Context context, String settingName, int defaultValue){
  SharedPreferences sharedPref = context.getSharedPreferences(filename2,Context.MODE_PRIVATE);
  SharedPreferences.Editor editor = sharedPref.edit();
  editor.putInt(settingName,defaultValue);
  editor.apply();
    }
}