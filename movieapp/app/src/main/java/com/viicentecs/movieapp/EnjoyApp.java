package com.viicentecs.movieapp;

import android.app.Application;
import android.content.Context;

import com.viicentecs.movieapp.Utils.UserPreferences;

public class EnjoyApp extends Application {
    private static EnjoyApp instance;
    private static UserPreferences userPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        userPreferences = new UserPreferences();
        userPreferences.init(instance);
    }

    public static UserPreferences getTokenManager(){
        return userPreferences;
    }

    public static Context getContext(){
        return instance;
    }

}
