package com.autodesk.spark.sdk.network.drive;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;


import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class CreateAssetTask extends BaseSparkRequest<AssetResponse> {


    public CreateAssetTask(final com.autodesk.spark.sdk.models.Request.AssetRequest asset, ISparkResponse<AssetResponse> onSparkResponse)
    {

        super(onSparkResponse,AssetResponse.class);

        mRequest = new StringRequest(Request.Method.POST,
                                            Utils.getBaseURL() + "/" + Constants.API_GET_ASSETS,
                                           this,
                                           this)

        {
            @Override
            public byte[] getBody() {

                StringBuilder bodyBuilder = new StringBuilder();
                bodyBuilder.append("asset_name=" + asset.asset_name).
                            append("&description="+ asset.asset_description).
                            append("&tags=" + asset.asset_tags);

                String httpPostBody =  bodyBuilder.toString();


                return httpPostBody.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBearerHeaders();
            }

        };


    }

}
