package com.autodesk.spark.sdk.example.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autodesk.spark.sdk.example.R;
import com.autodesk.spark.sdk.models.Request.FileRequest;
import com.autodesk.spark.sdk.models.Response.FilesResponse;
import com.autodesk.spark.sdk.spark.SparkDrive;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;



public class DriveCreateFileFragment extends Fragment {

    public final int SPARK_FILE_REQUEST_CODE = 0;

    public View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_drive_create_file, container, false);

        initUI();

        return mainView;
    }

    public void initUI()
    {

        mainView.findViewById(R.id.button_select_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        mainView.findViewById(R.id.button_create_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

    }

    public void selectFile()
    {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("*/*");
        startActivityForResult(i,SPARK_FILE_REQUEST_CODE);

    }

    public void createFile()
    {


        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/[YOUR-FILE-NAME]";

        FileRequest sparkFile  = new FileRequest(false,false,path);

        SparkDrive.createFile(sparkFile, new ISparkResponse<FilesResponse>() {
            @Override
            public void onSparkSuccess(FilesResponse responseObject) {

                String showResult  = "";
                Gson gson = new Gson();

                showResult = gson.toJson(responseObject,FilesResponse.class);

                ((TextView)mainView.findViewById(R.id.text_create_file_result)).setText(showResult);

            }

            @Override
            public void onSparkFailure(String errorMessage) {

                ((TextView)mainView.findViewById(R.id.text_create_file_result)).setText(errorMessage);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri selectedImageURI = data.getData();
        InputStream input = null;
        try {
            input = getActivity().getContentResolver().openInputStream(selectedImageURI);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data_buffer = new byte[16384];

            while ((nRead = input.read(data_buffer, 0, data_buffer.length)) != -1) {
                buffer.write(data_buffer, 0, nRead);
            }

            buffer.flush();

            byte[] byteArray = buffer.toByteArray();

           //Toast.makeText(getActivity(),"size = " + byteArray.length,Toast.LENGTH_SHORT).show();





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        super.onActivityResult(requestCode, resultCode, data);
    }


    public String getRealPathFromURI(Uri contentUri) {

        Cursor cursor = getActivity().getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
}