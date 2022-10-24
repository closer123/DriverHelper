package com.example.driverhelper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.TextView;

public class DirectBroadSurfaceView extends TextureView {
    public DirectBroadSurfaceView(Context context) {
        super(context);
    }

    public DirectBroadSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DirectBroadSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DirectBroadSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
