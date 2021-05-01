package com.example.hw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCanvas extends View {

    HashMap <Integer, Path> activePaths;
    public static Paint pathPaint;
    public static ArrayList<lane> paths;
    public static Path path;
    int laneColor;


    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        paths = new ArrayList<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        
        pathPaint.setColor(Color.BLACK);
        laneColor = Color.BLACK;
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(lane lane: paths){
            pathPaint.setColor(lane.color);
            canvas.drawPath(lane.path, pathPaint);
        }
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        path = new Path();
        lane lane = new lane(laneColor, path);
        paths.add(lane);
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }

    public void undo() {
        if (paths.size() != 0) {

            if (paths.size() == 1) {
                paths.remove(paths.size() - 1);
                path.reset();
                pathPaint.setColor(Color.BLACK);
            }
            else {
                paths.remove(paths.size() - 1);
            }
            invalidate();
        } else {
            path.reset();
        }
    }

    public void clearlane(){
        if (paths.size() != 0) {
            for(int i = 0; i < paths.size(); i++){
                paths.remove(i);
                invalidate();
            }
            paths.clear();
            path.reset();
            laneColor = Color.BLACK;
            pathPaint.setColor(Color.BLACK);
        }


    }
}
