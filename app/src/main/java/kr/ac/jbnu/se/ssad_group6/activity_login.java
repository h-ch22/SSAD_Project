package kr.ac.jbnu.se.ssad_group6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_login extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(activity_login.this, activity_main.class);
                startActivity(intent);
                finish();
            }
        }
    };
    private Button login;
    private Button signup;
    private EditText emailText;
    private EditText pwdText;
    public CheckBox autoLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button) findViewById(R.id.loginButton);
        signup=(Button) findViewById(R.id.signupButton);
        emailText=(EditText) findViewById(R.id.emailInput);
        pwdText=(EditText) findViewById(R.id.passwordInput);
        autoLogin=(CheckBox) findViewById(R.id.checkbox);
        firebaseAuth=firebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString().trim();
                String pwd = pwdText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때
                                    if (autoLogin.isChecked()) { //체크박스가 체크되어 있으면
                                        SaveSharedPreference.setUserName(activity_login.this, emailText.getText().toString()); //값 주기
                                    }
                                    Intent intent = new Intent(activity_login.this, activity_main.class);
                                    startActivity(intent);
                                } else {//실패했을때
                                    Toast.makeText(activity_login.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login.this, activity_signup.class);
                startActivity(intent);
            }
        });
    }
}