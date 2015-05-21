package com.autodesk.spark.sdk.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.spark.sdk.example.R;
import com.autodesk.spark.sdk.models.Request.AssetRequest;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.spark.SparkDrive;
import com.google.gson.Gson;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;



public class DriveCreateAssetFragment extends Fragment {

    public View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_drive_create_asset, container, false);

        initUI();

        return mainView;
    }

    public void initUI()
    {

        mainView.findViewById(R.id.button_create_asset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSparkAssets();
            }
        });


    }


    public void createSparkAssets()
    {

        EditText assetName = (EditText)mainView.findViewById(R.id.edit_asset_name);
        EditText assetDescription = (EditText)mainView.findViewById(R.id.edit_asset_description);
        EditText assetTags = (EditText)mainView.findViewById(R.id.edit_asset_tags);

        String assetNameValue = assetName.getText().toString();
        String assetDescriptionValue = assetDescription.getText().toString();
        String assetTagsValue = assetTags.getText().toString();

        AssetRequest asset = new AssetRequest(assetNameValue,assetDescriptionValue,assetTagsValue);

        SparkDrive.createAsset(asset, new ISparkResponse<AssetResponse>() {
            @Override
            public void onSparkSuccess(AssetResponse responseObject) {
                String showResult  = "";
                Gson gson = new Gson();

                showResult = gson.toJson(responseObject,AssetResponse.class);

                ((TextView)mainView.findViewById(R.id.text_create_asset_result)).setText(showResult);
            }

            @Override
            public void onSparkFailure(String errorMessage) {
                ((TextView)mainView.findViewById(R.id.text_create_asset_result)).setText(errorMessage);
            }
        });

    }

}