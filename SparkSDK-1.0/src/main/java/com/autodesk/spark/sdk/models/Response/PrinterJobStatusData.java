package com.autodesk.spark.sdk.models.Response;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class PrinterJobStatusData {

    private String total_layers;

    private String layer;

    private String seconds_left;

    private String job_status;

    private String job_id;

    private String temperature;

    public String getTotal_layers() {
        return total_layers;
    }

    public void setTotal_layers(String total_layers) {
        this.total_layers = total_layers;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getSeconds_left() {
        return seconds_left;
    }

    public void setSeconds_left(String seconds_left) {
        this.seconds_left = seconds_left;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "ClassPojo [total_layers = " + total_layers + ", layer = " + layer + ", seconds_left = " + seconds_left + ", job_status = " + job_status + ", job_id = " + job_id + ", temperature = " + temperature + "]";
    }
}
