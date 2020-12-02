package com.example.colorfullapp;

import android.graphics.Bitmap;
import android.graphics.Color;

public class colorfull {
    public Bitmap bitmap;
    private float redcolorvalue;
    private float yellowcolorvalue;
    private float greencolorvalue;
    public colorfull(Bitmap newbits,float red,float yellow,float green){
        bitmap=newbits;
        setGreencolorvalue(green);
        setRedcolorvalue(red);
        setYellowcolorvalue(yellow);
    }

    public void setRedcolorvalue(float redcolorvalue) {

        if(redcolorvalue>=0 && redcolorvalue<=1)
        this.redcolorvalue = redcolorvalue;
    }

    public void setYellowcolorvalue(float yellowcolorvalue) {
        if(yellowcolorvalue>=0 && yellowcolorvalue<=1)
        this.yellowcolorvalue = yellowcolorvalue;
    }

    public void setGreencolorvalue(float greencolorvalue) {
        if(greencolorvalue>=0 && greencolorvalue<=1)
        this.greencolorvalue = greencolorvalue;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getRedcolorvalue() {
        return redcolorvalue;
    }

    public float getYellowcolorvalue() {
        return yellowcolorvalue;
    }

    public float getGreencolorvalue() {
        return greencolorvalue;
    }
public Bitmap colorizetheBitmap(){
        int bitmapwidth=bitmap.getWidth();
        int bitmapheight=bitmap.getHeight();
        Bitmap.Config bts=bitmap.getConfig();
        Bitmap localbitmap=Bitmap.createBitmap(bitmapwidth,bitmapheight,bts);
        for(int row=0;row<bitmapwidth;row++){
            for(int col=0;col<bitmapheight;col++){
                int pixelcolor=bitmap.getPixel(row,col);
                pixelcolor= Color.argb(Color.alpha(pixelcolor),redcolorvalue*Color.red(pixelcolor),greencolorvalue*Color.green(pixelcolor),yellowcolorvalue
                *Color.blue(pixelcolor));
                localbitmap.setPixel(row,col,pixelcolor);
            }

        }
return localbitmap;
}
}
