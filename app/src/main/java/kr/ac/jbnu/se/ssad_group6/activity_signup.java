package kr.ac.jbnu.se.ssad_group6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_signup extends AppCompatActivity {
    private EditText emailText;
    private EditText pwdText;
    private EditText checkText;
    private Button signup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailText = (EditText) findViewById(R.id.emailInput);
        pwdText = (EditText) findViewById(R.id.passwordInput);
        checkText = (EditText) findViewById(R.id.passwordcheckInput);
        signup = (Button) findViewById(R.id.signupButton);


        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailText.getText().toString().trim();
                final String pwd = pwdText.getText().toString().trim();
                final String check = checkText.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();

                if (!emailText.equals("") && !pwdText.equals("") && !checkText.equals("")) {
                    // 이메일과 비밀번호가 공백이 아닌 경우
                    if (pwd.equals(check)) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                                .addOnCompleteListener(activity_signup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(activity_signup.this, activity_login.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(activity_signup.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(activity_signup.this, "비밀번호 확인이 틀립니다.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(activity_signup.this, "이메일, 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}