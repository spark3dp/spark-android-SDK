package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class FileRequest {

    public boolean zipEnable;
    public boolean publicEnable;
    public String path;

    public FileRequest(boolean zipEnable, boolean publicEnable, String path) {
        this.zipEnable = zipEnable;
        this.publicEnable = publicEnable;
        this.path = path;
    }

}
