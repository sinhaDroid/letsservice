package com.sinhadroid.letsservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sinhadroid.letsservice.model.DataHandler;
import com.sinhadroid.letsservice.model.UserResponse;

/**
 * Created by deepanshu on 23/5/17.
 */

public class LocationServiceManager extends Service {

    private static final String TAG = LocationServiceManager.class.getSimpleName();
    private LocationManager mLocationManager = null;
    // Time interval 1 minute
    private static final int LOCATION_INTERVAL = 1000 * 60;
    private static final float LOCATION_DISTANCE = 0;

    private void initializeMyLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    // TODO: ADD GPS or NETWORK
    LocationListener[] mLocationListeners = new LocationListener[]{
            new MyLocationListener(LocationManager.PASSIVE_PROVIDER)
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        initializeMyLocationManager();

        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.PASSIVE_PROVIDER,
                    LOCATION_INTERVAL,
                    LOCATION_DISTANCE,
                    mLocationListeners[0]
            );
        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        DataHandler.getInstance().setServiceStarted(false);
        super.onDestroy();
        if (null != mLocationManager) {
            for (LocationListener mLocationListener : mLocationListeners) {
                try {
                    mLocationManager.removeUpdates(mLocationListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyLocationListener implements LocationListener {

        Location mLastLocation;

        MyLocationListener(String provider) {
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            mLastLocation.set(location);
            Log.e(TAG, "onLocationChanged");
            UserResponse response = Utility.getLocationObject(location);
            DataHandler.getInstance().saveUserData(response);

            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.BundleKeys.DATA, response);

            Intent intent = new Intent(Constants.BroadCastKeys.USER_DATA);
            intent.putExtras(bundle);
            sendBroadcast(intent);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled");
        }
    }
}
