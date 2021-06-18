package com.melisasan2017556451.filmuygulamasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.melisasan2017556451.filmuygulamasi.models.Genres;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholder>{
    private Context mcontext;
    private List<Genres> categoryList;

    public SearchAdapter(Context mcontext, List<Genres> categoryList) {
        this.mcontext = mcontext;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card_tasarim,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
       Genres genre= categoryList.get(position);

       holder.textViewName.setText(genre.getName());

       holder.card_search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private CardView card_search;
        private TextView textViewName;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            card_search = itemView.findViewById(R.id.card_search);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }
}
