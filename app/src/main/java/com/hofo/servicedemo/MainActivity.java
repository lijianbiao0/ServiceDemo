package com.hofo.servicedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hofo.servicedemo.activity.BindingActivity;
import com.hofo.servicedemo.activity.HelloServiceActivity;
import com.hofo.servicedemo.activity.IntentServiceActivity;
import com.hofo.servicedemo.activity.MessengerActivity;

public class MainActivity extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    public void binding(View view) {
        startActivity(BindingActivity.class);
    }

    public void startActivity(Class cls) {
        super.startActivity(new Intent(mContext, cls));
    }

    public void messenger(View view) {
        startActivity(MessengerActivity.class);
    }

    public void IntentService(View view) {
        startActivity(IntentServiceActivity.class);
    }

    public void service(View view) {
        startActivity(HelloServiceActivity.class);
    }
}
