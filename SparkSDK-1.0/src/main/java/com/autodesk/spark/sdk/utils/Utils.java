package com.autodesk.spark.sdk.utils;

import android.Manifest;
import android.content.pm.PackageManager;

import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.spark.Spark;

/**
 * Created by ronnyremsnik on 3/3/15.
 */
public class Utils {

    public static boolean checkForPermissions()
    {
        String permissionInternet = Manifest.permission.INTERNET;
        String permissionNetwork =  Manifest.permission.ACCESS_NETWORK_STATE;

        int resInternet = Spark.getContext().checkCallingOrSelfPermission(permissionInternet);
        int resNetwork = Spark.getContext().checkCallingOrSelfPermission(permissionNetwork);

        return (resInternet == PackageManager.PERMISSION_GRANTED &&
                resNetwork == PackageManager.PERMISSION_GRANTED);

    }

    public static String getBaseURL()
    {
        return (MemoryManager.getInstance().geBaseURLType() == Constants.SPARK_ENV_TYPE_SANBOX) ?
                                                               Constants.SPARK_BASE_URL_SANDBOX :
                                                                Constants.SPARK_BASE_URL_PRODUCTION;
    }

    public static boolean needToUpdateAccessToken()
    {

        if (Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_REGULAR == MemoryManager.getInstance().getAuthorizationType())
        {
            // only on auth 2.0 there are refresh token

            if (MemoryManager.getInstance().getRefreshToken() != null)
            {
                // check if the time is correct to refresh the token

                long expireTime = MemoryManager.getInstance().getExpiresAt();

                return (System.currentTimeMillis() > expireTime);
            }

        }

        return false;
    }





}
