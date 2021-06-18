package com.melisasan2017556451.filmuygulamasi;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melisasan2017556451.filmuygulamasi.databinding.ProfileBinding;
import com.melisasan2017556451.filmuygulamasi.models.User;

public class Profile extends Fragment {

    private ProfileBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.toolbar8.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar8);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String email = userProfile.getEmail();
                    String name = userProfile.getName();
                    String phone = userProfile.getPhone();

                    binding.mail.setText(email);
                    binding.name.setText(name);
                    binding.phone.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),"Something wrong happened.",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
