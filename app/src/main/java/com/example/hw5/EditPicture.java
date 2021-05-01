package com.example.hw5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class EditPicture extends AppCompatActivity {

    static final int REQUEST_IMAGE= 1;

    MyCanvas myCanvas;
    Button blue;
    Button red;
    Button green;
    Button undo,clear,done;
    TouchListener touchListener;
    public static ConstraintLayout constraintLayout;
    public static ArrayList<ImageView> drawableList = new ArrayList<>();



    ImageView photoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_picture);

        photoview = (ImageView)findViewById(R.id.photoiv);
        constraintLayout = (ConstraintLayout) findViewById(R.id.mainpage);

        Bitmap image = getIntent().getParcelableExtra("photo");
        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);
        if(image != null) {

            photoview.setBackground((new BitmapDrawable(getResources(), image)));
        }
        blue = (Button)findViewById(R.id.blueb);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.laneColor = Color.BLUE;
            }
        });
        green = (Button)findViewById(R.id.greenb);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.laneColor = Color.GREEN;
            }
        });
        red = (Button)findViewById(R.id.redb);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.laneColor = Color.RED;
            }
        });
        undo = (Button) findViewById(R.id.undob);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.undo();
                undoImage();
            }
        });

        clear = (Button) findViewById(R.id.clearb);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.clearlane();
                removeImage();
            }
        });

        done = (Button) findViewById(R.id.doneb);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }



    public void onDoubleTap(float x, float y) {

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.icon1);
        imageView.setX(x);
        imageView.setY(y);
        constraintLayout.addView(imageView);
        drawableList.add(imageView);
    }

    public void onLongPress(float x, float y) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.images);
        imageView.setX(x);
        imageView.setY(y);
        constraintLayout.addView(imageView);
        drawableList.add(imageView);
    }
    public void undoImage(){
        if (drawableList.size() > 0) {
            constraintLayout.removeView(drawableList.get(drawableList.size() - 1));
            drawableList.remove(drawableList.size() - 1);
        }
    }
    public void removeImage(){
        for (int i = 0; i < drawableList.size(); i++) {
            constraintLayout.removeView(drawableList.get(i));
        }
    }

}


   // private Drawable resize(Drawable image) {
     //   Bitmap b = ((BitmapDrawable)image).getBitmap();
       // Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 50, 50, false);
       // return new BitmapDrawable(getResources(), bitmapResized);
   // }
