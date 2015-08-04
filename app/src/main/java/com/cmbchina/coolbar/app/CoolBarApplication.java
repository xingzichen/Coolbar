package com.cmbchina.coolbar.app;

import android.app.Application;

/**
 * Created by liang on 7/1/15.
 */
public class CoolBarApplication extends Application {
    private static CoolBarApplication ourInstance;

    public static CoolBarApplication getInstance() {
        return ourInstance;
    }

    public CoolBarApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
