package com.autodesk.spark.sdk.network;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import java.net.URLDecoder;

import com.autodesk.spark.sdk.R;
import com.autodesk.spark.sdk.interfaces.IOnWebViewAccessInvoke;
import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.models.Request.AssetRequest;
import com.autodesk.spark.sdk.models.Request.AuthCodeRequest;
import com.autodesk.spark.sdk.models.Request.CommandSendRequest;
import com.autodesk.spark.sdk.models.Request.CreateJobRequest;
import com.autodesk.spark.sdk.models.Request.FileRequest;
import com.autodesk.spark.sdk.models.Request.MemberRequest;
import com.autodesk.spark.sdk.models.Request.PrinterJobStatusRequest;
import com.autodesk.spark.sdk.models.Request.PrinterRegisterRequest;
import com.autodesk.spark.sdk.models.Request.PrinterUnregisterRequest;
import com.autodesk.spark.sdk.models.Request.RefreshAccessTokenRequest;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.models.Response.CommandSendResponse;
import com.autodesk.spark.sdk.models.Response.CreateJobResponse;
import com.autodesk.spark.sdk.models.Response.FilesResponse;
import com.autodesk.spark.sdk.models.Response.MemberResponse;
import com.autodesk.spark.sdk.models.Response.PrinterJobStatusResponse;
import com.autodesk.spark.sdk.models.Response.PrinterRegisterResponse;
import com.autodesk.spark.sdk.network.wrappers.BaseNetworkWrapper;
import com.autodesk.spark.sdk.network.wrappers.VolleyWrapper;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.utils.DebugModeUtils;
import com.autodesk.spark.sdk.utils.Utils;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;


import android.widget.RelativeLayout;

import com.autodesk.spark.sdk.network.login.*;

