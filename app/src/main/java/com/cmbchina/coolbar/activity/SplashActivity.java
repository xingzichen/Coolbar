package com.cmbchina.coolbar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.cmbchina.coolbar.R;

/**
 * Created by liang on 7/1/15.
 */
public class SplashActivity extends FragmentActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mHandler = new Handler();

        // 取用户名 密码

        mHandler.postDelayed(gotoLoginAct, 3000);
    }

    Runnable gotoLoginAct = new Runnable() {
        @Override
        public void run() {
            // 启动登录界面
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    };
}
