package com.sinhadroid.letsservice;

import android.app.Application;

import com.sinhadroid.letsservice.model.DataHandler;

/**
 * Created by deepanshu on 24/5/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataHandler.getInstance().init(this);
    }
}
