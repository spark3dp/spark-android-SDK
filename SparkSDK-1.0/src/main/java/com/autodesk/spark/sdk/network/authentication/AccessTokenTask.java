package com.autodesk.spark.sdk.network.authentication;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.models.Request.AuthCodeRequest;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;


/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class AccessTokenTask extends BaseSparkRequest<AccessTokenResponse> {


    public AccessTokenTask(final AuthCodeRequest code, ISparkResponse<AccessTokenResponse> onSparkResponse)
    {

        super(onSparkResponse,AccessTokenResponse.class);

        mRequest = new StringRequest(Request.Method.POST,
                                           Utils.getBaseURL() + "/" + Constants.API_GET_GUEST_TOKEN,
                                           this,
                                           this)

        {
            @Override
            public byte[] getBody() {

                String httpPostBody = "grant_type=authorization_code&code=" + code.authCode + "&response_type=code&redirect_uri=" + Constants.SPARK_BOGUS_REDIRECT_URL;

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

        MemoryManager.getInstance().setAuthorizationType(Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_REGULAR);

        // set the expires value
        mResponse.expires_at  = System.currentTimeMillis() +  (int)Float.parseFloat(mResponse.expires_in)* 1000;
        MemoryManager.getInstance().setExpiresAt(mResponse.expires_at);



    }
}
