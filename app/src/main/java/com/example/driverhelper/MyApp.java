package com.example.driverhelper;
import android.app.Application;

public class MyApp extends Application {

    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApp getInstance() {
        app=new MyApp();
        return app;
    }
}
