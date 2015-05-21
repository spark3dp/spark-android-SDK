package com.autodesk.spark.sdk.network.print;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.models.Request.PrinterJobStatusRequest;
import com.autodesk.spark.sdk.models.Response.PrinterJobStatusResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.utils.Utils;

import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class PrinterJobStatusTask extends BaseSparkRequest<PrinterJobStatusResponse> {

    public PrinterJobStatusTask(PrinterJobStatusRequest jobStatus, ISparkResponse<PrinterJobStatusResponse> onSparkResponse)
    {
        super(onSparkResponse,PrinterJobStatusResponse.class);

        mRequest = new StringRequest(Request.Method.GET,
                Utils.getBaseURL() + "/" + Constants.API_PRINTER_JOB_STATUS + "/" + jobStatus.jobID,
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
