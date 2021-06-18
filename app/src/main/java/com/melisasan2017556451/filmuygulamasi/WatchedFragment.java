package com.melisasan2017556451.filmuygulamasi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.melisasan2017556451.filmuygulamasi.adapters.FavoriteAdapter;
import com.melisasan2017556451.filmuygulamasi.adapters.WatchedAdapter;
import com.melisasan2017556451.filmuygulamasi.databinding.WatchedListBinding;
import com.melisasan2017556451.filmuygulamasi.db.Watched;
import com.melisasan2017556451.filmuygulamasi.viewmodels.WatchedViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

//Watchedfragment will show the watched movies which have saved using Room.

//This fragment is annotated with @AndroidEntryPoint which means that hilt should provide all the dependencies to this fragment that it asks for.
@AndroidEntryPoint
public class WatchedFragment  extends Fragment {

    private WatchedListBinding binding;
    private WatchedViewModel watchedViewModel;
    private WatchedAdapter adapter;
    private List<Watched> watchedList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WatchedListBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        watchedViewModel = new ViewModelProvider(this).get(WatchedViewModel.class);

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar2);
        binding.toolbar2.setTitle("Watched Movies");

        initRecyclerView();
        observeData();

        binding.deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build an alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Do you really want to delete all your Watched list?");

                //Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //user clicked yes button
                                watchedViewModel.deleteAllWatched();
                                Toast.makeText(getContext(), "Your want to watch list cleared!", Toast.LENGTH_SHORT).show();
                                watchedList.clear();
                                adapter.setList(watchedList);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //user clicked no button
                                break;

                        }
                    }
                };
                builder.setPositiveButton("Yes",dialogClickListener);
                builder.setNegativeButton("No",dialogClickListener);

                AlertDialog dialog = builder.create();
                //display the alertview on interface
                dialog.show();
            }

        });
    }

    private void observeData() {
        watchedViewModel.getWatchedListMovieList().observe(getViewLifecycleOwner(), new Observer<List<Watched>>() {
            @Override
            public void onChanged(List<Watched> watchedMovies) {
                if (watchedMovies.size() == 0 || watchedMovies == null){
                    binding.placeHolderText.setVisibility(View.VISIBLE);

                }
                else {
                    binding.placeHolderText.setVisibility(View.GONE);
                    adapter.setList(watchedMovies);
                    watchedList = watchedMovies;
                }
            }
        });
    }




    private void initRecyclerView() {
        binding.MovieListRv.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new WatchedAdapter(getContext(),watchedList);
        binding.MovieListRv.setAdapter(adapter);

    }


    //onDestroyView allows the fragment to clean up resources associated with its View.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


