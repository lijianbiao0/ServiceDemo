package com.hofo.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class LocalService extends Service {
    // Binder给客户
    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 客户的方法
     */
    public int getRandomNumber() {
        return new Random().nextInt(100);
    }

    /**
     * 用于客户端Binder的类。
     * 因为我们总是知道这项服务在与客户相同的流程中运行，我们不需要处理IPC。
     */
    public class LocalBinder extends Binder {
        public LocalService getService() {
            //返回此LocalService实例，以便客户端可以调用公共方法
            return LocalService.this;
        }
    }
}
