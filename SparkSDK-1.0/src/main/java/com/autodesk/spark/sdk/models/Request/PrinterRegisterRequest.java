package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class PrinterRegisterRequest {

    public String printerName;
    public String registrationCode;

    public PrinterRegisterRequest(String printerName, String registrationCode) {
        this.printerName = printerName;
        this.registrationCode = registrationCode;
    }
}
