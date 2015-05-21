package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class CommandSendRequest {

    public CommandSendRequest(String printerID, String jobID, String command) {
        this.printerID = printerID;
        this.jobID = jobID;
        this.command = command;
    }

    public String printerID;
    public String jobID;
    public String command;

}
