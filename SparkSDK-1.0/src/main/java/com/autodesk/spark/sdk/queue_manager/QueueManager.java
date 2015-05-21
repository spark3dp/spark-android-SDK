package com.autodesk.spark.sdk.queue_manager;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ronnyremsnik on 3/1/15.
 */
public class QueueManager {

    private Context mContext;
    private RequestQueue mQueue;

    public QueueManager(Context context)
    {
        mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
    }

    public RequestQueue getQueue()
    {
        return mQueue;
    }


}
