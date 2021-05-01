package com.example.hw5;

import android.annotation.SuppressLint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

public class TouchListener implements View.OnTouchListener {

    //MainActivity mainActivity;
    EditPicture editPicture;
    GestureDetectorCompat gestureDetectorCompat;

    public TouchListener(EditPicture ed) {
        this.editPicture = ed;
        gestureDetectorCompat = new GestureDetectorCompat(this.editPicture, new MyGestureListener());
    }

    //@SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetectorCompat.onTouchEvent(motionEvent);
        int maskedAction = motionEvent.getActionMasked();
        float xPos = motionEvent.getX();
        float yPos = motionEvent.getY();
        switch(maskedAction){
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_POINTER_DOWN:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    editPicture.addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }

            case MotionEvent.ACTION_MOVE:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    editPicture.updatePath(id, motionEvent.getX(i), motionEvent.getY(i));

                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            //default:
               // return false;
            case MotionEvent.ACTION_CANCEL:
               // for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                  //  int id = motionEvent.getPointerId(i);
                    //editPicture.removePath(id);
                //}

        }
        //view.postInvalidate();
        return true;
    }



    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            editPicture.onDoubleTap(e.getX(), e.getY());
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            editPicture.onLongPress(e.getX(), e.getY());
            super.onLongPress(e);
        }
    }
}
