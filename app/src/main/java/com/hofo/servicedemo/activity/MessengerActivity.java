package com.hofo.servicedemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import com.hofo.servicedemo.R;
import com.hofo.servicedemo.services.MessengerService;

public class MessengerActivity extends BaseActivity {

    /**
     * Messenger用于与服务通信。
     */
    Messenger mService = null;
    /**
     * 指示我们是否已在服务上调用绑定的标志。
     */
    boolean mBound;
    /**
     * 用于与服务主界面交互的类。
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            //在与服务建立连接时调用此方法，为我们提供可用于与服务交互的对象。
            // 我们正在使用Messenger与服务进行通信，
            // 因此我们在此处从原始IBinder对象获取客户端表示。
            mService = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            //当与服务的连接意外断开时调用此方法 - 即，其进程崩溃。
            mService = null;
            mBound = false;
        }
    };

    public void sayHello(View v) {
        if (!mBound) return;
        // 使用支持的“what”值创建并向服务发送消息
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //绑定到服务
        bindService(new Intent(mContext, MessengerService.class), mConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 从服务中取消绑定
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
}

