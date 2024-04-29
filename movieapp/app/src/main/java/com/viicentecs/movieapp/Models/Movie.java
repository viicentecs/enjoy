package com.viicentecs.movieapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

    public Movie(){}

    @SerializedName("title")
    private String title;
    private String duration;
    @SerializedName("release_date")
    private String releaseDate;


    private String classification;
    private ArrayList<Integer> genres;

    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    private double voteAverage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public ArrayList<Integer> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Integer> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String poster_path) {
        this.posterPath = poster_path;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
