package kr.ac.jbnu.se.ssad_group6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class activity_autoLogin extends AppCompatActivity {

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_login);

        if(SaveSharedPreference.getUserName(activity_autoLogin.this).length() == 0) {
            // call Login Activity
            intent = new Intent(activity_autoLogin.this, activity_login.class);
            startActivity(intent);
            this.finish();
        } else {
            // Call Next Activity
            intent = new Intent(activity_autoLogin.this, activity_main.class);
            intent.putExtra("STD_NUM", SaveSharedPreference.getUserName(this).toString());
            startActivity(intent);
            this.finish();
        }
    }
}
