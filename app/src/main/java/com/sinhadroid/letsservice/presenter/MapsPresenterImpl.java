package com.sinhadroid.letsservice.presenter;

import android.content.Context;

import com.sinhadroid.letsservice.model.DataHandler;
import com.sinhadroid.letsservice.model.UserResponse;
import com.sinhadroid.letsservice.view.DetailAdapter;
import com.sinhadroid.letsservice.view.MapsView;

import java.util.List;

/**
 * Created by deepanshu on 23/5/17.
 */

public class MapsPresenterImpl implements MapsPresenter {

    private MapsView mMapsView;

    private DetailAdapter mDetailAdapter;

    private MapsPresenterImpl(MapsView mapsView) {
        this.mMapsView = mapsView;
    }

    public static MapsPresenterImpl newInstance(MapsView mapsView) {
        return new MapsPresenterImpl(mapsView);
    }

    @Override
    public void onCreate() {
        Context context = mMapsView.getContext();

        if (null != context) {
            mMapsView.loadMap();
            mMapsView.setAdapter(mDetailAdapter = new DetailAdapter(context));
        }
    }

    @Override
    public void onMapReady() {
        updateAdapter();

        if (mDetailAdapter.getItemCount() > 0) {
            UserResponse item = mDetailAdapter.getItem(0);
            mMapsView.updateLocationOnMap(item.getLat(), item.getLon());
        }
    }

    private void updateAdapter() {
        mDetailAdapter.clear();
        List<UserResponse> data = DataHandler.getInstance().getUserData();
        mDetailAdapter.addAll(data);
    }

    @Override
    public void addInAdapter(UserResponse userLocation) {
        mDetailAdapter.add(userLocation, 0);
    }
}
