package com.autodesk.spark.sdk.network.drive;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class AssetTask extends BaseSparkRequest<AssetResponse> {

    public AssetTask(com.autodesk.spark.sdk.models.Request.AssetRequest asset, ISparkResponse<AssetResponse> onSparkResponse)
    {
        super(onSparkResponse,AssetResponse.class);

        mRequest = new StringRequest(Request.Method.GET,
                Utils.getBaseURL() + "/" + Constants.API_GET_ASSETS + "/" + asset.asset_id,
                this,
                this)

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBearerHeaders();
            }
        };



    }

}
