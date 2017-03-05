package com.example.nipu.example;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sau on 31/5/16.
 */
public class SendDataToServer {


    private static final String Auth_URL="http://54.69.110.134/ankur/logReg.php";
    public static final String KEY_Phase="Phase";
    public static final String KEY_AuthType = "AuthType";
    public static final String KEY_GridSize = "GridSize";
    public static final String KEY_Tap = "Tap";
    public static final String KEY_Gesture= "Gesture";
    public static final String KEY_AndroidId = "AndroidId";
    public static final String KEY_RegTime = "RegTime";

    String responseGlobal;

    public String LoginRegis(final String Phase, final String AuthType, final String GridSize, final String Tap, final String Gesture, final String AndroidId, final String RegTime)
    {
        Log.i("KEY_AuthType",AuthType);
        Log.i("KEY_GridSize",GridSize);
        Log.i("KEY_Tap", Tap);
        Log.i("KEY_Gesture",Gesture);
        Log.i("KEY_AndroidId",AndroidId);
        Log.i("KEY_RegTime", RegTime);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Auth_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseGlobal=response;

                        Toast.makeText(BaseActivity.context,response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BaseActivity.context,error.toString(), Toast.LENGTH_LONG).show();
                        responseGlobal=error.toString();
                    }
                }){



            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_Phase,Phase);
                params.put(KEY_AuthType,AuthType);
                params.put(KEY_GridSize,GridSize);
                params.put(KEY_Tap, Tap);
                params.put(KEY_Gesture,Gesture);
                params.put(KEY_AndroidId,AndroidId);
                params.put(KEY_RegTime, RegTime);
                return params;
            }

        };



        RequestQueue requestQueue = Volley.newRequestQueue(BaseActivity.context);
        requestQueue.add(stringRequest);
        return responseGlobal;
    }





}



