package com.hofo.servicedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hofo.servicedemo.R;
import com.hofo.servicedemo.services.HelloIntentService;

public class IntentServiceActivity extends BaseActivity {

    private Intent mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
    }

    public void startService(View view) {
        mService = new Intent(mContext, HelloIntentService.class);
        startService(mService);
    }
}
