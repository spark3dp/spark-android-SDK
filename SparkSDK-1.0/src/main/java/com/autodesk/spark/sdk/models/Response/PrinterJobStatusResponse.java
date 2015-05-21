package com.autodesk.spark.sdk.models.Response;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class PrinterJobStatusResponse {

    private String local_job;

    private String job_status_time;

    private PrinterJobStatus job_status;

    private String job_date_time;

    private String job_id;

    public String getLocal_job() {
        return local_job;
    }

    public void setLocal_job(String local_job) {
        this.local_job = local_job;
    }

    public String getJob_status_time() {
        return job_status_time;
    }

    public void setJob_status_time(String job_status_time) {
        this.job_status_time = job_status_time;
    }

    public PrinterJobStatus getJob_status() {
        return job_status;
    }

    public void setJob_status(PrinterJobStatus job_status) {
        this.job_status = job_status;
    }

    public String getJob_date_time() {
        return job_date_time;
    }

    public void setJob_date_time(String job_date_time) {
        this.job_date_time = job_date_time;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [local_job = " + local_job + ", job_status_time = " + job_status_time + ", job_status = " + job_status + ", job_date_time = " + job_date_time + ", job_id = " + job_id + "]";
    }

}
