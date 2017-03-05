package com.example.nipu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sau on 30/5/16.
 */
public class Selection extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);

        s1 = (Spinner)findViewById(R.id.spinner1);
        s2 = (Spinner)findViewById(R.id.spinner2);
        s1.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

        // Get Authentication Type
        String sp1= String.valueOf(s1.getSelectedItem());
        //Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();

        if(sp1.contentEquals("Tap Based Authentication")) {

          /*  MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tap);
            mp.start();
*/
            List<String> list = new ArrayList<String>();
            list.add("2*1");
            list.add("2*2");
            list.add("2*3");
            list.add("3*3");
            list.add("4*3");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Gesture Based Authentication")) {
            List<String> list = new ArrayList<String>();
            list.add("Select Me");
            /*list.add("Three Gesture");
            list.add("Four Gesture");
            list.add("Five Gesture");*/
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Multi-Factor Authentication")) {
            List<String> list = new ArrayList<String>();
            /*list.add("Two tap & Two gesture");
            list.add("Three Tap & Three gesture");
            list.add("Four Tap & Four gesture");*/
            list.add("Anything");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    public void selectedtech(View view)
    {
        /*Toast.makeText(getApplicationContext(), String.valueOf(s1.getSelectedItem()),Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), String.valueOf(s2.getSelectedItem()),Toast.LENGTH_LONG).show();
*/
        String selectedFromListOne=String.valueOf(s1.getSelectedItem());
        String selectedFromListTwo=String.valueOf(s2.getSelectedItem());


        if (selectedFromListOne.contentEquals("Tap Based Authentication"))
        {
            BaseActivity.Auth_type="Tap";
           /* MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tap);
            mp.start();*/


            if (selectedFromListTwo.contentEquals("2*1"))
            {

                /*MediaPlayer mpo = MediaPlayer.create(getApplicationContext(), R.raw.threeinto3);
                mpo.start();*/

                BaseActivity.startRegtime=System.currentTimeMillis();
                BaseActivity.Sub_Type="Select Me";
                BaseActivity.GridSize="2*1";
                startActivity(new Intent(Selection.this,TwoIntoOne.class));
                finish();
            }

            else if (selectedFromListTwo.contentEquals("2*2"))
            {

                /*MediaPlayer mpo = MediaPlayer.create(getApplicationContext(), R.raw.threeinto3);
                mpo.start();*/

                BaseActivity.startRegtime=System.currentTimeMillis();
                BaseActivity.Sub_Type="Select Me";
                BaseActivity.GridSize="2*2";
                startActivity(new Intent(Selection.this,TwoIntoTwo.class));
                finish();
            }

            else if (selectedFromListTwo.contentEquals("2*3"))
            {

                /*MediaPlayer mpo = MediaPlayer.create(getApplicationContext(), R.raw.threeinto3);
                mpo.start();*/

                BaseActivity.startRegtime=System.currentTimeMillis();
                BaseActivity.Sub_Type="Select Me";
                BaseActivity.GridSize="2*3";
                startActivity(new Intent(Selection.this,TwoIntoThree.class));
                finish();
            }

            else if (selectedFromListTwo.contentEquals("4*3"))
            {

                /*MediaPlayer mpo = MediaPlayer.create(getApplicationContext(), R.raw.threeinto3);
                mpo.start();*/

                BaseActivity.startRegtime=System.currentTimeMillis();
                BaseActivity.Sub_Type="Select Me";
                BaseActivity.GridSize="4*3";
                startActivity(new Intent(Selection.this,FourIntoThree.class));
                finish();
            }

            else if (selectedFromListTwo.contentEquals("3*3"))
            {

                /*MediaPlayer mpo = MediaPlayer.create(getApplicationContext(), R.raw.threeinto3);
                mpo.start();*/

                BaseActivity.startRegtime=System.currentTimeMillis();
                BaseActivity.Sub_Type="Select Me";
                BaseActivity.GridSize="3*3";
                startActivity(new Intent(Selection.this,ThreeIntoThree.class));
                finish();
            }

            else {
                Toast.makeText(getApplicationContext(),"Wrong Selection",Toast.LENGTH_LONG).show();
            }

        }

        else if (selectedFromListOne.contentEquals("Gesture Based Authentication"))
        {


            BaseActivity.Auth_type="Gesture";
            if (selectedFromListTwo.contentEquals("Select Me"))
            {
                BaseActivity.Sub_Type="Select Me";
                BaseActivity.startRegtime=System.currentTimeMillis();
                startActivity(new Intent(Selection.this,Gesture.class));
            }

            else {
                Toast.makeText(getApplicationContext(),"Wrong Selection",Toast.LENGTH_LONG).show();
            }
        }

        else if (selectedFromListOne.contentEquals("Multi-Factor Authentication"))
        {


            BaseActivity.Auth_type="Multifactor";

            if (selectedFromListTwo.contentEquals("Anything"))
            {
                BaseActivity.Coming_From_Multiple=4;
                BaseActivity.Sub_Type="Anything";
                BaseActivity.startRegtime=System.currentTimeMillis();
                startActivity(new Intent(Selection.this,ThreeIntoThree.class));
            }

            else {
                Toast.makeText(getApplicationContext(),"Wrong Selection",Toast.LENGTH_LONG).show();
            }

        }

        else
        {
            Toast.makeText(getApplicationContext(),"Wrong Selection",Toast.LENGTH_LONG).show();
        }

    }

}
