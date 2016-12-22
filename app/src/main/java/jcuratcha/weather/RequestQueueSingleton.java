package jcuratcha.weather;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton extends Application {

    private RequestQueue mRequestQueue;
    private static RequestQueueSingleton mInstance;

    public static final String TAG = RequestQueueSingleton.class.getName();

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    public <T> void add(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }

    public void cancel() {
        mRequestQueue.cancelAll(TAG);
    }

    public static synchronized RequestQueueSingleton getInstance() {
        return mInstance;
    }
}
