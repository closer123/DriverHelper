package com.example.driverhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.driverhelper.R;

public class SplashScreenActivity extends AppCompatActivity {

    TextView textView;
    //声明引入的组件lottie
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setContentView(R.layout.activity_splash_screen);
        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        initView();
        //实现文字动画
        textView.animate().translationY(300).setDuration(2000).setStartDelay(0);
        //实现图片动画
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2200);
    }

    private void initView() {
        textView = findViewById(R.id.appname);
        //获取布局里面的组件
        lottie = findViewById(R.id.lottie);
    }
}