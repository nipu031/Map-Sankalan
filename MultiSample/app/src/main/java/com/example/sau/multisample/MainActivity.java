package com.example.sau.multisample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;*/

public class MainActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseActivity.AndroidId=android.os.Build.SERIAL;
        /*final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout androididWrapper = (TextInputLayout) findViewById(R.id.androididWrapper);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.emailWrapper);*/
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
