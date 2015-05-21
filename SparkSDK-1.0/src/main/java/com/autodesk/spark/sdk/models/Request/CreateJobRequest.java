package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class CreateJobRequest {

    public String printerID;
    public String printableURL;

    public CreateJobRequest(String printerID, String printableURL) {
        this.printerID = printerID;
        this.printableURL = printableURL;
    }

}
