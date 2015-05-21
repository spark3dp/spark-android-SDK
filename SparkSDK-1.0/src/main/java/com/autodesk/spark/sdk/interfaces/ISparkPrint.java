package com.autodesk.spark.sdk.interfaces;

import com.autodesk.spark.sdk.models.Request.CommandSendRequest;
import com.autodesk.spark.sdk.models.Request.CreateJobRequest;
import com.autodesk.spark.sdk.models.Request.PrinterJobStatusRequest;
import com.autodesk.spark.sdk.models.Request.PrinterRegisterRequest;
import com.autodesk.spark.sdk.models.Request.PrinterUnregisterRequest;
import com.autodesk.spark.sdk.models.Response.CommandSendResponse;
import com.autodesk.spark.sdk.models.Response.CreateJobResponse;
import com.autodesk.spark.sdk.models.Response.PrinterJobStatusResponse;
import com.autodesk.spark.sdk.models.Response.PrinterRegisterResponse;

/**
 * Created by ronnyremsnik on 5/10/15.
 */
public interface ISparkPrint {

    void sparkRegisterPrinter(PrinterRegisterRequest printer, ISparkResponse<PrinterRegisterResponse> onSparkResponse);

    void sparkUnregisterPrinter(PrinterUnregisterRequest printer, ISparkResponse<Object> onSparkResponse);

    void sparkCreateJob(CreateJobRequest printerJob, ISparkResponse<CreateJobResponse> onSparkResponse);

    void sparkCommandSend(CommandSendRequest command, ISparkResponse<CommandSendResponse> onSparkResponse);

    void sparkJobStatus(PrinterJobStatusRequest job, ISparkResponse<PrinterJobStatusResponse> onSparkResponse);


}
