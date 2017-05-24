package com.sinhadroid.letsservice.presenter;

import com.sinhadroid.letsservice.model.UserResponse;

/**
 * Created by deepanshu on 23/5/17.
 */

public interface MapsPresenter {
    void onCreate();

    void onMapReady();

    void addInAdapter(UserResponse userLocation);
}
