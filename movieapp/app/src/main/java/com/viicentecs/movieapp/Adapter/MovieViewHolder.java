package com.viicentecs.movieapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viicentecs.movieapp.R;
import com.viicentecs.movieapp.interfaces.OpenDetailsMovie;

public class MovieViewHolder extends RecyclerView.ViewHolder{

    public ImageView ivPoster;
    public TextView tvTittle;
    public TextView tvDate;
    public TextView tvQualification;

    public MovieViewHolder(@NonNull View itemView, OpenDetailsMovie openDetailsMovie) {
        super(itemView);
        ivPoster = itemView.findViewById(R.id.iv_poster_tittle_movie_item);
        tvTittle = itemView.findViewById(R.id.tv_tittle_movie_item);
        tvDate = itemView.findViewById(R.id.tv_date_movie_item);
        tvQualification = itemView.findViewById(R.id.tv_qualification_movie_item);
        itemView.setOnClickListener(v -> openDetailsMovie.openDetailsMovie(getAdapterPosition()));
    }

}
