package com.viicentecs.movieapp.Network;

import com.viicentecs.movieapp.Models.MovieResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EnjoyApi {
    @GET("movie/now_playing")
    Single<Response<MovieResponse>> listMovies(@Query("api_key") String api_key,@Query("page") int page,@Query("language") String language);


}


