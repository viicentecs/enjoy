package com.viicentecs.movieapp.Utils;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class FileUtils {


    public static void makeAllDirectory(String folder) {
        try {
            File location = getDirectoryToFile();
           /* createDirectory(location,ConstantUtils.file_directory1);
            createDirectory(location,ConstantUtils.file_directory2);
            createDirectory(location,ConstantUtils.file_directory3);
            createDirectory(location,ConstantUtils.file_directory3 + folder);*/
        } catch (Exception ignored) {
            Log.e("exportDbFile",ignored.getMessage());
        }
    }

    public static void createDirectory(File location,String subfolder){
        File file = new File(location + "/" + subfolder);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static File getDirectoryToFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            return Environment.getExternalStorageDirectory();
        }
    }

}
