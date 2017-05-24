package com.sinhadroid.letsservice;

import android.location.Location;

import com.sinhadroid.letsservice.model.UserResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by deepanshu on 24/5/17.
 */

class Utility {

    static UserResponse getLocationObject(Location location) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS", Locale.getDefault());
        return new UserResponse(dateFormat.format(new Date(System.currentTimeMillis())), location.getLatitude(), location.getLongitude());
    }
}
