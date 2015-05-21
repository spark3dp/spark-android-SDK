package com.autodesk.spark.sdk.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.google.gson.Gson;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public abstract class BaseSparkRequest<T> implements Response.ErrorListener,
                                                 Response.Listener<String>
{

    protected Request<?> mRequest;
    protected Class<T> mType;
    protected T mResponse;

    protected ISparkResponse<T> mOnResponseCallback;

    public BaseSparkRequest(ISparkResponse<T> onResponseCallback, Class<T> type)
    {
        // save the callback
        mOnResponseCallback = onResponseCallback;

        // set the class type for response invocation
        mType = type;

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        if (mOnResponseCallback != null) {

            try {
                String errorStr = new String(error.networkResponse.data,"UTF-8");

                mOnResponseCallback.onSparkFailure(errorStr);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }

    }


    @Override
    public void onResponse(String response) {

        if (response != null)
        {
            String json = response;
            Gson gson = new Gson();
            mResponse =  gson.fromJson(json, mType);

            // call the child method to enable pre-parsing method before the callback
            onAfterParsing();

            if (mOnResponseCallback != null)
            {
                mOnResponseCallback.onSparkSuccess(mResponse);
            }


        }

    }

    public Request<?> getRequest()
    {
        return mRequest;

    }

    public void execute(RequestQueue queue)
    {
        // run the request

        queue.add(mRequest);

    }

    public Map<String, String> getBearerHeaders() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Authorization", "Bearer " + MemoryManager.getInstance().getAccessToken());
        return params;
    }

    public Map<String, String> getBasicHeaders() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Authorization", "Basic " + MemoryManager.getInstance().getAppKeySecretBase64());
        return params;
    }

    public void onAfterParsing()
    {
        // default parsing code
    }
}
