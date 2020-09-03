package com.example.calculatorbasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CalLogic extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_logic);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);

        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email=email.getText().toString();
                String text_password=password.getText().toString();
                if(TextUtils.isEmpty(text_email )|| TextUtils.isEmpty(text_password)){
                    Toast.makeText(CalLogic.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
                else if(text_password.length()<6){
                    Toast.makeText(CalLogic.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(text_email,text_password);
                   startActivity(new Intent(CalLogic.this,Main2Activity.class));
                }

            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CalLogic.this,"Register was successfull",Toast.LENGTH_SHORT).show();


                }
                else{
                    Toast.makeText(CalLogic.this,"Register failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
