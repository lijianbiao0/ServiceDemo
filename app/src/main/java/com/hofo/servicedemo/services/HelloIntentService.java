package com.hofo.servicedemo.services;

import android.app.IntentService;
import android.content.Intent;

import com.hofo.servicedemo.util.L;

public class HelloIntentService extends IntentService {

    /**
     * 构造函数是必需的，并且必须调用父类IntentService（String）具有工作线程名称的构造函数。
     */
    public HelloIntentService() {
        super("HelloIntentService");
    }

    /**
     * IntentService从默认工作线程调用此方法，其意图是启动服务。 当此方法返回时，IntentService会根据需要停止服务。
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // 通常我们会在这里做一些工作，比如下载一个文件。
        //本示例睡眠五秒钟，模拟工作。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
        L.e("service工作完成");
    }
}
