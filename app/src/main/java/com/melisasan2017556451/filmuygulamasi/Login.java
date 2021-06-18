package com.melisasan2017556451.filmuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melisasan2017556451.filmuygulamasi.databinding.ActivityLoginBinding;
import com.melisasan2017556451.filmuygulamasi.databinding.ActivityRegisterBinding;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fAuth = FirebaseAuth.getInstance();


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.Email.getText().toString().trim();
                String password = binding.Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    binding.Email.setError("Lütfen E-mail adresinizi giriniz.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    binding.Email.setError("Lütfen Şifre giriniz.");
                    return;
                }
                if(password.length() <6){
                    binding.Password.setError("Şifreniz 6 karakterden büyük olmalı");
                    return;
                }
                //authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if the login is sattisful
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Giriş Yapıldı", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = fAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Hata oluştu!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}