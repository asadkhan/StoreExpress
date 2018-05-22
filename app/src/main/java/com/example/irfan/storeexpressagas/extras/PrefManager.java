package com.example.irfan.storeexpressagas.extras;

import android.content.Context;

public class PrefManager {

    private static final String USER = "USER";
    private static final String LOGIN = "LOGIN";
    private static final String CUSTOMER_CELL = "CELL";
    private static final String CUSTOMER_ADDRESS = "ADDRESS";

    private final android.content.SharedPreferences mPrefs;

    public PrefManager(Context mContext) {
        mPrefs = android.preference.PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    public boolean removeALL() {
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.clear();
        return prefsEditor.commit();
    }

    public boolean saveAddress(String address) {
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString(CUSTOMER_ADDRESS, address);
        return prefsEditor.commit();

    }


    public String getAddress() {

        String address = mPrefs.getString(CUSTOMER_ADDRESS, "");

        return address;
    }

}
