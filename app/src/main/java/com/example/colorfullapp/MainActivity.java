package com.example.colorfullapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {
Button b1,b2,b3,b4;
SeekBar sg,sr,sy;
TextView gr,ye,re;
ImageView img1;
    colorfull cl1;
    Bitmap bitmap;
public static final int GALLERY_REQUESR_CODE=101;
    public static final int CAMERA_REQUEST_CODE=102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.adding);
        b2=(Button)findViewById(R.id.saves);
        b3=(Button)findViewById(R.id.button);
        b4=(Button)findViewById(R.id.share);
        sg=(SeekBar)findViewById(R.id.greenseeks);
        sr=(SeekBar)findViewById(R.id.redseeks);
        sy=(SeekBar)findViewById(R.id.yellowseeks);
        gr=(TextView)findViewById(R.id.green);
        re=(TextView)findViewById(R.id.red);
        ye=(TextView)findViewById(R.id.Yellow);
        img1=(ImageView)findViewById(R.id.mainframe);
        b1.setOnClickListener(MainActivity.this);
        b2.setOnClickListener(MainActivity.this);
        b3.setOnClickListener(MainActivity.this);
        b4.setOnClickListener(MainActivity.this);
        sg.setOnSeekBarChangeListener(MainActivity.this);
        sr.setOnSeekBarChangeListener(MainActivity.this);
        sy.setOnSeekBarChangeListener(MainActivity.this);
        sg.setVisibility(View.INVISIBLE);
        sr.setVisibility(View.INVISIBLE);
        sy.setVisibility(View.INVISIBLE);
        gr.setVisibility(View.INVISIBLE);
        re.setVisibility(View.INVISIBLE);
        ye.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.adding){
            int permiss= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
if(permiss== PackageManager.PERMISSION_GRANTED){
PackageManager pm1=getPackageManager();
if(pm1.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
    Intent i1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(i1,CAMERA_REQUEST_CODE);
}
else{
    Toast.makeText(MainActivity.this,"You Have not camera",Toast.LENGTH_LONG);
}
}
else {
    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
}

        }
        else if(v.getId()==R.id.saves){
int permission=ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
if(permission==PackageManager.PERMISSION_GRANTED){
try{
    Saving.savefile(MainActivity.this,bitmap);
    Toast.makeText(MainActivity.this,"the Image Save SuccessFully",Toast.LENGTH_LONG).show();
}
catch (Exception e){
Toast.makeText(MainActivity.this,"error occured1",Toast.LENGTH_LONG).show();
}
}
else{
    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
}
        }
else if(v.getId()==R.id.button){
    Intent i2=new Intent();
    i2.setType("image/*");
    i2.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(i2,GALLERY_REQUESR_CODE);
        }
else if(v.getId()==R.id.share){
            try {
                File mypicture=Saving.savefile(MainActivity.this,bitmap);
                Uri myuri=Uri.fromFile(mypicture);
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"This picture is send to you by my new app");
                shareIntent.putExtra(Intent.EXTRA_STREAM,myuri);
                startActivity(shareIntent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUESR_CODE && resultCode==RESULT_OK && data!=null){
            Uri ur=data.getData();
            img1.setImageURI(ur);
        }
        else if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            sg.setVisibility(View.VISIBLE);
            sr.setVisibility(View.VISIBLE);
            sy.setVisibility(View.VISIBLE);
            gr.setVisibility(View.VISIBLE);
            re.setVisibility(View.VISIBLE);
            ye.setVisibility(View.VISIBLE);
            b4.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            Bundle bundle=data.getExtras();
            bitmap=(Bitmap)bundle.get("data");
            cl1=new colorfull(bitmap,0.0f,0.0f,0.0f);
            img1.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            if(seekBar==sg){
                    cl1.setGreencolorvalue((float)progress/100);
                    sg.setProgress(progress);
                    gr.setText(cl1.getGreencolorvalue()+"");

            }
            
            else if(seekBar==sr){
                cl1.setRedcolorvalue((float)progress/100);
                sr.setProgress(progress);
                re.setText(cl1.getRedcolorvalue()+"");
            }
            else if(seekBar==sy){
                cl1.setYellowcolorvalue((float)progress/100);
                sy.setProgress(progress);
                ye.setText(cl1.getYellowcolorvalue()+"");
            }
           bitmap=cl1.colorizetheBitmap();
            img1.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}