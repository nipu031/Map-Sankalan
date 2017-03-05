package com.example.sau.multisample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sau on 17/4/16.
 */
public class TwoIntoThree extends Activity {

    int No_of_iteration = 2;
    StringBuilder tapSequence = new StringBuilder();
    String mobilie_id = "";
    String responseGlobal;

    LinearLayout[] linearLayouts = new LinearLayout[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_into_three);

        mobilie_id = android.os.Build.SERIAL;

        linearLayouts[0] = (LinearLayout) findViewById(R.id.linearlayout_1_1);
        linearLayouts[1] = (LinearLayout) findViewById(R.id.linearlayout_1_2);
        linearLayouts[2] = (LinearLayout) findViewById(R.id.linearlayout_1_3);
        linearLayouts[3] = (LinearLayout) findViewById(R.id.linearlayout_2_1);
        linearLayouts[4] = (LinearLayout) findViewById(R.id.linearlayout_2_2);
        linearLayouts[5] = (LinearLayout) findViewById(R.id.linearlayout_2_3);
        linearLayouts[6] = (LinearLayout) findViewById(R.id.linearlayout_3);

        if (BaseActivity.no_of_iteration==1)
        {
            Toast.makeText(getApplicationContext(),"Re-Press The Tap",Toast.LENGTH_LONG).show();
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
                /*MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.one);
                mp.start();*/
                return false;
            }
        });

        linearLayouts[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "2";
                tapSequence.append(value);
                /*MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.two);
                mp.start();*/
                return false;
            }
        });


        linearLayouts[2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "3";
                tapSequence.append(value);
                /*MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.three);
                mp.start();*/
                return false;
            }
        });

        linearLayouts[3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "4";
                tapSequence.append(value);
               /* MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.four);
                mp.start();*/
                return false;
            }
        });

        linearLayouts[4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "5";
                tapSequence.append(value);
                /*MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.five);
                mp.start();*/
                return false;
            }
        });

        linearLayouts[5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String value = "6";
                tapSequence.append(value);
                /*MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.six);
                mp.start();*/
                return false;
            }
        });


        linearLayouts[6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Means This is registration Phase
                if (BaseActivity.tap_iteration==2) {

                    BaseActivity.tapseq=tapSequence.toString();
                    Log.i("First Tap", tapSequence.toString());
                    startActivity(new Intent(BaseActivity.context, TwoIntoThree.class));
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
