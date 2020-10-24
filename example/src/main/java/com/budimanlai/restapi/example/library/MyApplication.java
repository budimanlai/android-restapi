package com.budimanlai.restapi.example.library;

import android.app.Application;

import com.budimanlai.restapi.RestAPIBase;
import com.budimanlai.restapi.example.R;

public class MyApplication extends Application {

    public RestAPIBase api;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        api = new RestAPIBase(this, getString(R.string.base_url));
        api.setDebug(true);
    }

    public MyApplication() {
        super();
        instance = this;
    }

    public static MyApplication getInstance() { return instance; }

}
