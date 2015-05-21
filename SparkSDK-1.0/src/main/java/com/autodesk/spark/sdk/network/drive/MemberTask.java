package com.autodesk.spark.sdk.network.drive;

/**
 * Created by ronnyremsnik on 3/3/15.
 */

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.models.Response.MemberResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;

import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class MemberTask extends BaseSparkRequest<MemberResponse> {

    public MemberTask(com.autodesk.spark.sdk.models.Request.MemberRequest member, ISparkResponse<MemberResponse> onSparkResponse)
    {
        super(onSparkResponse,MemberResponse.class);



        mRequest = new StringRequest(Request.Method.GET,
                Utils.getBaseURL() + "/" + Constants.API_GET_MEMBERS + "/" + member.memberID,
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
