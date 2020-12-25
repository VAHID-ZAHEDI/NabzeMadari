package com.example.pregnancykotlin.ui;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

@TargetApi(Build.VERSION_CODES.P)
public class ProgressOutlineProvider extends ViewOutlineProvider{
    private float radius = -1;

    @Override
    public void getOutline(View view, Outline outline) {
        if(radius != -1) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
        }
    }

    public void updateProgress(float maxRadius, float progress) {
        this.radius = maxRadius - maxRadius * (1 - progress);
    }
}