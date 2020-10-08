package com.appofkirtan.sgplayout;

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
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    private EditText passwordemail;
    private Button passwordreset;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        passwordemail=(EditText)findViewById(R.id.reset_email);
        passwordreset=(Button)findViewById(R.id.reset_button);
        mauth= FirebaseAuth.getInstance();

        passwordreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resetmail=passwordemail.getText().toString().trim();
                if(TextUtils.isEmpty(resetmail))
                {
                    passwordemail.setError("Email is required");
                    return;
                }
                else
                {
                    mauth.sendPasswordResetEmail(resetmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(forgot_password.this,Fragment_Signin.class));
                                Toast.makeText(forgot_password.this,"Password reset email sent!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(forgot_password.this,"Email is not registered.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}