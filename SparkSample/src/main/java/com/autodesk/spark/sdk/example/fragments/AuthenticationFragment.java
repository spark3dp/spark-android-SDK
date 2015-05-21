package com.autodesk.spark.sdk.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.autodesk.spark.sdk.example.R;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.models.SparkSession;
import com.autodesk.spark.sdk.spark.Spark;
import com.autodesk.spark.sdk.spark.SparkAuthentication;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.utils.Constants;

public class AuthenticationFragment extends Fragment {

    public View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_authentication, container, false);

        initUI();

        return mainView;
    }

    public void initUI()
    {
        mainView.findViewById(R.id.button_guest_token).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grantSparkGuestToken();
            }
        });

        mainView.findViewById(R.id.button_access_token).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grantSparkAccessToken();
            }
        });

        initActiveSession();


    }


    public void initActiveSession()
    {
            EditText editAccessToken = (EditText)mainView.findViewById(R.id.edit_session_access_token);
            EditText editRefreshToken = (EditText)mainView.findViewById(R.id.edit_session_refresh_token);
            EditText editAccessTokenType = (EditText)mainView.findViewById(R.id.edit_session_access_type);

            SparkSession currentSession = Spark.getActiveSession();

            editAccessToken.setText(currentSession.mAccessToken);
            editRefreshToken.setText(currentSession.mRefreshToken);

            String tokenType = Constants.SPARK_TOKEN_TYPE_NONE;
            if (currentSession.mAuthorizationType == Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_REGULAR)
            {
                tokenType = Constants.SPARK_TOKEN_TYPE_REGULAR;
            }
            else if (currentSession.mAuthorizationType == Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_GUEST)
            {
                tokenType = Constants.SPARK_TOKEN_TYPE_GUEST;
            }

            editAccessTokenType.setText(tokenType);



    }

    public void grantSparkGuestToken()
    {
        SparkAuthentication.getGuestToken(new ISparkResponse<AccessTokenResponse>() {
            @Override
            public void onSparkSuccess(AccessTokenResponse responseObject) {
                ((EditText) mainView.findViewById(R.id.text_guest_token)).setText(responseObject.access_token);
                initActiveSession();
            }

            @Override
            public void onSparkFailure(String errorMessage) {
                ((EditText) mainView.findViewById(R.id.text_guest_token)).setText(errorMessage);
            }
        });
    }


    public void grantSparkAccessToken()
    {
        SparkAuthentication.getAuthorizationCode(new ISparkResponse<AccessTokenResponse>() {
            @Override
            public void onSparkSuccess(AccessTokenResponse responseObject) {
                ((EditText)mainView.findViewById(R.id.text_access_token)).setText(responseObject.access_token);
                initActiveSession();
            }

            @Override
            public void onSparkFailure(String errorMessage) {
                ((EditText)mainView.findViewById(R.id.text_access_token)).setText(errorMessage);
            }

        });

    }

}