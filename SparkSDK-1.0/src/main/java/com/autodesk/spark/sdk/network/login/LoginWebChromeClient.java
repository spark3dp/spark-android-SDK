package com.autodesk.spark.sdk.network.login;

import android.app.Dialog;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.autodesk.spark.sdk.interfaces.IOnWebViewAccessInvoke;
import com.autodesk.spark.sdk.utils.DebugModeUtils;

/**
 * Created by ronnyremsnik on 5/12/15.
 */
public class LoginWebChromeClient extends WebChromeClient {

    private static final String TAG = WebChromeClient.class.getSimpleName();


    public Dialog mDialog;

    public LoginWebChromeClient(Dialog dialog)
    {
        mDialog = dialog;
    }

    @Override
    public void onCloseWindow(WebView window) {

        String url = window.getUrl();

        DebugModeUtils.logErrorMessage(TAG, "Close Window ", url);


        super.onCloseWindow(window);
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean userGesture, Message resultMsg) {
        Log.d(TAG, "Creating new window");
        Dialog dialog = ((LoginWebView)view).getDialog(view.getContext());
        IOnWebViewAccessInvoke mInvoke = ((LoginWebView)view).getWebViewAccessInvoke();

        LoginWebView childView = new LoginWebView(view.getContext());
        childView.setWebViewAccessInvoke(mInvoke);

        childView.setDialog(dialog);
        dialog.setContentView(childView);

            /*
            RelativeLayout relativeWrapper = (RelativeLayout)dialog.findViewById(R.id.realtive_webview_wrapper);
            if (relativeWrapper.getVisibility() == View.VISIBLE)
            {
                relativeWrapper.setVisibility(View.INVISIBLE);
                View progressBar = dialog.findViewById(R.id.login_progress);
                progressBar.setVisibility(View.VISIBLE);

            }
            */



        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
        transport.setWebView(childView);
        resultMsg.sendToTarget();
        return true;
    }
}
