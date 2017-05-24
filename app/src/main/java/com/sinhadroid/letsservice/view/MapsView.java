package com.sinhadroid.letsservice.view;

import android.content.Context;

/**
 * Created by deepanshu on 23/5/17.
 */

public interface MapsView {
    Context getContext();

    void loadMap();

    void setAdapter(DetailAdapter detailAdapter);

    void updateLocationOnMap(double latitute, double longitude);
}
