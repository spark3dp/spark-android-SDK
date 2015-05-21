package com.autodesk.spark.sdk.network.print;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.models.Request.CreateJobSettings;
import com.autodesk.spark.sdk.models.Response.CreateJobResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;

import org.json.JSONObject;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;
import com.google.gson.Gson;


import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class CreateJobTask extends BaseSparkRequest<CreateJobResponse> {

    public CreateJobTask(final com.autodesk.spark.sdk.models.Request.CreateJobRequest printerJob, ISparkResponse<CreateJobResponse> onSparkResponse)
    {
        super(onSparkResponse,CreateJobResponse.class);

        String url = Utils.getBaseURL() + "/" + Constants.API_PRINTER_CREATE_JOB;
        url = url.replace(Constants.API_PRINTER_PID, printerJob.printerID);

        mRequest = new StringRequest(Request.Method.POST,
                url,
                this,
                this)

        {

            @Override
            public byte[] getBody() {

                JSONObject main = new JSONObject();
                try {
                    main.put("printable_url", printerJob.printableURL);

                    CreateJobSettings settigns = CreateJobSettings.getDefaultSettings();
                    Gson gson = new Gson();
                    String settingsJsonString = gson.toJson(settigns,CreateJobSettings.class);
                    JSONObject settingJsonObject = new JSONObject(settingsJsonString);

                    main.put("settings",settingJsonObject);

                    String httpPostBody = main.toString();

                    return httpPostBody.getBytes();

                }
                catch (Exception e)
                {

                }
                return  null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBearerHeaders();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };



    }

}
