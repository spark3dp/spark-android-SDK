package com.autodesk.spark.sdk.network.authentication;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;


/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class GuestTokenTask extends BaseSparkRequest<AccessTokenResponse> {


    public GuestTokenTask(ISparkResponse<AccessTokenResponse> onSparkResponse)
    {

        super(onSparkResponse,AccessTokenResponse.class);

        mRequest = new StringRequest(Request.Method.POST,
                Utils.getBaseURL()  + "/" + Constants.API_GET_GUEST_TOKEN,
                                           this,
                                           this)

        {
            @Override
            public byte[] getBody() {

                String httpPostBody = "grant_type=client_credentials&client_id=" + MemoryManager.getInstance().getAppKey() +
                                     "&client_secret=" + MemoryManager.getInstance().getAppSecret();


                return httpPostBody.getBytes();
            }

            @Override
            protected Map<String, String> getParams()  {
                return getBasicHeaders();

            }

        };


    }

    @Override
    public void onAfterParsing() {

        MemoryManager.getInstance().setAccessToken(mResponse.access_token);
        MemoryManager.getInstance().setRefreshToken(mResponse.refresh_token);

        MemoryManager.getInstance().setAuthorizationType(Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_GUEST);
    }
}
