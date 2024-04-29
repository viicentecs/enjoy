package com.viicentecs.movieapp.AlertDialogUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.viicentecs.movieapp.EnjoyApp;
import com.viicentecs.movieapp.R;

public class WelcomeCustomDialog {

    public static void showWelcome(Activity activity){
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.view_customdialog_welcome, null);
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setView(view);
        view.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ImageView iv_close = view.findViewById(R.id.iv_close_form);
        iv_close.setOnClickListener(v ->{
            if(!alertDialog.isShowing()){
                return;
            }
            EnjoyApp.getTokenManager().setFirstOpen(false);
            alertDialog.dismiss();
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


}
