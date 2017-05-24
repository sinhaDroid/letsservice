package com.sinhadroid.letsservice.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sinhadroid.letsservice.Constants.SharedKeys;

/**
 * Created by deepanshu on 24/5/17.
 */

public class DataHandler extends AbstractDataHandler {

    private static final String SESSION_FILE_NAME = "userData";

    private static final DataHandler ourInstance = new DataHandler();

    public static DataHandler getInstance() {
        return ourInstance;
    }

    private DataHandler() {
    }

    private SharedPreferences mSharedPreferences;

    public void init(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SESSION_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public void setServiceStarted(boolean value) {
        setSharedBooleanData(SharedKeys.SERVICE_STARTED, value);
    }

    public boolean isServiceStarted() {
        return getSharedBooleanData(SharedKeys.SERVICE_STARTED, false);
    }

    public void saveUserData(UserResponse location) {
        List<UserResponse> userData = getUserData();
        userData.add(0, location);
        Gson gson = new Gson();
        setSharedStringData(SharedKeys.SERVICE_DATA, gson.toJson(userData));
    }

    public List<UserResponse> getUserData() {
        String data = getSharedStringData(SharedKeys.SERVICE_DATA);

        if (null != data && data.length() > 0) {
            List<UserResponse> list;
            Gson gson = new Gson();
            Type type = new TypeToken<List<UserResponse>>() {
            }.getType();
            list = gson.fromJson(data, type);
            return list;
        }
        return new ArrayList<>();
    }
}
