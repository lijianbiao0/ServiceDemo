package com.hofo.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import com.hofo.servicedemo.util.L;

public class HelloService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    @Override
    public void onCreate() {
        L.e("HelloServiceActivity.onCreate");
        // 服务是运行在主线程的， 所以我们为了不阻塞主线程而造成ANR异常，
        // 所以我们创建个线程队列来执行任务。
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // 获取HandlerThread的Looper并将其用于我们的Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e("HelloServiceActivity.onStartCommand- startId=" + startId);
        // 对于每个启动请求，发送消息以启动作业并发送
        // 启动ID，以便我们知道在完成工作时我们停止了哪个请求
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        // 如果我们被杀了，从这里返回后，重启
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        L.e("HelloServiceActivity.onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.e("HelloServiceActivity.onBind");
        // 我们不提供绑定，因此返回null
        return null;
    }

    // 从线程接收消息的处理程序
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
            L.e("ServiceHandler.ServiceHandler");
        }

        @Override
        public void handleMessage(Message msg) {
            L.e("ServiceHandler.handleMessage" + msg.arg1);
            // 通常我们会在这里做一些工作，比如下载一个文件。
            // 对于我们的样本，我们只是睡了5秒钟。
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 恢复中断状态。
                Thread.currentThread().interrupt();
            }
            // 使用startId停止服务，这样我们就不会停止处理另一份工作的服务
            stopSelf(msg.arg1);
        }
    }
}