package com.viicentecs.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viicentecs.movieapp.EnjoyApp;
import com.viicentecs.movieapp.Models.Movie;
import com.viicentecs.movieapp.Models.MovieResponse;
import com.viicentecs.movieapp.Network.EnjoyApi;
import com.viicentecs.movieapp.Network.RetrofitClient;
import com.viicentecs.movieapp.R;
import com.viicentecs.movieapp.Adapter.MovieAdapter;
import com.viicentecs.movieapp.AlertDialogUtils.WelcomeCustomDialog;
import com.viicentecs.movieapp.Utils.Constats;
import com.viicentecs.movieapp.Utils.NetworkConstants;
import com.viicentecs.movieapp.interfaces.OpenDetailsMovie;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private int currentPage = 1;
    private int maximumPage = 1;
    private RecyclerView rvMovies;
    private MovieAdapter movieAdapter;
    private LinearLayout llEmpty;
    private ImageView ivNext;
    private ImageView ivBack;
    private ImageView ivLogout;
    private TextView tvCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initRecyclerview();
        validateFirstOpenAnShowMessage();
        loadServerDataListMovies();
    }

    private void initUI(){
        llEmpty = findViewById(R.id.ll_view_empty);
        ivBack = findViewById(R.id.iv_back_main);
        ivNext = findViewById(R.id.iv_next_main);
        ivLogout = findViewById(R.id.iv_exit_main);
        tvCurrentPage = findViewById(R.id.tv_number_main);
        ivBack.setOnClickListener(v->backOnList());
        ivNext.setOnClickListener(v->nextOnList());
        ivLogout.setOnClickListener(v ->closeMain());
    }
    private void initRecyclerview(){
        rvMovies = findViewById(R.id.rcv_list_main);
        movieAdapter = new MovieAdapter(openDetailsMovie);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void backOnList(){
        if(currentPage == 1){
            return;
        }
        currentPage--;
        loadServerDataListMovies();
    }

    private void nextOnList(){
        if(currentPage == maximumPage){
            return;
        }
        currentPage++;
        loadServerDataListMovies();
    }

    private void closeMain(){
        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);
        this.finish();
    }

    private void validateFirstOpenAnShowMessage(){
        boolean isFirstOpen = EnjoyApp.getTokenManager().isFirstOpen();
        if(!isFirstOpen){
            return;
        }
        showFistOpenMessage();
    }

    private void showFistOpenMessage(){
        WelcomeCustomDialog.showWelcome(this);
    }

    private void loadServerDataListMovies(){
        RetrofitClient.getClient().create(EnjoyApi.class)
        .listMovies(NetworkConstants.api_key,currentPage,NetworkConstants.language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Response<MovieResponse>>() {
                    @Override
                    public void onSuccess(Response<MovieResponse> response) {
                        validateListResponse(response.body());
                    }

                    @Override
                    public void onError(Throwable e) {
                        validateListResponse(null);
                    }
                });
    }

    private void validateListResponse(MovieResponse movieResponse){
        if(movieResponse == null || movieResponse.getResults().size() == 0){
            llEmpty.setVisibility(View.VISIBLE);
            return;
        }
        llEmpty.setVisibility(View.GONE);
        maximumPage = movieResponse.getTotal_pages();
        movieAdapter.setData(movieResponse.getResults());
        String pages = currentPage + " / " + maximumPage;
        tvCurrentPage.setText(pages);
    }

    OpenDetailsMovie openDetailsMovie = new OpenDetailsMovie() {
        @Override
        public void openDetailsMovie(int position) {
            Movie movie = movieAdapter.getMovieByPosition(position);
            validateAndOpenDetails(movie);
        }
    };

    private void validateAndOpenDetails(Movie movie){
        if(movie == null){
            return;
        }
        openDetails(movie);
    }

    private void openDetails(Movie movie){
        Intent details = new Intent(this, MovieDetailsActivity.class);
        details.putExtra(Constats.movieSelect,movie);
        startActivity(details);
    }

}