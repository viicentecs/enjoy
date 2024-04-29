package com.viicentecs.movieapp.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.viicentecs.movieapp.EnjoyApp;
import com.viicentecs.movieapp.Models.Movie;
import com.viicentecs.movieapp.Network.RetrofitClient;
import com.viicentecs.movieapp.R;
import com.viicentecs.movieapp.Utils.DatesUtils;
import com.viicentecs.movieapp.interfaces.OpenDetailsMovie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private OpenDetailsMovie openDetailsMovie;
    private final HashMap<String,Bitmap> imagesMovies = new HashMap<>();
    private ArrayList<Movie> moviesArrayList = new ArrayList<>();

    public MovieAdapter(OpenDetailsMovie openDetailsMovie){
        this.openDetailsMovie = openDetailsMovie;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false),openDetailsMovie);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = moviesArrayList.get(position);
        if(movie != null){
            holder.tvTittle.setText(movie.getTitle());
            holder.tvDate.setText(DatesUtils.convertMovieDateToEasyFormat(movie.getReleaseDate()));
            String votes = movie.getVoteAverage() + "%";
            holder.tvQualification.setText(votes);
            validaImage(movie,holder.ivPoster);
        }
    }
    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Movie> moviesList){
        this.moviesArrayList = moviesList;
        notifyDataSetChanged();
    }

    public Movie getMovieByPosition(int position){
        return moviesArrayList.get(position);
    }

    public void validaImage(Movie movie,ImageView imageView){
        if(imagesMovies.containsKey(movie.getPosterPath())){
            imageView.setImageBitmap(imagesMovies.get(movie.getPosterPath()));
            return;
        }
        loadServerDataImage(movie.getPosterPath(),imageView);
    }

    public void loadServerDataImage(String url,ImageView imageView){
        try{
            Glide.with(EnjoyApp.getContext())
                    .load(RetrofitClient.getQRUrlWithHeaders(url))
                    .apply(RequestOptions.noTransformation()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(false)
                            .dontAnimate())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            try{
                                Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                imagesMovies.put(url,bitmap);
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }catch (Exception ignored){
                            }
                            return false;
                        }
                    })
                    .into(imageView);
        }catch (Exception ignored){
        }
    }
}
