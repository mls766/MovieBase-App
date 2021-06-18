package com.melisasan2017556451.filmuygulamasi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.melisasan2017556451.filmuygulamasi.databinding.FragmentFirstLayoutBinding;
import com.melisasan2017556451.filmuygulamasi.databinding.FragmentThirdLayoutBinding;

import java.util.ArrayList;

import utils.Info;

public class FragmentThird extends Fragment {
    private FragmentThirdLayoutBinding binding;
    String mTitle[] = {"Favorites","Watched","Want to Watch"};
    int mPictures[] = { R.drawable.ic_round_favorite, R.drawable.ic_watched, R.drawable.ic_want_to_watch};
    private NavController navController;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentThirdLayoutBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialization of the Toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar3);
        binding.toolbar3.setTitle("");

        MyAdapter adapter = new MyAdapter(getContext(), mTitle, mPictures);
        binding.listview.setAdapter(adapter);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    NavDirections action =
                          FragmentThirdDirections.actionFragmentThirdToFavoriteFragment();
                            Navigation.findNavController(view).navigate(action);
                }
                if (position == 1) {
                    NavDirections action =
                            FragmentThirdDirections.actionFragmentThirdToWatchedFragment();
                    Navigation.findNavController(view).navigate(action);
                }
                if (position == 2) {
                    NavDirections action =
                            FragmentThirdDirections.actionFragmentThirdToWantToWatchFragment();
                    Navigation.findNavController(view).navigate(action);
                }
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        int rPictures[];

        MyAdapter(Context c, String title[], int pictures[]){
            super(c,R.layout.lists, R.id.textViewlist,title);
            this.context = c;
            this.rTitle = title;
            this.rPictures = pictures;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = layoutInflater.inflate(R.layout.lists,parent,false);
            ImageView pictures= rootView.findViewById(R.id.symbol);
            TextView myTitle= rootView.findViewById(R.id.textViewlist);

            pictures.setImageResource(rPictures[position]);
            myTitle.setText(rTitle[position]);


            return rootView;

        }
    }





}
