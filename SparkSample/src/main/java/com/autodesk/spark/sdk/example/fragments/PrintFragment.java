package com.autodesk.spark.sdk.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.autodesk.spark.sdk.example.R;
import com.autodesk.spark.sdk.models.Request.CommandSendRequest;
import com.autodesk.spark.sdk.models.Request.CreateJobRequest;
import com.autodesk.spark.sdk.models.Request.PrinterJobStatusRequest;
import com.autodesk.spark.sdk.models.Request.PrinterRegisterRequest;
import com.autodesk.spark.sdk.models.Request.PrinterUnregisterRequest;
import com.autodesk.spark.sdk.models.Response.CommandSendResponse;
import com.autodesk.spark.sdk.models.Response.CreateJobResponse;
import com.autodesk.spark.sdk.models.Response.PrinterJobStatusResponse;
import com.autodesk.spark.sdk.models.Response.PrinterRegisterResponse;
import com.autodesk.spark.sdk.spark.SparkPrint;
import com.autodesk.spark.sdk.utils.Constants;
import com.google.gson.Gson;
import android.os.Handler;
import android.widget.ProgressBar;

import com.autodesk.spark.sdk.interfaces.ISparkResponse;

public class PrintFragment extends Fragment {

    public final int JOB_STATUS_DELAY = 2000;

    public View mainView;
    public Runnable mJobStatusRunnable;
    public Handler mHandler;
    public String mCurrentJob = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_print, container, false);

        initUI();

        return mainView;
    }

    public void initUI() {

        mHandler = new Handler();

        mJobStatusRunnable = new Runnable() {
            @Override
            public void run() {

                final ProgressBar bar  = (ProgressBar)mainView.findViewById(R.id.job_progress);


                PrinterJobStatusRequest jobStatusRequest = new PrinterJobStatusRequest(mCurrentJob);

                SparkPrint.jobStatus(jobStatusRequest,new ISparkResponse<PrinterJobStatusResponse>() {
                    @Override
                    public void onSparkSuccess(PrinterJobStatusResponse responseObject) {

                        if (responseObject != null && responseObject.getJob_status() != null && responseObject.getJob_status().getData() != null)
                        {
                            int max = Integer.parseInt(responseObject.getJob_status().getData().getTotal_layers());
                            int current = Integer.parseInt(responseObject.getJob_status().getData().getLayer());

                            bar.setMax(max);
                            bar.setProgress(current);

                            if (current < max)
                            {
                                mHandler.postDelayed(mJobStatusRunnable,JOB_STATUS_DELAY);
                            }

                        }
                        else
                        {
                            mHandler.removeCallbacks(mJobStatusRunnable);
                        }

                    }

                    @Override
                    public void onSparkFailure(String errorMessage) {

                        ((EditText) mainView.findViewById(R.id.text_create_job_result)).setText(errorMessage);

                    }
                });



            }
        };

        mainView.findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sparkRegisterPrinter();
            }
        });

        mainView.findViewById(R.id.button_unregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sparkUnregisterPrinter();
            }
        });


        mainView.findViewById(R.id.button_create_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sparkCreateJob();
            }
        });

        mainView.findViewById(R.id.button_command_resume_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sparkCommandSend(Constants.API_PRINTER_COMMAND_RESUME);
                mHandler.postDelayed(mJobStatusRunnable,JOB_STATUS_DELAY);
            }
        });

        mainView.findViewById(R.id.button_command_pause_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sparkCommandSend(Constants.API_PRINTER_COMMAND_PAUSE);
                mHandler.removeCallbacks(mJobStatusRunnable);
            }
        });

        mainView.findViewById(R.id.button_command_cancel_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sparkCommandSend(Constants.API_PRINTER_COMMAND_CANCEL);
                mHandler.removeCallbacks(mJobStatusRunnable);
            }
        });

    }

    public void sparkCommandSend(String command) {

        EditText printeIDEdit = (EditText) mainView.findViewById(R.id.edit_printer_id3);
        String printerID = printeIDEdit.getText().toString();

        EditText jobIDEdit = (EditText) mainView.findViewById(R.id.edit_job_id);
        String jobID = jobIDEdit.getText().toString();

        CommandSendRequest commandSend = new CommandSendRequest(printerID,jobID,command);

        SparkPrint.commandSend(commandSend, new ISparkResponse<CommandSendResponse>() {
            @Override
            public void onSparkSuccess(CommandSendResponse responseObject) {
                ((EditText) mainView.findViewById(R.id.text_command_send_result)).setText(responseObject.task_id);
            }

            @Override
            public void onSparkFailure(String errorMessage) {
                ((EditText) mainView.findViewById(R.id.text_command_send_result)).setText(errorMessage);
            }
        });


    }

    public void sparkRegisterPrinter() {

        EditText editCode = (EditText) mainView.findViewById(R.id.edit_registration);
        String registrationCode = editCode.getText().toString();

        EditText printerNameEdit = (EditText) mainView.findViewById(R.id.edit_printer_name);
        String printerName = printerNameEdit.getText().toString();

        PrinterRegisterRequest printer = new PrinterRegisterRequest(printerName,registrationCode);

        SparkPrint.registerPrinter(printer, new ISparkResponse<PrinterRegisterResponse>() {
            @Override
            public void onSparkSuccess(PrinterRegisterResponse responseObject) {

                ((EditText) mainView.findViewById(R.id.text_register_result)).setText("printer id = " + responseObject.printer_id);

            }

            @Override
            public void onSparkFailure(String errorMessage) {

                ((EditText) mainView.findViewById(R.id.text_register_result)).setText(errorMessage);
            }
        });


    }

    public void sparkUnregisterPrinter() {

        EditText printeIDEdit = (EditText) mainView.findViewById(R.id.edit_printer_id);
        String printerID = printeIDEdit.getText().toString();

        PrinterUnregisterRequest printer = new PrinterUnregisterRequest(printerID);

        SparkPrint.unregisterPrinter(printer, new ISparkResponse<Object>() {
            @Override
            public void onSparkSuccess(Object responseObject) {

                ((EditText) mainView.findViewById(R.id.text_unregister_result)).setText("OK");
            }

            @Override
            public void onSparkFailure(String errorMessage) {

                ((EditText) mainView.findViewById(R.id.text_unregister_result)).setText(errorMessage);
            }
        });


    }

    public void sparkCreateJob() {

        EditText printeIDEdit = (EditText) mainView.findViewById(R.id.edit_printer_id2);
        String printerID = printeIDEdit.getText().toString();
        String printableURL = "[URL-TO-GCODE-FILE]";

        CreateJobRequest printerJob = new CreateJobRequest(printerID,printableURL);

        SparkPrint.createJob(printerJob, new ISparkResponse<CreateJobResponse>() {
            @Override
            public void onSparkSuccess(CreateJobResponse responseObject) {

                String showResult = "";
                Gson gson = new Gson();

                showResult = gson.toJson(responseObject, CreateJobResponse.class);

                ((EditText) mainView.findViewById(R.id.text_create_job_result)).setText(showResult);

                //set the job ID for next API
                EditText jobIDEdit = (EditText) mainView.findViewById(R.id.edit_job_id);
                jobIDEdit.setText(responseObject.job_id);

                mCurrentJob = responseObject.job_id;

                mHandler.postDelayed(mJobStatusRunnable,JOB_STATUS_DELAY);

            }

            @Override
            public void onSparkFailure(String errorMessage) {

                ((EditText) mainView.findViewById(R.id.text_create_job_result)).setText(errorMessage);
            }
        });


    }

}