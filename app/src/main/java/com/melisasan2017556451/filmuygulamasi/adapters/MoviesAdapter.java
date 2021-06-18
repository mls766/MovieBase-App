package com.melisasan2017556451.filmuygulamasi.adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.melisasan2017556451.filmuygulamasi.FragmentFirstDirections;
import com.melisasan2017556451.filmuygulamasi.R;
import com.melisasan2017556451.filmuygulamasi.databinding.ItemMovieBinding;
import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.ArrayList;

//This class extends RecyclerView.Adapter class and override its unimplemented methods
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieMainViewHolder>{


    private ItemMovieBinding binding;
    private ArrayList<Movies> mMovies;
    private Context context;


    public MoviesAdapter(Context context,ArrayList<Movies>  mMovies) {
        this.mMovies =mMovies;
        this.context = context;

    }

    @NonNull
    @Override
    // onCreateViewHolder() methods inflates the item_movie.xml
    public MovieMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //bu metod ile cardtasarimtutucu sınıfının contructor'ına gönderilecek card_tasarımın görsel nesnelere erişilir
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ItemMovieBinding.inflate(inflater, parent, false);
        return new MovieMainViewHolder(binding);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    // onBindViewHolder() method each data items are set to each row
    //Each individual element in the list is defined by a view holder object
    public void onBindViewHolder(@NonNull MovieMainViewHolder holder, int position) {
        //bu metoddaki holder nesnesi ile card tasarımına erişirip,işlemler yapılır.
             holder.binding.movieItemLayout.setClipToOutline(true);
             holder.binding.movieItemTitle.setText(mMovies.get(position).getTitle());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path()) //image url
                    .into(holder.binding.movieItemPoster);

            //When the movie item is clicked
            holder.binding.movieItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentFirstDirections.ActionFragmentFirstToMovieDetailFragment action =
                            FragmentFirstDirections.actionFragmentFirstToMovieDetailFragment(mMovies.get(position).getId());
                    Navigation.findNavController(v).navigate(action);

                }
            });

    }

    @Override
    //RecyclerView calls this method to get the size of the data set
    //RecyclerView uses this to determine when there are no more items that can be displayed
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }


    //After the view holder is created, the RecyclerView binds it to its data
    public class MovieMainViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding binding;

        public MovieMainViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }



    public void setMoviesList(ArrayList<Movies> mMovies){
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }


}