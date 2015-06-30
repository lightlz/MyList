package com.light.myilists.volley;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by light on 15-2-26.
 */
public class VolleyHttp {

    public static final int HTTP_SUCCEED = 0;

    public static final int HTTP_FAILED = -1;

    public static void post(Context context,String url,Map<String,String> map,
                            final Handler handler, final int type){

        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        final JSONObject jsonObject = new JSONObject(map);
        Log.v("http post :  ", jsonObject.toString());
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response : ", response.toString());

                        //if(response.get("ret").toString().equals("0")){
                            Message msg = new Message();
                            msg.obj = response;
                            msg.what = type;
                            handler.sendMessage(msg);
                        //}else{
                        //    handler.sendEmptyMessage(HTTP_FAILED);
                        //}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error : ", error.getMessage(), error);
                handler.sendEmptyMessage(HTTP_FAILED);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    public static void get(Context context,String url, final Handler handler, final int type){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest StringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response : ", response.toString());
                        Message msg = new Message();
                        msg.obj = response;
                        msg.what = HTTP_SUCCEED;
                        msg.arg1 = type;
                        handler.sendMessage(msg);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error : ", error.getMessage(), error);
                handler.sendEmptyMessage(HTTP_FAILED);
            }
        });
        requestQueue.add(StringRequest);
    }


}
