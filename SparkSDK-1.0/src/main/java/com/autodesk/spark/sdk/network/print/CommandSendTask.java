package com.autodesk.spark.sdk.network.print;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.models.Response.CommandSendResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.utils.Utils;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class CommandSendTask extends BaseSparkRequest<CommandSendResponse> {

    public CommandSendTask(final com.autodesk.spark.sdk.models.Request.CommandSendRequest command, ISparkResponse<CommandSendResponse> onSparkResponse)
    {
        super(onSparkResponse,CommandSendResponse.class);

        String url = Utils.getBaseURL() + "/" + Constants.API_PRINTER_COMMAND_SEND;
        url = url.replace(Constants.API_PRINTER_PID, command.printerID);

        mRequest = new StringRequest(Request.Method.POST,
                url,
                this,
                this)

        {

            @Override
            public byte[] getBody() {

                JSONObject main = new JSONObject();
                try {
                    main.put("command", command.command);
                    main.put("job_id",command.jobID);

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
