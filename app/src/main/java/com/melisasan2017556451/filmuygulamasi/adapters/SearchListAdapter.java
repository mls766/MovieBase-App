package com.melisasan2017556451.filmuygulamasi.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.melisasan2017556451.filmuygulamasi.FragmentFirst;
import com.melisasan2017556451.filmuygulamasi.FragmentFirstDirections;
import com.melisasan2017556451.filmuygulamasi.FragmentSecondDirections;
import com.melisasan2017556451.filmuygulamasi.MovieListFragmentDirections;
import com.melisasan2017556451.filmuygulamasi.R;
import com.melisasan2017556451.filmuygulamasi.databinding.MoviesCardBinding;
import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.ArrayList;
import java.util.List;

import utils.Info;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>{

    private ArrayList<Movies> mMovies;
    private Context context;
    private MoviesCardBinding binding;
    private String temp;

    public SearchListAdapter(Context context, ArrayList<Movies> mMovies) {
        this.mMovies = mMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = MoviesCardBinding.inflate(inflater,parent,false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        temp = "";

        for (int i = 0; i < mMovies.get(position).getGenre_ids().size(); i++){
            if(i ==  mMovies.get(position).getGenre_ids().size() -1)
                temp+= Info.getGenreMap().get(mMovies.get(position).getGenre_ids().get(i));
            else
                temp+= Info.getGenreMap().get(mMovies.get(position).getGenre_ids().get(i)) + " , ";
        }

        holder.binding.movieCardRating.setRating(mMovies.get(position).getVote_average()/2);
        holder.binding.movieCardGenre.setText(temp);
        holder.binding.movieCardTitle.setText(mMovies.get(position).getTitle());
        String[] movieYear = mMovies.get(position).getRelease_date()
                .split("-");
        holder.binding.movieCardYear.setText(movieYear[0]);

        //Using Glide library to display image
        Glide.with(context)
                // [put the general directory where the images are stored]+ image of the parced movie
                .load("https://image.tmdb.org/t/p/w500/" +mMovies.get(position).getPoster_path()) //image url
                .into(holder.binding.movieCardPoster);


        //When the movie item is clicked
        holder.binding.movieCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSecondDirections.ActionFragmentSecondToMovieDetailFragment action =
                        FragmentSecondDirections.actionFragmentSecondToMovieDetailFragment(mMovies.get(position).getId());
                Navigation.findNavController(v).navigate(action);

            }
        });


    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private MoviesCardBinding binding;

        public SearchViewHolder(MoviesCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setList(ArrayList<Movies> mMovies){
        this.mMovies = mMovies;
        notifyDataSetChanged();
        //notifyDataSetChanged() works every time the list is updated
    }


}
