package com.melisasan2017556451.filmuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.melisasan2017556451.filmuygulamasi.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;


//This fragment is annotated with @AndroidEntryPoint which means that hilt should provide all the dependencies to this fragment that it asks for.
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Getting the Navigation Controller

        navController = Navigation.findNavController(MainActivity.this, R.id.fragment);

        binding.bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fragmentFirst:
                        navController.navigate(R.id.fragmentFirst);
                        return true;
                    case R.id.fragmentSecond:
                        navController.navigate(R.id.fragmentSecond);
                        return true;
                    case R.id.fragmentThird:
                        navController.navigate(R.id.fragmentThird);
                        return true;
                    case R.id.fragmentFourth:
                        navController.navigate(R.id.fragmentFourth);
                        return true;
                }
                return false;
            }
        });
    }
}



