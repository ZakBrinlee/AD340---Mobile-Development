package com.example.zak.monsterofkotlin;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsHelper {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefsHelper(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.default_name), Context.MODE_PRIVATE);
    }

    public void putSharedPrefsHelper(String str){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.default_text), str);
        editor.commit();
    }

    public String getSharedPrefs(){
        String str = sharedPreferences.getString(context.getResources().getString(R.string.default_text),context.getResources().getString(R.string.default_backup));
        return str;
    }
}
