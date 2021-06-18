package com.melisasan2017556451.filmuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;
import com.melisasan2017556451.filmuygulamasi.databinding.ActivityRegisterBinding;
import com.melisasan2017556451.filmuygulamasi.models.User;


public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    FirebaseAuth fAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        fAuth = FirebaseAuth.getInstance();

        //if user is already loged in:
        if(fAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }





        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Email = binding.Email.getText().toString().trim();
                String password = binding.Password.getText().toString().trim();
                String Name = binding.FullName.getText().toString().trim();
                String Phone = binding.Phone.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    binding.Email.setError("Please enter your e-mail address.");
                    binding.Email.requestFocus();
                    return;
                }
               /* if(Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    binding.Email.setError("Please provide valid e-mail!");
                    binding.Email.requestFocus();
                    return;
                }

                */
                if(TextUtils.isEmpty(password)){
                    binding.Password.setError("Please enter your password.");
                    binding.Password.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Name)){
                    binding.FullName.setError("Please enter your full name.");
                    binding.FullName.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Phone)){
                    binding.Phone.setError("Please enter your phone number.");
                    binding.Phone.requestFocus();
                    return;
                }
                if(password.length() < 6){
                    binding.Password.setError("Your password must includes at least 7 character");
                    binding.Password.requestFocus();
                    return;
                }

                //register the firebase

                fAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //registeration is successful or not
                        if (task.isSuccessful()) {
                            User user = new User(Name, Phone, Email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "User created succsessfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    } else {
                                        Toast.makeText(Register.this, "An errror occured!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }else{
                            Toast.makeText(Register.this, "An errror occured!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        binding.Alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }
}