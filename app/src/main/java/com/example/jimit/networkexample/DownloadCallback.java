package com.example.jimit.networkexample;

import java.util.ArrayList;

/**
 * Created by jimit on 24-12-2015.
 */
public interface DownloadCallback {
    void onDownloadComplete(boolean isError, String message, ArrayList<Post> postArrayList);
}
