package com.autodesk.spark.sdk.spark;

import com.autodesk.spark.sdk.models.Request.CommandSendRequest;
import com.autodesk.spark.sdk.models.Request.CreateJobRequest;
import com.autodesk.spark.sdk.models.Request.PrinterJobStatusRequest;
import com.autodesk.spark.sdk.models.Request.PrinterRegisterRequest;
import com.autodesk.spark.sdk.models.Request.PrinterUnregisterRequest;
import com.autodesk.spark.sdk.models.Response.CommandSendResponse;
import com.autodesk.spark.sdk.models.Response.CreateJobResponse;
import com.autodesk.spark.sdk.models.Response.PrinterJobStatusResponse;
import com.autodesk.spark.sdk.models.Response.PrinterRegisterResponse;
import com.autodesk.spark.sdk.network.NetworkUtils;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;

/**
 * Created by ronnyremsnik on 5/4/15.
 */
public class SparkPrint {

    private NetworkUtils mNetworkUtils;

    public void setNetworkUtils (NetworkUtils NetworkUtils)
    {
        mNetworkUtils = NetworkUtils;
    }
    public NetworkUtils getNetworkUtils()
    {
        return mNetworkUtils;
    }

    private static SparkPrint mInstance;

    protected static SparkPrint getInstance()
    {
        if (mInstance == null)
            mInstance = new SparkPrint();

        return mInstance;

    }

    /** Register new printer.
     *
     * @param printer Printer request object, filled with printer name and registration code.
     * @param onSparkResponse Printer response object.
     */
    public static void registerPrinter(PrinterRegisterRequest printer, ISparkResponse<PrinterRegisterResponse> onSparkResponse){
        getInstance().sparkRegisterPrinter(printer, onSparkResponse);
    }

    private void sparkRegisterPrinter(PrinterRegisterRequest printer, ISparkResponse<PrinterRegisterResponse> onSparkResponse){

        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().registerPrinter(printer, onSparkResponse);
        }

    }

    /** Unregister Printer.
     *
     * @param printer Printer unregister object filled with the printer ID.
     * @param onSparkResponse Printer response object.
     */
    public static void unregisterPrinter(PrinterUnregisterRequest printer,ISparkResponse<Object> onSparkResponse){
        getInstance().sparkUnregisterPrinter(printer, onSparkResponse);
    }

    private void sparkUnregisterPrinter(PrinterUnregisterRequest printer,ISparkResponse<Object> onSparkResponse){

        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().unregisterPrinter(printer,onSparkResponse);
        }

    }

    /** Create new job for the printer and run it.
     *
     * @param printerJob Printer job object filled with th printer id and the data to print.
     * @param onSparkResponse  Printer job response object.
     */
    public static void createJob(CreateJobRequest printerJob, ISparkResponse<CreateJobResponse> onSparkResponse){
        getInstance().sparkCreateJob(printerJob, onSparkResponse);
    }

    private void sparkCreateJob(CreateJobRequest printerJob, ISparkResponse<CreateJobResponse> onSparkResponse){

        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().createJob(printerJob, onSparkResponse);
        }

    }

    /** Send command to the printer (Pause,Resume,Cancel).
     *
     * @param command selected command to send to the printer.
     * @param onSparkResponse Send command response object.
     */
    public static void commandSend(CommandSendRequest command, ISparkResponse<CommandSendResponse> onSparkResponse){
        getInstance().sparkCommandSend(command, onSparkResponse);
    }

    private void sparkCommandSend(CommandSendRequest command, ISparkResponse<CommandSendResponse> onSparkResponse){

        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().commandSend(command, onSparkResponse);
        }

    }

    /** Get job status by job ID.
     *
     * @param job Job request object filled with job ID.
     * @param onSparkResponse Job status response object.
     */
    public static void jobStatus(PrinterJobStatusRequest job, ISparkResponse<PrinterJobStatusResponse> onSparkResponse){
        getInstance().sparkJobStatus(job,onSparkResponse);
    }

    private void sparkJobStatus(PrinterJobStatusRequest job, ISparkResponse<PrinterJobStatusResponse> onSparkResponse){
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().jobStatus(job,onSparkResponse);
        }
    }

}
