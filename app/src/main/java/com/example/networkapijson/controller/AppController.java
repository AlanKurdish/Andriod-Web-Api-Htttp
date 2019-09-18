package com.example.networkapijson.controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class AppController extends Application {

    public static String TAG=AppController.class.getSimpleName();

    private static AppController mInstance;

    private RequestQueue queue;

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }



    public RequestQueue getRequestQueue() {
        if (queue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            queue = Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }


    public <T> void addToRequestQueue(Request<T> req,String tag) {
        req.setTag(tag.isEmpty() ? TAG : tag);
        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void CanclePendingRequest(Object tag)
    {
        if (queue!=null)
        {
            queue.cancelAll(tag);
        }
    }
}
