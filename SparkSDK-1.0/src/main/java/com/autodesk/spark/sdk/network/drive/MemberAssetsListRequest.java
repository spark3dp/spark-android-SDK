package com.autodesk.spark.sdk.network.drive;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.models.Request.MemberRequest;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.utils.Utils;

import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class MemberAssetsListRequest extends BaseSparkRequest<AssetsListResponse> {

    public MemberAssetsListRequest(MemberRequest member, ISparkResponse<AssetsListResponse> onSparkResponse)
    {
        super(onSparkResponse,AssetsListResponse.class);


        String url = Utils.getBaseURL() + "/" + Constants.API_GET_MEMBERS_ASSETS;
        url = url.replace(Constants.API_MEMBER_MID,member.memberID);

        mRequest = new StringRequest(Request.Method.GET,
                url,
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
