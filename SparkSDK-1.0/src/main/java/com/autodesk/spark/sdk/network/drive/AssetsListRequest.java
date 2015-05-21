package com.autodesk.spark.sdk.network.drive;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;

import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class AssetsListRequest extends BaseSparkRequest<AssetsListResponse> {

    public AssetsListRequest(ISparkResponse<AssetsListResponse> onSparkResponse)
    {
        super(onSparkResponse,AssetsListResponse.class);

        mRequest = new StringRequest(Request.Method.GET,
                Utils.getBaseURL() + "/" + Constants.API_GET_ASSETS,
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
