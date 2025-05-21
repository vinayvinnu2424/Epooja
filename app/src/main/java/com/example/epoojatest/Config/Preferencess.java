package com.example.epoojatest.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencess {

    private static final String PREFERENCE_NAME ="EpoojaTest" ;

    public static final String LOGINCHECK ="LOGINCHECK" ;
    public static final String UserId ="UserId" ;
    public static final String email ="email" ;
    public static final String Password ="Password" ;
    public static final String first ="firstname";
    public static final String last ="lastname";
    public static final String gender ="gender";



    public static void saveStringValue(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String loadStringValue(Context mContext, String from, String defValue) {
        return mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(from, defValue);
    }


    public static void deleteSharedPreferences(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.clear().commit();

    }
}
