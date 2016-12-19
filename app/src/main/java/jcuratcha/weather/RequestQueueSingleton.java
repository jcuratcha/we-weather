package jcuratcha.weather;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by DoorCrasher on 2016-12-18.
 */

public class RequestQueueSingleton extends Application {

    private RequestQueue mRequestQueue;
    private static RequestQueueSingleton mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

    }

    public static synchronized RequestQueueSingleton getInstance() {
        return mInstance;
    }
}
