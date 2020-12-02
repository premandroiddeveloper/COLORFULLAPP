package com.example.colorfullapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Saving {
    public static File savefile(Activity mya, Bitmap bitmap) throws  IOException{
        String externalStorage= Environment.getExternalStorageState();
        File myfile=null;
        if(externalStorage.equals(Environment.MEDIA_MOUNTED)){
            File Picturedirectory=mya.getExternalFilesDir("ColorAppPicture");
            Date CurrentDate=new Date();
            long elapsedTime= SystemClock.elapsedRealtime();
            String uniqueImageName="/"+CurrentDate+"_"+elapsedTime+".png";
            myfile =new File(Picturedirectory+uniqueImageName);
            long remainingspace=Picturedirectory.getFreeSpace();
            long currentspace=bitmap.getByteCount();
            if(currentspace<remainingspace){
                try{
                    FileOutputStream fl1=new FileOutputStream(myfile);
                    boolean isImageSaves=bitmap.compress(Bitmap.CompressFormat.PNG,100,fl1);
                    if(isImageSaves){
                       return myfile;
                    }
                    else{

throw new IOException("The Image is Not Save SuccessFully");
                    }
                }
                catch(Exception e){

                    throw new IOException("error occured");

                }
            }else{
                throw  new IOException("you have not enough memory to store");
            }
        }
        else{
            throw  new IOException("THis Device Does Not have external storage");
        }

    }
}
