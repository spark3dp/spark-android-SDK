package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/13/15.
 */
public class RefreshAccessTokenRequest {
    public String refreshCode;

    public RefreshAccessTokenRequest(String refreshCode)
    {
        this.refreshCode = refreshCode;
    }
}
