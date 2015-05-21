package com.autodesk.spark.sdk.network.print;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.models.Request.PrinterRegisterRequest;
import com.autodesk.spark.sdk.models.Response.PrinterRegisterResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;


import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class RegisterPrinterTask extends BaseSparkRequest<PrinterRegisterResponse> {

    public RegisterPrinterTask(final PrinterRegisterRequest printer, ISparkResponse<PrinterRegisterResponse> onSparkResponse)
    {
        super(onSparkResponse,PrinterRegisterResponse.class);

        mRequest = new StringRequest(Request.Method.POST,
                Utils.getBaseURL() + "/" + Constants.API_PRINTER_REGISTER,
                this,
                this)

        {

            @Override
            public byte[] getBody() {

                String httpPostBody = "printer_name=" + printer.printerName + "&registration_code=" + printer.registrationCode;


                return httpPostBody.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBearerHeaders();
            }
        };



    }

}
