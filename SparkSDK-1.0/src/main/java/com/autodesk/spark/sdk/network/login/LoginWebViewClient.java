package com.autodesk.spark.sdk.network.login;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.autodesk.spark.sdk.interfaces.IOnWebViewAccessInvoke;
import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.utils.DebugModeUtils;

/**
 * Created by ronnyremsnik on 5/12/15.
 */
public class LoginWebViewClient extends WebViewClient {

    private static final String TAG = LoginWebViewClient.class.getSimpleName();

    private boolean loadingFinished = true;
    private IOnWebViewAccessInvoke mOnWebViewInvoke;


    public void setOnWebViewAccessInvoke(IOnWebViewAccessInvoke OnWebViewInvoke)
    {
        mOnWebViewInvoke = OnWebViewInvoke;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (url.startsWith(Constants.SPARK_BOGUS_REDIRECT_URL)) {
            if (MemoryManager.getInstance().getDebugMode()) {
                DebugModeUtils.logErrorMessage(TAG, "Loading URL", url);

                if (mOnWebViewInvoke != null)
                {
                    mOnWebViewInvoke.onWebViewAccessInvoke(url);
                }

            }

        }


        return super.shouldOverrideUrlLoading(view, url);
    }

}