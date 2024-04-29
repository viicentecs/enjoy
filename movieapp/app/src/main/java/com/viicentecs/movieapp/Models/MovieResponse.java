package com.viicentecs.movieapp.Models;

import java.util.ArrayList;

public class MovieResponse {

    public MovieResponse(){}

    private int page;
    private ArrayList<Movie> results;

    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return (results != null)?results:new ArrayList<>();
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
