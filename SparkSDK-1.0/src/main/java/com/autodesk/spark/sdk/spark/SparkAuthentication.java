package com.autodesk.spark.sdk.spark;

import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.network.NetworkUtils;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;

/**
 * Created by ronnyremsnik on 4/29/15.
 */
public class SparkAuthentication {

    private NetworkUtils mNetworkUtils;

    public void setNetworkUtils (NetworkUtils NetworkUtils)
    {
        mNetworkUtils = NetworkUtils;
    }
    public NetworkUtils getNetworkUtils()
    {
        return mNetworkUtils;
    }

    private static SparkAuthentication mInstance;

    protected static SparkAuthentication getInstance()
    {
        if (mInstance == null)
            mInstance = new SparkAuthentication();

        return mInstance;

    }

    /** Get Guest Access Token.
     *
     * @param onGuestTokenResponse Contain the guest access token.
     */
    public static void getGuestToken(ISparkResponse<AccessTokenResponse> onGuestTokenResponse) {
        getInstance().sparkGetGuestToken(onGuestTokenResponse);
    }

    private void sparkGetGuestToken(ISparkResponse<AccessTokenResponse> onGuestTokenResponse) {
        if (Spark.checkPreAccessToken()) {
            getNetworkUtils().getGuestToken(onGuestTokenResponse);
        }
    }

    /** Invoke Web-Based authentication view.
     *
     * @param onAccessTokenResponse Contain the Access Token.
     */
    public static void getAuthorizationCode(ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {
        getInstance().sparkGetAuthorizationCode(onAccessTokenResponse);
    }

    private void sparkGetAuthorizationCode(ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {
        if (Spark.checkPreAccessToken()) {
            getNetworkUtils().getAuthorizationCode(onAccessTokenResponse);
        }
    }

}
