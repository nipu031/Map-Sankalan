package com.example.nipu.multi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sau on 17/4/16.
 */
public class FourIntoThree extends Activity {

    int No_of_iteration = 2;
    StringBuilder tapSequence = new StringBuilder();
    String mobilie_id = "";

    String responseGlobal;

    LinearLayout[] linearLayouts = new LinearLayout[13];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_into_three);

        mobilie_id = android.os.Build.SERIAL;

        linearLayouts[0] = (LinearLayout) findViewById(R.id.linearlayout_1_1);
        linearLayouts[1] = (LinearLayout) findViewById(R.id.linearlayout_1_2);
        linearLayouts[2] = (LinearLayout) findViewById(R.id.linearlayout_1_3);
        linearLayouts[3] = (LinearLayout) findViewById(R.id.linearlayout_2_1);
        linearLayouts[4] = (LinearLayout) findViewById(R.id.linearlayout_2_2);
        linearLayouts[5] = (LinearLayout) findViewById(R.id.linearlayout_2_3);
        linearLayouts[6] = (LinearLayout) findViewById(R.id.linearlayout_3_1);
        linearLayouts[7] = (LinearLayout) findViewById(R.id.linearlayout_3_2);
        linearLayouts[8] = (LinearLayout) findViewById(R.id.linearlayout_3_3);
        linearLayouts[9] = (LinearLayout) findViewById(R.id.linearlayout_4_1);
        linearLayouts[10] = (LinearLayout) findViewById(R.id.linearlayout_4_2);
        linearLayouts[11] = (LinearLayout) findViewById(R.id.linearlayout_4_3);
        linearLayouts[12] = (LinearLayout) findViewById(R.id.linearlayout_5);

        //Variable is used because to differenticate from where createTapSequence is calling.

        if (BaseActivity.no_of_iteration==1)
        {
            Toast.makeText(getApplicationContext(),"Re-Press The Tap",Toast.LENGTH_SHORT).show();
        }

        BaseActivity.context=this;
        createTapSequence();
    }

    public void createTapSequence() {


        linearLayouts[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "1";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "2";
                tapSequence.append(value);

                return false;
            }
        });


        linearLayouts[2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "3";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "4";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "5";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "6";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "7";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[7].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "8";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[8].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "9";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[9].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "10";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[10].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "11";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[11].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "12";
                tapSequence.append(value);

                return false;
            }
        });

        linearLayouts[12].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Means This is registration Phase
                if (BaseActivity.tap_iteration==2) {

                    BaseActivity.tapseq=tapSequence.toString();
                    Log.i("First Tap", tapSequence.toString());
                    startActivity(new Intent(BaseActivity.context, FourIntoThree.class));
                    BaseActivity.tap_iteration=1;
                }

                //Either login or registration is done at second time.
                else {

                    //Login Step
                    if (BaseActivity.SignIn) {
                        //fetch data from server snd verify it
                        Log.i("In the Second Phase","Login Step");
                        //Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_LONG).show();

                        String gesture="mvn";
                        long totaltime=552;
                        //responseGlobal=checkdata(BaseActivity.Auth_type,BaseActivity.GridSize,tapSequence.toString(),gesture,BaseActivity.AndroidId,Long.toString(totaltime));
                        String Phase="2"; //Login Phase


                        //Send Data to server
                        SendDataToServer sendDataToServer=new SendDataToServer();

                        responseGlobal=sendDataToServer.LoginRegis(Phase,BaseActivity.Auth_type,BaseActivity.GridSize,tapSequence.toString(),gesture,BaseActivity.AndroidId,Long.toString(totaltime));
                        //Toast.makeText(getApplicationContext(),responseGlobal,Toast.LENGTH_LONG).show();

                        startActivity(new Intent(BaseActivity.context,MainActivity.class));
                    }

                    else if (BaseActivity.tapseq.contentEquals(tapSequence.toString())) {

                        //Send to the server and Back to MAin screen
                        String gesture="";

                        Log.i("In the Second Phase","Registration Step");


                        Log.i("Second Tap", BaseActivity.tapseq);


                        BaseActivity.endRegtime=System.currentTimeMillis();

                        long totaltime=BaseActivity.endRegtime-BaseActivity.startRegtime;

                        //Send Data to server and records ACK
                        //responseGlobal=senddata(BaseActivity.Auth_type,BaseActivity.GridSize,BaseActivity.tapseq,gesture,BaseActivity.AndroidId,Long.toString(totaltime));

                        String Phase="1"; //Login Phase

                        Boolean b=true;

                        //Assign currently context to temp variable so we can use it in future.
                       /* BaseActivity.context=TwoIntoOne.this;*/

                        SendDataToServer sendDataToServer=new SendDataToServer();

                        responseGlobal=sendDataToServer.LoginRegis(Phase,BaseActivity.Auth_type,BaseActivity.GridSize,tapSequence.toString(),gesture,BaseActivity.AndroidId,Long.toString(totaltime));
                        //Toast.makeText(getApplicationContext(),responseGlobal,Toast.LENGTH_LONG).show();

                        BaseActivity.endRegtime=0;
                        BaseActivity.startRegtime=0;

                        //Toast.makeText(getApplicationContext(),responseGlobal,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(BaseActivity.context,MainActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(BaseActivity.context, MainActivity.class));
                    }


                }


                return false;
            }
        });

    }
}
