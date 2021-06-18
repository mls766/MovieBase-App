
package com.melisasan2017556451.filmuygulamasi;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.melisasan2017556451.filmuygulamasi.adapters.SearchListAdapter;
import com.melisasan2017556451.filmuygulamasi.databinding.FragmentSecondLayoutBinding;
import com.melisasan2017556451.filmuygulamasi.models.Movies;
import com.melisasan2017556451.filmuygulamasi.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;
import utils.Info;
//This fragment finds the list of the movies being searched

//This fragment is annotated with @AndroidEntryPoint which means that hilt should provide all the dependencies to this fragment that it asks for.
@AndroidEntryPoint
public class FragmentSecond extends Fragment {
    private SearchListAdapter searchListAdapter;
    private ArrayList<Movies> movies;
    private FragmentSecondLayoutBinding binding;
    private HashMap<String, String> queryMap;
    private String query;


    private MovieListViewModel movieListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSecondLayoutBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movies = new ArrayList<>();

        //Using the "query" argument of FragmentSecond
         FragmentSecondArgs args = FragmentSecondArgs.fromBundle(getArguments());
         query = args.getQuery();
         //set Queried text into queryText
         binding.queryText.setText(query);

        queryMap = new HashMap<>();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        queryMap.put("api_key", Info.API_KEY);
        queryMap.put("query",query);

        observeData();

        //getSearchedMovies from View Model and put queryMap as a parameter
        movieListViewModel.getSearchedMovies(queryMap);

        ConfigureRecyclerView();


        //
        binding.queryText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //queryText has imeOptions="actionSearch"
                    query = binding.queryText.getText().toString().trim().toLowerCase();
                    queryMap.clear();
                    queryMap.put("api_key", Info.API_KEY);
                    queryMap.put("query",query);

                    movieListViewModel.getSearchedMovies(queryMap);
                }
                //return false will  defines if user consume the click search or not
                //while returning false it executes this action
                return false;
            }
        });

        //When click on the search button
        binding.querySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                query = binding.queryText.getText().toString().trim().toLowerCase();
                queryMap.clear();
                queryMap.put("api_key", Info.API_KEY);
                queryMap.put("query",query);

                movieListViewModel.getSearchedMovies(queryMap);
            }
        });


}

        //Put data into RecyclerView
        private void ConfigureRecyclerView() {
            binding.MoviesearchRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            searchListAdapter = new SearchListAdapter(getContext(), movies);
            binding.MoviesearchRv.setAdapter(searchListAdapter);
        }


        private void observeData() {
        //Observing MutableLiveData<ArrayList<Movies>>
        movieListViewModel.getQueriesMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {

                        searchListAdapter.setList(movies);
                        //set a list with movies

                }
        });
     }



    //onDestroyView allows the fragment to clean up resources associated with its View.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



