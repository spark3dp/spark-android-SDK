package com.autodesk.spark.sdk.interfaces;

import com.autodesk.spark.sdk.models.Request.AuthCodeRequest;
import com.autodesk.spark.sdk.models.Request.RefreshAccessTokenRequest;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;

/**
 * Created by ronnyremsnik on 5/10/15.
 */
public interface ISparkAuthentication {

     void sparkGetGuestToken(ISparkResponse<AccessTokenResponse> onGuestTokenResponse);

     void sparkGetAccessToken(AuthCodeRequest authCode, ISparkResponse<AccessTokenResponse> onAccessTokenResponse);

     void sparkGetRefreshToken(RefreshAccessTokenRequest refreshCode,ISparkResponse<AccessTokenResponse> onRefreshTokenResponse);

}
