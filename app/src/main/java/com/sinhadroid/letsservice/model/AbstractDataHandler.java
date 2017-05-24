package com.sinhadroid.letsservice.model;

import android.content.SharedPreferences;

/**
 * Created by deepanshu on 24/5/17.
 */
public abstract class AbstractDataHandler {

    public abstract SharedPreferences getSharedPreferences();

    public void setSharedStringData(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public String getSharedStringData(String key) {
        return getSharedPreferences().getString(key, null);
    }

    public void setSharedBooleanData(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public boolean getSharedBooleanData(String key, boolean defVal) {
        return getSharedPreferences().getBoolean(key, defVal);
    }

    private SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

}