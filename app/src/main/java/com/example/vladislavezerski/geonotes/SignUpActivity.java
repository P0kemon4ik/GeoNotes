package com.example.vladislavezerski.geonotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    static final int RC_SIGN_IN = 1;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

   public EditText ETemail;
   public EditText ETpassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ETemail = (EditText) findViewById(R.id.txt_email);
        ETpassword = (EditText) findViewById(R.id.txt_password);

        findViewById(R.id.btn_log_in).setOnClickListener(this);
        findViewById(R.id.btn_sign_up).setOnClickListener(this);

       /* Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        Button btnLogIn = (Button) findViewById(R.id.btn_log_in);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_log_in) {
            logining(ETemail.getText().toString(), ETpassword.getText().toString());
        } else if (v.getId() == R.id.btn_sign_up){
            registration(ETemail.getText().toString(), ETpassword.getText().toString());
        }
    }

    public  void logining(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка входа", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
