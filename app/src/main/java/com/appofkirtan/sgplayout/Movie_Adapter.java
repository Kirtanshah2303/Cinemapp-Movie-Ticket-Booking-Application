package com.appofkirtan.sgplayout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.MovieViewHolder> implements Filterable {

    private ArrayList<MovieItem> mMovieList;
    private  ArrayList<MovieItem> MovieListFull;
    Context mContext;

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
        CardView movieparent;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.MovieImage_cardView);
            mTextView = itemView.findViewById(R.id.Movie_name_cardView);
            movieparent = itemView.findViewById(R.id.movie_parent);
        }


    }

    public Movie_Adapter(ArrayList<MovieItem> MovieList,Context context){
        mMovieList = MovieList;
        MovieListFull = new ArrayList<>(MovieList);
        mContext=context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        MovieViewHolder evh = new MovieViewHolder(v);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieItem currentItem = mMovieList.get(position);

        holder.mImageView.setImageResource(currentItem.getMovieResource());
        holder.mTextView.setText(currentItem.getMovie_name());
        holder.movieparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,currentItem.getMovie_name(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext,demothirdActivity.class);
                intent.putExtra("MovieName",currentItem.getMovie_name());
                intent.putExtra("MovieImage",currentItem.getMovieResource());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    @Override
    public Filter getFilter() {
        return movieFilter;
    }

    private Filter movieFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MovieItem> filteredList = new ArrayList<>();

            if(charSequence == null | charSequence.length() ==0){
                filteredList.addAll(MovieListFull);
            }else{

                String filteredPattern = charSequence.toString().toLowerCase().trim();

                for(MovieItem item : MovieListFull){
                    if(item.getMovie_name().toLowerCase().contains(filteredPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {

            mMovieList.clear();
            mMovieList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
