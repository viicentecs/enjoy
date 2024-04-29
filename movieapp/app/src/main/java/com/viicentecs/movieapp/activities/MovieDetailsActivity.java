package com.viicentecs.movieapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.viicentecs.movieapp.Utils.Constats;
import com.viicentecs.movieapp.Utils.DatesUtils;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;
    TextView tvTittle;
    TextView tvDescription;
    TextView tvDate;
    TextView tvQuality;
    ImageView ivBack;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movie = (Movie) getIntent().getSerializableExtra(Constats.movieSelect);
        initUI();
        setData();
    }

    private void initUI(){
        tvTittle = findViewById(R.id.tv_tittle_details);
        tvDescription = findViewById(R.id.tv_description_details);
        tvDate = findViewById(R.id.tv_date_details);
        tvQuality = findViewById(R.id.tv_qualifity_details);
        ivBack = findViewById(R.id.iv_exit_main);
        ivPhoto = findViewById(R.id.iv_photo_details);
        ivBack.setOnClickListener(v -> this.finish());
    }

    private void setData(){
        tvTittle.setText(movie.getTitle());
        tvDescription.setText(movie.getDescription());
        tvDate.setText(DatesUtils.convertMovieDateToEasyFormat(movie.getReleaseDate()));
        String votes = movie.getVoteAverage() + "%";
        tvQuality.setText(votes);
        loadServerDataImage(movie.getPosterPath(),ivPhoto);
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