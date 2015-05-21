package com.autodesk.spark.sdk.models;

import com.autodesk.spark.sdk.memory_manager.MemoryManager;

/**
 * Created by ronnyremsnik on 5/14/15.
 */
public class SparkSession {

    public String mAccessToken;
    public String mRefreshToken;
    public int    mAuthorizationType;

    private SparkSession(String accessToken,String refreshToken,int authType)
    {
        mAccessToken = accessToken;
        mRefreshToken = refreshToken;
        mAuthorizationType = authType;
    }

    public static SparkSession getActiveSession()
    {
        SparkSession  session = new SparkSession(MemoryManager.getInstance().getAccessToken(),
                                        MemoryManager.getInstance().getRefreshToken(),
                                        MemoryManager.getInstance().getAuthorizationType() );

        return session;

    }

}
