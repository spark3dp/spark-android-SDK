package com.autodesk.spark.sdk.network.print;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.models.Request.PrinterUnregisterRequest;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;


import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class UnregisterPrinterTask extends BaseSparkRequest<Object> {

    public UnregisterPrinterTask(final PrinterUnregisterRequest printer, ISparkResponse<Object> onSparkResponse)
    {
        super(onSparkResponse,Object.class);

        mRequest = new StringRequest(Request.Method.DELETE,
                Utils.getBaseURL() + "/" + Constants.API_PRINTER_UNREGISTER + "/" + printer.printerID,
                this,
                this)

        {

            @Override
            public byte[] getBody() {

                String httpPostBody = "";


                return httpPostBody.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBearerHeaders();
            }
        };



    }

}
