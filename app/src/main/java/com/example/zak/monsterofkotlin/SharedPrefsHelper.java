package com.example.zak.monsterofkotlin;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsHelper {

    private final SharedPreferences mSharedPreferences;
    private Context context;
    static final String KEY_ENTRY = "text_entry";

    public SharedPrefsHelper(SharedPreferences sharedPreferences){
        mSharedPreferences = sharedPreferences;    }

    public boolean putSharedPrefsHelper(String str){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_ENTRY, str);

        return editor.commit();
    }

    public String getSharedPrefs(){
        String str = mSharedPreferences.getString(KEY_ENTRY, "");
        return str;
    }
}
