package com.viicentecs.movieapp.Utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtils {

    private static Date date;
    private static SimpleDateFormat formatIn;
    private static SimpleDateFormat formatOut;



    @SuppressLint("SimpleDateFormat")
    public static String convertMovieDateToEasyFormat(String dateMovie){
        try {

            if(dateMovie == null){
                return "N/A";
            }

            formatIn = new SimpleDateFormat(DateConstants.movieFormat);
            formatOut = new SimpleDateFormat(DateConstants.easyFormat);
            date = formatIn.parse(dateMovie);
            if(date == null){
                return "N/A";
            }
            return formatOut.format(date);
        } catch (ParseException e) {
            return "N/A";
        }
    }

}