/**
 * Created by ronnyremsnik on 3/1/15.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private Context mContext;
    private BaseNetworkWrapper mWrapper;

    public NetworkUtils(Context context) {

        mContext = context;

        // use this to change the network wrapper
        mWrapper = new VolleyWrapper(mContext);

    }

    // API with no need of Access Token
    //----------------------------------

    public void getGuestToken(final ISparkResponse<AccessTokenResponse> onGuestTokenResponse) {
        // Call the class that encapsulate the call
        mWrapper.sparkGetGuestToken(onGuestTokenResponse);
    }

    public void getAccessToken(AuthCodeRequest authCode, final ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {
        // Call the class that encapsulate the call
        mWrapper.sparkGetAccessToken(authCode, onAccessTokenResponse);
    }

    public void getAuthorizationCode(final ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {
        initAndShowWebview(onAccessTokenResponse);
    }

    public void getRefreshToken(RefreshAccessTokenRequest refreshCode, final ISparkResponse<AccessTokenResponse> onRefreshTokenResponse) {

        mWrapper.sparkGetRefreshToken(refreshCode, onRefreshTokenResponse);
    }


    public void callWithUpdateRefreshToken(final Runnable action, final ISparkResponse<?> onResponse) {
        // override the flag
        callWithUpdateRefreshToken(action, onResponse, false);
    }

    public void callWithUpdateRefreshToken(final Runnable action, final ISparkResponse<?> onResponse, boolean checkToken) {

        if (checkToken && isGuestTokenType(onResponse)) {
            //on guest token type raise error and quit
            return;
        }

        if (Utils.needToUpdateAccessToken()) {
            // update call
            getRefreshToken(new RefreshAccessTokenRequest(MemoryManager.getInstance().getRefreshToken()), new ISparkResponse<AccessTokenResponse>() {
                @Override
                public void onSparkSuccess(AccessTokenResponse responseObject) {

                    action.run();
                }

                @Override
                public void onSparkFailure(String errorMessage) {

                    onResponse.onSparkFailure(errorMessage);

                    // on refresh error, try to open the login screen again
                    initAndShowWebview(new ISparkResponse<AccessTokenResponse>() {
                        @Override
                        public void onSparkSuccess(AccessTokenResponse responseObject) {

                            // on success, call the action to perform
                            action.run();
                        }

                        @Override
                        public void onSparkFailure(String errorMessage) {

                        }
                    });


                }
            });

        } else {
            // regular call
            action.run();
        }

    }

    // API that updates the Access Token when expire
    //----------------------------------------------
    public void getAsset(final AssetRequest asset, final ISparkResponse<AssetResponse> onAssetResponse) {
        // Call the class that encapsulate the call

        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkGetAsset(asset, onAssetResponse);

            }
        }, onAssetResponse);

    }

    public void getAssets(final ISparkResponse<AssetsListResponse> onAssetResponse) {

        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkGetAssets(onAssetResponse);

            }
        }, onAssetResponse);
    }

    public void getMemberAssets(final MemberRequest member,final ISparkResponse<AssetsListResponse> onAssetResponse) {

        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkGetMemberAssets(member,onAssetResponse);

            }
        }, onAssetResponse,true);
    }

    public void getMember(final MemberRequest member, final ISparkResponse<MemberResponse> onMemberResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkGetMember(member, onMemberResponse);

            }
        }, onMemberResponse);
    }

    public boolean isGuestTokenType(final ISparkResponse<?> onResponse) {
        if (Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_REGULAR != MemoryManager.getInstance().getAuthorizationType()) {
            // auth 2.0 required and not guest token
            onResponse.onSparkFailure(Constants.SPARK_EXCEPTION_ACCESS_TOKEN_REQUIRED);
            return true;
        }

        return false;
    }

    public void createAsset(final AssetRequest asset, final ISparkResponse<AssetResponse> onCreateAssetResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkCreateAsset(asset, onCreateAssetResponse);

            }
        }, onCreateAssetResponse, true);
    }

    public void createFile(final FileRequest file, final ISparkResponse<FilesResponse> onSparkResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkCreateFile(file, onSparkResponse);

            }
        }, onSparkResponse, true);
    }


    public void registerPrinter(final PrinterRegisterRequest printer, final ISparkResponse<PrinterRegisterResponse> onSparkResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkRegisterPrinter(printer, onSparkResponse);

            }
        }, onSparkResponse, true);
    }

    public void unregisterPrinter(final PrinterUnregisterRequest printer, final ISparkResponse<Object> onSparkResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkUnregisterPrinter(printer, onSparkResponse);

            }
        }, onSparkResponse, true);
    }

    public void createJob(final CreateJobRequest printerJob, final ISparkResponse<CreateJobResponse> onSparkResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkCreateJob(printerJob, onSparkResponse);

            }
        }, onSparkResponse, true);
    }

    public void commandSend(final CommandSendRequest command, final ISparkResponse<CommandSendResponse> onSparkResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkCommandSend(command, onSparkResponse);

            }
        }, onSparkResponse, true);
    }

    public void jobStatus(final PrinterJobStatusRequest jobStatus, final ISparkResponse<PrinterJobStatusResponse> onSparkResponse) {
        callWithUpdateRefreshToken(new Runnable() {
            @Override
            public void run() {

                mWrapper.sparkJobStatus(jobStatus,onSparkResponse);

            }
        }, onSparkResponse, true);
    }

    private void initAndShowWebview(final ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {
//        final ProgressDialog pd = new ProgressDialog(activity);
//        pd.setIndeterminate(true);
//        pd.show();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(Constants.SPARK_SCHEME)
                .authority(Utils.getBaseURL())
                .appendPath(Constants.API_AUTHORIZE)
                .appendQueryParameter(Constants.SPARK_LOGIN_REQUEST_PARAM_RESPONSE_TYPE, Constants.SPARK_LOGIN_REQUEST_VALUE_RESPONSE_TYPE)
                .appendQueryParameter(Constants.SPARK_LOGIN_REQUEST_PARAM_CLIENT_ID, MemoryManager.getInstance().getAppKey())
                .appendQueryParameter(Constants.SPARK_LOGIN_REQUEST_PARAM_REDIRECT_URI, Constants.SPARK_BOGUS_REDIRECT_URL);
        Uri uri = builder.build();
        final String initialUrl = URLDecoder.decode(uri.toString());


        LoginWebView webView = new LoginWebView(mContext);

        if (MemoryManager.getInstance().getDebugMode()) {
            DebugModeUtils.logDebugMessage(TAG, "Logging into Spark with URL:", initialUrl);
        }

        webView.loadUrl(initialUrl);

        final Dialog dialog = webView.getDialog(mContext);
        dialog.show();

        View mainView = View.inflate(mContext,R.layout.layout_login_dialog,null);
        RelativeLayout relativeWrapper = (RelativeLayout) mainView.findViewById(R.id.realtive_webview_wrapper);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeWrapper.addView(webView, lp);

        dialog.setContentView(mainView);

        webView.setWebViewAccessInvoke(new IOnWebViewAccessInvoke() {
            @Override
            public void onWebViewAccessInvoke(String url) {

                dialog.dismiss();

                loadURLAuthorizationCode(url, onAccessTokenResponse);

            }
        });

    }

    private void loadURLAuthorizationCode(String url, ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {

        if (url.length() > 0) {
            Uri returnURL = Uri.parse(url);
            if (returnURL != null) {
                String code = returnURL.getQueryParameter(Constants.SPARK_LOGIN_REQUEST_VALUE_RESPONSE_TYPE);
                if (code != null) {
                    getAccessToken(new AuthCodeRequest(code), onAccessTokenResponse);
                }
            }
        }

    }


}



