package com.example.jimit.networkexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.toolbox.JsonArrayRequest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DownloadCallback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private PostRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new PostRecyclerAdapter(MainActivity.this, new ArrayList<Post>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                callApi();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDownloadComplete(boolean isError, String message, ArrayList<Post> postArrayList) {
        adapter.removeAll();
        for (Post post : postArrayList) {
            Log.d(TAG, "onDownloadComplete: " + post.toString());
            adapter.add(post);
        }
    }

    private void callApi() {
        PostHandler handler = new PostHandler(MainActivity.this, getResources().getString(R.string.api), this);
        JsonArrayRequest request = handler.request();
        NetworkApp.getInstance().addToRequestQueue(request, "Post");
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null != networkInfo
                && ((networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                || (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)))
            return true;
        return false;
    }
}
