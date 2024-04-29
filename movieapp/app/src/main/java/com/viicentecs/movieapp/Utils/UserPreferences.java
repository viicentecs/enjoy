package com.viicentecs.movieapp.Utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private Context context;

    private SharedPreferences sharedPreferences;

    String username;
    String password;
    boolean isFirstOpen;

    public void init(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PreferencesConstants.preferences, Context.MODE_PRIVATE);
    }

    public String getUsername() {
        return getStringFromPreference(PreferencesConstants.usernamePreference);
    }

    public void setUsername(String username) {
        saveStringPreference(PreferencesConstants.usernamePreference,username);
    }

    public String getPassword() {
        return getStringFromPreference(PreferencesConstants.passwordPreference);
    }

    public void setPassword(String password) {
        saveStringPreference(PreferencesConstants.passwordPreference,password);
    }

    public boolean isFirstOpen() {
        return getStringBoolPreference(PreferencesConstants.isFirstPreference);
    }

    public void setFirstOpen(boolean firstOpen) {
        saveBooleanPreference(PreferencesConstants.isFirstPreference,firstOpen);
    }

    private String getStringFromPreference(String field){
        return sharedPreferences.getString(field,PreferencesConstants.emptyPreference);
    }

    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    private void saveStringPreference(String field,String value){
        try{
            if(value == null || value.isEmpty()){
                return;
            }
            if(sharedPreferences == null){
                sharedPreferences = context.getSharedPreferences(PreferencesConstants.preferences, Context.MODE_PRIVATE);
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(field, value);
            editor.apply();
            editor.commit();
        }catch (Exception ignored){}
    }

    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    public void saveBooleanPreference(String field, boolean value){
        try{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(field, value);
            editor.apply();
            editor.commit();
        }catch (Exception ignored){}
    }

    private boolean getStringBoolPreference(String field){
        return sharedPreferences.getBoolean(field,false);
    }


}
