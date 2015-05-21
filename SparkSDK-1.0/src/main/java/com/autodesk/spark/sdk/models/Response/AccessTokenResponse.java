package com.autodesk.spark.sdk.models.Response;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class AccessTokenResponse {

    public String access_token;
    public String refresh_token;
    public String expires_in;

    // external
    public long expires_at;

}
