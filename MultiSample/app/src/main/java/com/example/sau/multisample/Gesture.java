package com.example.sau.multisample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by sau on 16/4/16.
 */
public class Gesture extends Activity {


    private ProgressDialog loading;
    String responseGlobal;
    LinearLayout touchLayout;
    double x, y, z;
    double startX = 0, startY = 0, startZ = 0;
    int Start = 0, Startposition = 0, MiddlePosition, newValue, s = 0;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    /*private TextView xTextView;
    private TextView yTextView;
    private TextView zTextView;*/
    StringBuilder gestureSequence = new StringBuilder();
    private TextView value;
    Bundle bundle = new Bundle();
    LinkedList linkedList = new LinkedList();
    int count = 0;
    int countGesture=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture);

        BaseActivity.AndroidId= android.os.Build.SERIAL;

        //playSound = MediaPlayer.create(this, R.raw.beep22);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        touchLayout = (LinearLayout) findViewById(R.id.linearlayout_3);

        final TextView GestureStart=(TextView)findViewById(R.id.gestureStart);

        final TextView GestureTitle=(TextView)findViewById(R.id.gestureTitle);

        if (BaseActivity.gesture_iteration==1)
        {
            GestureTitle.setText("Re-Enter The Gesture");
        }

        BaseActivity.context=Gesture.this;

        //btnstart = (Button) findViewById(R.id.start);

        //Start First Time Touch the screen
        touchLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (count == 0) {

                    GestureStart.setText("Tap Here After Completion");
                    count++;
                    System.out.print("Counting " + count);
                    mSensorManager.registerListener(accelerometerListener, accelerometer,
                            mSensorManager.SENSOR_DELAY_UI);

                } else if (count == 1) {
                    Start = 0;
                    count = 0;

                    mSensorManager.unregisterListener(accelerometerListener);


                    //Means This is registration Phase ANd coming here for first time
                    if (BaseActivity.gesture_iteration==2) {

                        BaseActivity.gestureseq=gestureSequence.toString();
                        Log.i("First Gesture", gestureSequence.toString());
                        BaseActivity.gesture_iteration=1;


                        if (BaseActivity.Sub_Type.contentEquals("Anything"))
                        {
                            startActivity(new Intent(BaseActivity.context,ThreeIntoThree.class));
                        }
                        else {
                            startActivity(new Intent(BaseActivity.context, Gesture.class));
                        }


                    }

                    //Either login or registration is done at second time.
                    else {


                        //Login Step
                        if (BaseActivity.SignIn) {
                            //fetch data from server snd verify it
                            Log.i("In the Second Phase","Login Step");
                            //Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_LONG).show();

                            if (BaseActivity.Sub_Type.contentEquals("Anything"))
                            {
                                String Phase="2";
                                long totaltime=552;
                                BaseActivity.GridSize="3*3";
                                SendDataToServer sendDataToServer=new SendDataToServer();

                                responseGlobal=sendDataToServer.LoginRegis(Phase,BaseActivity.Auth_type,BaseActivity.GridSize,BaseActivity.tapseq,gestureSequence.toString(),BaseActivity.AndroidId,Long.toString(totaltime));
                                startActivity(new Intent(BaseActivity.context,MainActivity.class));
                                finish();
                            }
                            else
                            {
                                String gesture="mvn";
                                long totaltime=552;
                                //responseGlobal=checkdata(BaseActivity.Auth_type,BaseActivity.GridSize,tapSequence.toString(),gesture,BaseActivity.AndroidId,Long.toString(totaltime));
                                String Phase="2"; //Login Phase

                                String Tap="";
                                //Send Data to server
                                SendDataToServer sendDataToServer=new SendDataToServer();

                                responseGlobal=sendDataToServer.LoginRegis(Phase,BaseActivity.Auth_type,BaseActivity.GridSize,Tap,gestureSequence.toString(),BaseActivity.AndroidId,Long.toString(totaltime));
                                //Toast.makeText(getApplicationContext(),responseGlobal,Toast.LENGTH_LONG).show();

                                startActivity(new Intent(BaseActivity.context,MainActivity.class));
                                finish();
                            }

                        }

                        else if (BaseActivity.gestureseq.contentEquals(gestureSequence.toString()) && BaseActivity.Sub_Type.contentEquals("Anything"))
                        {
                            //Send data to server

                            SendDataToServer sendDataToServer=new SendDataToServer();

                            String Phase="1";


                            BaseActivity.GridSize="3*3";

                            BaseActivity.endRegtime=System.currentTimeMillis();

                            long totaltime=BaseActivity.endRegtime-BaseActivity.startRegtime;
                            responseGlobal=sendDataToServer.LoginRegis(Phase,BaseActivity.Auth_type,BaseActivity.GridSize,BaseActivity.tapseq,gestureSequence.toString(),BaseActivity.AndroidId,Long.toString(totaltime));

                            startActivity(new Intent(BaseActivity.context,MainActivity.class));

                        }

                        else if (BaseActivity.gestureseq.contentEquals(gestureSequence.toString())) {

                            //Send to the server and Back to MAin screen
                            String Tap="";

                            Log.i("In the Second Phase","Registration Step");


                            Log.i("Second Gesture", BaseActivity.gestureseq);


                            BaseActivity.endRegtime=System.currentTimeMillis();

                            long totaltime=BaseActivity.endRegtime-BaseActivity.startRegtime;

                            //Send Data to server and records ACK
                            //responseGlobal=senddata(BaseActivity.Auth_type,BaseActivity.GridSize,BaseActivity.tapseq,gesture,BaseActivity.AndroidId,Long.toString(totaltime));

                            String Phase="1"; //Login Phase

                            BaseActivity.GridSize=Integer.toString(gestureSequence.toString().length());
                            //Assign currently context to temp variable so we can use it in future.
                       /* BaseActivity.context=TwoIntoOne.this;*/

                            SendDataToServer sendDataToServer=new SendDataToServer();

                            responseGlobal=sendDataToServer.LoginRegis(Phase,BaseActivity.Auth_type,BaseActivity.GridSize,Tap,gestureSequence.toString(),BaseActivity.AndroidId,Long.toString(totaltime));
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

                }
                return false;
            }
        });



        /*xTextView = (TextView) findViewById(R.id.textView);
        yTextView = (TextView) findViewById(R.id.textView2);
        zTextView = (TextView) findViewById(R.id.textView3);*/
        value = (TextView) findViewById(R.id.result);
    }



    private SensorEventListener accelerometerListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            int maxStart = 0;
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            maxStart = getMax(x, y, z);

            while (true) {

                if (Start == 0 && ((x > 0 || y > 0 || z > 0))) {

                    startX = x;
                    startY = y;
                    startZ = z;

                    Startposition = getMax(startX, startY, startZ);
                    linkedList.add(Startposition);
                    //System.out.println("Start" + Startposition);
                    Start++;
                    break;

                } else if (Start > 0 && (getMax(event.values[0], event.values[1], event.values[2])) != 2) {

                    newValue = getMax(event.values[0], event.values[1], event.values[2]);

                    if (newValue != linkedList.getLast() && Start <= 3) {

                        if (Start == 1) {
                            MiddlePosition = newValue;
                            linkedList.addLast(newValue);
                            //System.out.println("Middle" + newV
                            // alue);
                        } else if (Start == 2) {
                            linkedList.addLast(newValue);
                            //System.out.println("Last" + newValue);
                        }
                        Start++;
                        break;
                    }
                    if (newValue == linkedList.getLast()) {
                        break;
                    }
                    break;

                } else if (Start > 0 && (getMax(event.values[0], event.values[1], event.values[2])) == 2 && linkedList.size() > 1) {
                    RecordGest();
                    linkedList.clear();
                    Start = 0;
                    break;

                } else if (Start > 0 && maxStart == 2) {
                    break;

                } else {
                    System.out.println("Wrong Gesture");
                    break;
                }
            }
            /*xTextView.setText(Double.toString(x));
            yTextView.setText(Double.toString(y));
            zTextView.setText(Double.toString(z));*/
        }
    };

    public int getMax(double x, double y, double z) {
        int max = 0;
        boolean minusX = false,
                minusY = false,
                minusZ = false;

        if (x < 0) {
            minusX = true;
            x = -1 * x;
        }
        if (y < 0) {
            minusY = true;
            y = -1 * y;
        }
        if (z < 0) {
            minusZ = true;
            z = -1 * z;
        }

        if (x > y && x > z)
            max = 1;
        else if (y > z)
            max = 2;
        else if (z > y)
            max = 3;
        else {
            System.out.println("");
        }

        if (max == 1 && minusX) {
            max = 4;
        }
        if (max == 2 && minusY) {
            max = 5;
        }
        if (max == 3 && minusZ) {
            max = 6;
        }

        return max;
    }

    public void RecordGest() {

        if (linkedList.size() == 2) {
            if (linkedList.getLast() == 1 && linkedList.getFirst() == 2) {
                countGesture++;
                gestureSequence.append("1");
                value.setText("Left Move");
            } else if (linkedList.getLast() == 3 && linkedList.getFirst() == 2) {
                countGesture++;
                gestureSequence.append("2");
                value.setText("Upward Move");
            } else if (linkedList.getLast() == 4 && linkedList.getFirst() == 2) {
                countGesture++;
                gestureSequence.append("3");
                value.setText("Right Move");
            } else if (linkedList.getLast() == 6 && linkedList.getFirst() == 2) {
                countGesture++;
                gestureSequence.append("4");
                value.setText("Downward Move");
            } else {
                value.setText("Wrong Gesture");
            }
        } else if (linkedList.size() == 3) {
            if (MiddlePosition == 1) {
                if (linkedList.getLast() == 6 && linkedList.getFirst() == 2) {
                    countGesture++;
                    gestureSequence.append("5");
                    value.setText("Left to Left");
                } else if (linkedList.getLast() == 3 && linkedList.getFirst() == 2) {
                    countGesture++;
                    gestureSequence.append("6");
                    value.setText("Left To Right");
                }
            } else if (MiddlePosition == 4) {
                if (linkedList.getLast() == 6 && linkedList.getFirst() == 2) {
                    countGesture++;
                    gestureSequence.append("7");
                    value.setText("Right to Left");
                } else if (linkedList.getLast() == 3 && linkedList.getFirst() == 2) {
                    countGesture++;
                    gestureSequence.append("8");
                    value.setText("Right to Right");
                }
            } else if (MiddlePosition == 3) {
                if (linkedList.getLast() == 1 && linkedList.getFirst() == 2) {
                    countGesture++;
                    gestureSequence.append("9");
                    value.setText("Upward To Left");
                } else if ((Integer)linkedList.getLast() == 4 && (Integer)linkedList.getFirst() == 2) {
                    countGesture++;
                    gestureSequence.append("0");
                    value.setText("Upward to Right");
                }
            } else {
                value.setText("Wrong Gesture");
            }
        }
    }
}
