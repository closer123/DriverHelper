package com.example.driverhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.driverhelper.R;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("xiaohao滑动状态：","登陆了");

                Intent i=new Intent();
                i.setClass(getApplicationContext(),ContainActivity.class);
                startActivity(i);

            }
        });
    }

    private void initView() {
        button=findViewById(R.id.sendBt);
    }
}