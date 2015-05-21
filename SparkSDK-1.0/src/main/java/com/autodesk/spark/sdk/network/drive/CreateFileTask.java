package com.autodesk.spark.sdk.network.drive;

import com.android.volley.AuthFailureError;
import com.autodesk.spark.sdk.models.Request.FileRequest;
import com.autodesk.spark.sdk.models.Response.FilesResponse;
import com.autodesk.spark.sdk.network.BaseSparkRequest;
import com.autodesk.spark.sdk.network.MultipartRequest;
import com.autodesk.spark.sdk.utils.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Utils;


/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class CreateFileTask extends BaseSparkRequest<FilesResponse> {


    public CreateFileTask(final FileRequest file, ISparkResponse<FilesResponse> onSparkResponse)
    {

        super(onSparkResponse,FilesResponse.class);

        File fileToUpload = new File(file.path);
        HashMap<String,String> params = new HashMap<>();

        mRequest = new MultipartRequest(Utils.getBaseURL() + "/" + Constants.API_CREATE_FILE,
                                        this,this,fileToUpload,params)
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getBearerHeaders();
            }

        };




    }

}
