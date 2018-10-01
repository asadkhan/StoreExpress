package com.example.irfan.storeexpressagas.extras;

import android.content.Context;

public class PrefManager {

    private static final String USERNAME = "USERNAME";
    private static final String LOGIN = "LOGIN";
    private static final String TOKEN = "TOKEN";
    private static final String USERID = "USERID";

    private final android.content.SharedPreferences mPrefs;

    public PrefManager(Context mContext) {
        mPrefs = android.preference.PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    public boolean removeALL() {
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.clear();
        return prefsEditor.commit();
    }

    public boolean saveToken(String token) {
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString(TOKEN, token);
        return prefsEditor.commit();

    }


    public String getToken() {

        String token = mPrefs.getString(TOKEN, "");

        return token;
    }


    public boolean saveUsername(String name) {
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString(USERNAME, name);
        return prefsEditor.commit();

    }


    public String getUsername() {

        String name = mPrefs.getString(USERNAME, "");

        return name;
    }




    public boolean saveUserID(String ID) {
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString(USERID, ID);
        return prefsEditor.commit();

    }


    public String getUserID() {

        String userid = mPrefs.getString(USERID, "");

        return userid;
    }


}
