package com.hofo.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {
    /**
     * 服务显示消息的命令
     */
    public static final int MSG_SAY_HELLO = 1;
    /**
     * 我们发布的目标是客户端向IncomingHandler发送消息。
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    /**
     * 绑定到服务时，我们返回一个接口到我们的messenger，用于向服务发送消息。
     */
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

    /**
     * 来自客户端的传入消息的处理程序。
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}