package com.bawei.myapplication.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.myapplication.app.MyApp;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * author : Eaves
 * desc   : Volley网络请求工具类
 * date   : 2019/11/27
 */
public class VolleyUtils {
    private RequestQueue mQueue;
    private VolleyUtils() {
        mQueue = Volley.newRequestQueue(MyApp.mContext);
    }
    private static class VolleyHolder{
        private final static VolleyUtils VOLLEY_UTILS = new VolleyUtils();
    }

    public static VolleyUtils getInstance() {
        return VolleyHolder.VOLLEY_UTILS;
    }
    public interface VolleyCallBack{
        void onSuccess(String json);
        void onError(String error);
    }

    //GET
    public void doGet(String httpUrl, final VolleyCallBack volleyCallBack){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallBack.onError(error.getMessage());
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = "";
                try {
                    str = new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    str = new String(response.data);
                }
                return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        mQueue.add(stringRequest);
    }

    //POST
    public void doPost(String httpUrl, final Map<String,String> map, final VolleyCallBack volleyCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallBack.onError(error.getMessage());
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = "";
                try {
                    str = new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    str = new String(response.data);
                }
                return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map != null) {
                    return map;
                }
                return super.getParams();
            }
        };
        mQueue.add(stringRequest);
    }


    //有无网
    public boolean hasNet(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }
    }

    //有无WIFI
    public boolean isWifi(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return false;
        }
    }

    //是否流量
    public boolean isMobile(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return false;
        }
    }
}
