package com.example.nipu.example;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseActivity.AndroidId=android.os.Build.SERIAL;
    }



    public void registration(View view)
    {
        BaseActivity.SignIn=false;
        BaseActivity.gesture_iteration=2;
        BaseActivity.tap_iteration=2;
        startActivity(new Intent(MainActivity.this,Selection.class));
    }

    public void login(View view)
    {
        BaseActivity.SignIn=true;
        BaseActivity.gesture_iteration=1;
        BaseActivity.tap_iteration=1;
        startActivity(new Intent(MainActivity.this,Selection.class));
    }



}
