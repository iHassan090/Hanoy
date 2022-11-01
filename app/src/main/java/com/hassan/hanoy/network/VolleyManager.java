package com.hassan.hanoy.network;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hassan.hanoy.interfaces.IRequestListener;

import org.json.JSONObject;

public class VolleyManager {
    private static VolleyManager mInstance;
    private static Context mContext;

    private RequestQueue mRequestQueue;
    private static final int DEFAULT_MAX_RETRIES = 1;


    private DefaultRetryPolicy mDefaultRetryPolicy;

    public VolleyManager(Context context) {
        mContext = context;
        this.mRequestQueue = Volley.newRequestQueue(context);

    }

    public static synchronized VolleyManager getInstance(Context context) {
        if (mInstance == null)
            mInstance = new VolleyManager(context);
        return mInstance;
    }

    public void sendApiCall(String url, IRequestListener mListener) {
        System.out.println("Request URL: " + url);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Request Response: " + response);
                mListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request Error: " + error.getMessage());
                mListener.onError(error.getMessage());
            }
        });
        this.mRequestQueue.add(jsonRequest);
    }
}
