package com.example.myapplication;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class ApiCall {
    private static ApiCall mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public ApiCall(Context ctx) {
        mCtx = ctx;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ApiCall getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ApiCall(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
//    public static void make(Context ctx, String query, Response.Listener<String>
//            listener, Response.ErrorListener errorListener) {
//        String url = "https://itunes.apple.com/search?term=" + query
//                + "&country=US";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                listener, errorListener);
//        ApiCall.getInstance(ctx).addToRequestQueue(stringRequest);
//    }

    public static void make(Context ctx, String query, Response.Listener<JSONArray>
            listener, Response.ErrorListener errorListener)
    {

        String url = "https://weather-node-app-259004.appspot.com/getAutocompleteSuggestion/" + query;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                listener, errorListener);
        ApiCall.getInstance(ctx).addToRequestQueue(request);
    }
}
