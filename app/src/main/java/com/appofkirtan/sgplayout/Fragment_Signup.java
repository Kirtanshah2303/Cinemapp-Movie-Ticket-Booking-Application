package com.appofkirtan.sgplayout;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Fragment_Signup extends Fragment {
    EditText mUsername,mEmail,mPassword;
    Button SignupBtn;
    FirebaseAuth mauth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up,null);

        mUsername=(EditText) view.findViewById(R.id.Usernamesignup);
        mEmail=(EditText) view.findViewById(R.id.emailsignup);
        mPassword=(EditText) view.findViewById(R.id.editTextTextPassword);
        SignupBtn=(Button)view.findViewById(R.id.registerbtn);
        mauth=FirebaseAuth.getInstance();
        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString().trim();
                FirebaseUser user=mauth.getCurrentUser();
                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is required");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    mEmail.setError("Enter valid email");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password required");
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("Password must be more than 6 characters");
                    return;
                }
                mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            mauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getActivity(),"We have sent you a verification link on registered email.Verify email to signup.",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(getActivity(),"Already Registred",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                }
        });

        return view;
        //        return inflater.inflate(R.layout.sign_up,null);
    }
}

