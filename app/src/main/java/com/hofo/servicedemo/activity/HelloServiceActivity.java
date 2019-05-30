package com.hofo.servicedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hofo.servicedemo.R;
import com.hofo.servicedemo.services.HelloService;

public class HelloServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_service);
    }

    public void startService(View view) {
        startService(new Intent(mContext, HelloService.class));
    }
}
