package com.autodesk.spark.sdk.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.autodesk.spark.sdk.example.R;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.models.Request.AssetRequest;
import com.autodesk.spark.sdk.models.Request.MemberRequest;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.models.Response.MemberResponse;
import com.autodesk.spark.sdk.spark.SparkDrive;


public class DriveGeneralFragment extends Fragment {

    public View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_drive_general, container, false);

        initUI();

        return mainView;
    }

    public void initUI()
    {

        mainView.findViewById(R.id.button_get_asset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSparkAsset();
            }
        });

        mainView.findViewById(R.id.button_get_assets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSparkAssets();
            }
        });

        mainView.findViewById(R.id.button_get_member).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSparkMember();
            }
        });

        mainView.findViewById(R.id.button_get_member_assets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSparkMemberAssets();
            }
        });


    }

    public void getSparkAsset()
    {

        SparkDrive.getAsset(new AssetRequest("[ASSET-ID]"), new ISparkResponse<AssetResponse>() {

            @Override
            public void onSparkFailure(String errorMessage) {
                ((EditText) mainView.findViewById(R.id.text_asset_name)).setText(errorMessage);
            }

            @Override
            public void onSparkSuccess(AssetResponse responseObject) {
                ((EditText) mainView.findViewById(R.id.text_asset_name)).setText(responseObject.asset_name);
            }
        });
    }

    public void getSparkAssets()
    {

        SparkDrive.getAssets(new ISparkResponse<AssetsListResponse>() {
            @Override
            public void onSparkSuccess(AssetsListResponse responseObject) {

                String result = "";
                for(AssetResponse a : responseObject.assets)
                {
                    result += a.asset_name + ":" + a.asset_id + "\n";
                }

                ((EditText) mainView.findViewById(R.id.text_assets_name)).setText(result);
            }

            @Override
            public void onSparkFailure(String errorMessage) {

                ((EditText) mainView.findViewById(R.id.text_assets_name)).setText(errorMessage);
            }
        });
    }

    public void getSparkMemberAssets()
    {

        EditText memberEdit = (EditText)mainView.findViewById(R.id.edit_member_id);
        String memberID = memberEdit.getText().toString();

        MemberRequest member = new MemberRequest(memberID);

        SparkDrive.getMemberAssets(member,new ISparkResponse<AssetsListResponse>() {
            @Override
            public void onSparkSuccess(AssetsListResponse responseObject) {

                String result = "";
                for(AssetResponse a : responseObject.assets)
                {
                    result += a.asset_name + ":" + a.asset_id + "\n";
                }

                ((EditText) mainView.findViewById(R.id.text_member_asssets_result)).setText(result);
            }

            @Override
            public void onSparkFailure(String errorMessage) {

                ((EditText) mainView.findViewById(R.id.text_member_asssets_result)).setText(errorMessage);
            }
        });
    }

    public void getSparkMember()
    {
        SparkDrive.getCurrentMember(new ISparkResponse<MemberResponse>() {
            @Override
            public void onSparkSuccess(MemberResponse responseObject) {
                ((EditText) mainView.findViewById(R.id.text_member_name)).setText(responseObject.member.id + " , " +
                        responseObject.member.name + " , " +
                        responseObject.member.first_name);

                ((EditText) mainView.findViewById(R.id.edit_member_id)).setText(responseObject.member.id);

            }

            @Override
            public void onSparkFailure(String errorMessage) {
                ((EditText) mainView.findViewById(R.id.text_member_name)).setText(errorMessage);
            }
        });
    }



}