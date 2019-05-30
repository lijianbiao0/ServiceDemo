package com.hofo.servicedemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.hofo.servicedemo.R;
import com.hofo.servicedemo.services.LocalService;
import com.hofo.servicedemo.util.L;

public class BindingActivity extends BaseActivity {
    LocalService mService;
    boolean mBound = false;
    /**
     * 定义服务绑定的回调，传递给bindService（）
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            //我们绑定到LocalService，强制转换IBinder并获取LocalService实例
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e("Bind to LocalService");
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e("Unbind from the service");
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    /**
     * 单击按钮时调用（布局文件中的按钮使用android：onClick属性附加到此方法）
     */
    public void onButtonClick(View v) {
        if (mBound) {
            // 从LocalService调用方法。
            // 如果此调用可能会挂起，则此请求应在单独的线程中调用，以避免降低Activity性能。
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
        }
    }
}

