package com.viger.myappframe.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

public class MyHandler extends Handler {

    private WeakReference<Activity> activityWeakReference;
    private HandlerCallback callback;

    public MyHandler(Activity activity, HandlerCallback callback) {
        this.callback = callback;
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if(activityWeakReference != null && activityWeakReference.get() != null) {
            //Activity activity = activityWeakReference.get();
            if(callback != null && msg != null) {
                callback.handlerMessage(msg);
            }
        }
    }

    public interface HandlerCallback {
        void handlerMessage(Message msg);
    }

//    private MyHandler myHandler = new MyHandler(this,
//            new MyHandler.HandlerCallback() {
//                @Override
//                public void handlerMessage(Message msg) {
//                    String messge = (String) msg.obj;
//                    textView.setText(messge);
//                }
//            });

}
