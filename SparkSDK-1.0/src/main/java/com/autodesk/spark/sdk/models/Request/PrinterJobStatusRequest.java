package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class PrinterJobStatusRequest {

    public String jobID;

    public PrinterJobStatusRequest(String jobID) {
        this.jobID = jobID;
    }

}
