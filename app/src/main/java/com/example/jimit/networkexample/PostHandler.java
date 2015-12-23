package com.example.jimit.networkexample;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by jimit on 24-12-2015.
 */
public class PostHandler implements Response.Listener<JSONArray>, Response.ErrorListener {

    private static final String TAG = PostHandler.class.getSimpleName();

    private WeakReference<Context> mContextReference;
    private String url;
    private DownloadCallback callback;

    public PostHandler(Context context, String url, DownloadCallback callback) {
        mContextReference = new WeakReference<>(context);
        this.url = url;
        this.callback = callback;
    }

    public JsonArrayRequest request() {
        return new JsonArrayRequest(Request.Method.GET, url, null, this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.onDownloadComplete(true, error.getMessage(), null);
    }

    @Override
    public void onResponse(JSONArray response) {
        Context context = mContextReference.get();
        if (null != context) {
            ArrayList<Post> postArrayList = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject object = response.getJSONObject(i);
                    int userId = object.getInt("userId");
                    int id = object.getInt("id");
                    String title = object.getString("title");
                    String body = object.getString("body");
                    postArrayList.add(new Post(body, id, title, userId));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            callback.onDownloadComplete(false, "Downloaded", postArrayList);
        }
    }
}
