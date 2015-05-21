package com.autodesk.spark.sdk.network.authentication;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.models.Request.RefreshAccessTokenRequest;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;

import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class RefreshTokenTask extends BaseSparkRequest<AccessTokenResponse> {


    public RefreshTokenTask(final RefreshAccessTokenRequest refreshCode, ISparkResponse<AccessTokenResponse> onSparkResponse)
    {

        super(onSparkResponse,AccessTokenResponse.class);

        mRequest = new StringRequest(Request.Method.POST,
                                            Utils.getBaseURL() + "/" + Constants.API_GET_REFRESH_TOKEN,
                                           this,
                                           this)

        {
            @Override
            public byte[] getBody() {

                String httpPostBody = "grant_type=refresh_token&refresh_token=" + refreshCode.refreshCode;

                return httpPostBody.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBasicHeaders();
            }

        };


    }


    @Override
    public void onAfterParsing() {

        MemoryManager.getInstance().setAccessToken(mResponse.access_token);
        MemoryManager.getInstance().setRefreshToken(mResponse.refresh_token);

        // set the expires value
        mResponse.expires_at  = System.currentTimeMillis() +  (int)Float.parseFloat(mResponse.expires_in)* 1000;
        MemoryManager.getInstance().setExpiresAt(mResponse.expires_at);

    }
}
