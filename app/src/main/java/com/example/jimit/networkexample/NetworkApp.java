package com.example.jimit.networkexample;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by jimit on 24-12-2015.
 */
public class NetworkApp extends Application {

    private static final String TAG = NetworkApp.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static NetworkApp _mInstance = new NetworkApp();

    public static synchronized NetworkApp getInstance() {
        return _mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _mInstance = this;
    }

    public RequestQueue getRequestQueue() {
        if (null == mRequestQueue)
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
}
