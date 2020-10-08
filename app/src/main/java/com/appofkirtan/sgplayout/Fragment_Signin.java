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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Fragment_Signin extends Fragment {
    EditText mEmail,mPassword;
    Button SigninBtn;
    //forgot
    TextView forgotbtn;
    FirebaseAuth mauth;
    public static String emailuser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sign_in,null);
        mEmail=(EditText) view.findViewById(R.id.Usernamesignin);
        mPassword=(EditText) view.findViewById(R.id.editTextTextPassword1);
        SigninBtn=(Button)view.findViewById(R.id.btn_signin);
        //forgot
        forgotbtn=(TextView)view.findViewById(R.id.forgotpassword);

        //emailuser = email;
        mauth=FirebaseAuth.getInstance();
        SigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=mEmail.getText().toString();
                String password=mPassword.getText().toString().trim();

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
                mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if(mauth.getCurrentUser().isEmailVerified())
                            {
                                Toast.makeText(getContext(),"Verified",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getActivity(),demoaAtication.class);
                                getActivity().finish();
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                              //  intent.putExtra("UserEmail",email);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getContext(),"Please verify email to sign in.",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
});
        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),forgot_password.class));
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mauth.getCurrentUser()!=null)
        {
            if(mauth.getCurrentUser().isEmailVerified())
            {
                getActivity().finish();
                Intent intent = new Intent(getActivity(),demoaAtication.class);
              //  intent.putExtra("UserEmail",emailuser);
                startActivity(intent);

                //startActivity(new Intent(getActivity(),demoaAtication.class));
            }
        }
    }
}
