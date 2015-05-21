package com.autodesk.spark.sdk.models.Response;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class PrinterJobStatus {

    private String progress;

    private PrinterJobStatusData data;

    private String job_progress;

    private String printer_status;

    private String job_status;

    private String job_id;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public PrinterJobStatusData getData() {
        return data;
    }

    public void setData(PrinterJobStatusData data) {
        this.data = data;
    }

    public String getJob_progress() {
        return job_progress;
    }

    public void setJob_progress(String job_progress) {
        this.job_progress = job_progress;
    }

    public String getPrinter_status() {
        return printer_status;
    }

    public void setPrinter_status(String printer_status) {
        this.printer_status = printer_status;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [progress = " + progress + ", data = " + data + ", job_progress = " + job_progress + ", printer_status = " + printer_status + ", job_status = " + job_status + ", job_id = " + job_id + "]";
    }

}
