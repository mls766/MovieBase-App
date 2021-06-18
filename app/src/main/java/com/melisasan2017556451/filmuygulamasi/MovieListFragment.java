package com.melisasan2017556451.filmuygulamasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melisasan2017556451.filmuygulamasi.R;
import com.melisasan2017556451.filmuygulamasi.adapters.MovieListAdapter;
import com.melisasan2017556451.filmuygulamasi.adapters.MoviesAdapter;
import com.melisasan2017556451.filmuygulamasi.databinding.FragmentMovieListBinding;
import com.melisasan2017556451.filmuygulamasi.models.Movies;
import com.melisasan2017556451.filmuygulamasi.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;
import utils.Info;


//This fragment is annotated with @AndroidEntryPoint which means that hilt should provide all the dependencies to this fragment that it asks for.
@AndroidEntryPoint
public class MovieListFragment extends Fragment {

    //adding network security config before running app
    private FragmentMovieListBinding binding;
    private MovieListAdapter movieListAdapter;
    private HashMap<String, String> queryMap;
    private String moviesCategory="";
    private ArrayList<Movies> moviesList;
    private MovieListViewModel movieListViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar6);


        queryMap = new HashMap<>();
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        MovieListFragmentArgs args = MovieListFragmentArgs.fromBundle(getArguments());
        moviesCategory = args.getMovieCategory();



        queryMap.put("api_key", Info.API_KEY);
        queryMap.put("page","1");


        ConfigureRecyclerView();
        Observe();
        getMoviesList();



    }


    private void ConfigureRecyclerView() {

        binding.MovieListRv.setLayoutManager(new GridLayoutManager(getContext(),1));
        movieListAdapter = new MovieListAdapter(getContext(),moviesList);
        binding.MovieListRv.setAdapter(movieListAdapter);

    }

    private void Observe() {
        switch (moviesCategory){
            case Info.Popular:
                movieListViewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Popular Movies");
                    }
                });
                break;
            case Info.TopRated:
                movieListViewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Top Rated Movies");
                    }
                });
                break;
            case Info.Latest:
                movieListViewModel.getLatestMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Latest Movies");
                    }
                });
                break;
            case Info.Upcoming:
                movieListViewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Upcoming Movies");
                    }
                });
                break;
        }
    }

    private void getMoviesList() {
        switch (moviesCategory){
            case Info.Popular:
                movieListViewModel.getPopularMovies(queryMap);
                break;
            case Info.TopRated:
                movieListViewModel.getTopRatedMovies(queryMap);
                break;
            case Info.Latest:
                movieListViewModel.getLatestMovies(queryMap);
                break;
            case Info.Upcoming:
                movieListViewModel.getUpcomingMovies(queryMap);
                break;
        }
    }


    //onDestroyView allows the fragment to clean up resources associated with its View.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

