package com.autodesk.spark.sdk.network.login;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.autodesk.spark.sdk.interfaces.IOnWebViewAccessInvoke;
import com.autodesk.spark.sdk.network.login.LoginWebChromeClient;

/**
 * Created by ronnyremsnik on 5/12/15.
 */
public class LoginWebView extends WebView {

    private Dialog mDialog;
    private IOnWebViewAccessInvoke mOnWebViewInvoke;
    private LoginWebViewClient mLoginWebViewClient;

    public LoginWebView(Context context) {
        super(context);
        WebSettings webSettings = getSettings();
        clearCache(true);
//            clearFormData();
//            clearHistory();
        webSettings.setSaveFormData(false);
//            webSettings.setSavePassword(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        setWebChromeClient(new LoginWebChromeClient(mDialog));

        mLoginWebViewClient = new LoginWebViewClient();
        setWebViewClient(mLoginWebViewClient);

    }

    public void setWebViewAccessInvoke(IOnWebViewAccessInvoke OnWebViewAccessInvoke)
    {
        mOnWebViewInvoke = OnWebViewAccessInvoke;
        mLoginWebViewClient.setOnWebViewAccessInvoke(mOnWebViewInvoke);

    }
    public IOnWebViewAccessInvoke getWebViewAccessInvoke()
    {
        return mOnWebViewInvoke;
    }

    public void setDialog(Dialog dialog)
    {
        mDialog = dialog;

    }

    public Dialog getDialog(Context context) {
        if (mDialog == null) {
            //mDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            //mDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mDialog = new Dialog(context);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        }
        return mDialog;
    }


}