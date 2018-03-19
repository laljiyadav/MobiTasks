package com.example.erlaljiyadav.mobitask.utility;

/**
 * Created by Win 7 on 10/1/2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    public static final String PREF_USER_ID = "customer_id";
    public static final String PREF_USER_NAME = "name";
    public static final String PREF_USER_MOBILE = "mobile";
    public static final String PREF_USER_EMAIL = "email";
    public static final String PREF_REMEMBER ="remember" ;






    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setUserName(Context ctx, String userName) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }



    public static void setMobile(Context ctx, String userName) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_MOBILE, userName);
        editor.apply();
    }

    public static String getMobile(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_MOBILE, "");
    }

    public static void setUserID(Context ctx, String userid) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userid);
        editor.apply();
    }

    public static String getUserID(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }


    public static void setUserEMAIL(Context ctx, String useremail) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, useremail);
        editor.apply();
    }

    public static String getUserEMAIL(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }
    public static boolean getPrefRemember(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_REMEMBER, false);
    }

    public static void setPrefRemember(Context ctx,boolean remember)
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_REMEMBER, remember);
        editor.apply();
    }
}