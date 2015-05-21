package com.autodesk.spark.sdk.spark;

import android.content.Context;

import com.autodesk.spark.sdk.memory_manager.MemoryManager;
import com.autodesk.spark.sdk.models.SparkSession;
import com.autodesk.spark.sdk.network.NetworkUtils;
import com.autodesk.spark.sdk.utils.Constants;
import com.autodesk.spark.sdk.utils.DebugModeUtils;
import com.autodesk.spark.sdk.utils.Utils;

/**
 * Created by ronnyremsnik on 3/1/15.
 */
public class Spark {

    private static final String TAG = Spark.class.getSimpleName();

    private static Spark mInstance;

    private Context mContext;
    private NetworkUtils mNetworkUtils;

    private Spark() {

    }

    private static Spark getInstance() {
        if (mInstance == null) {
            mInstance = new Spark();
        }

        return mInstance;

    }

    /**
     * Init Spark API - Use this method the init the Spark API with the app key & app secret
     *
     * @param context Current activity
     * @param appKey Spark App Key from the dev portal
     * @param appSecret Spark App Secret from the dev portal
     */
    public static void init(Context context, String appKey, String appSecret,int envType) {
        getInstance().sparkInit(context, appKey, appSecret,envType);
    }

    /**
     * Enable Spark Debug Mode
     * @param debugMode Enabel the debug mode.
     *                  Debug messages appear in the logcat
     *
     */

    public static void setDebugMode(boolean debugMode) {
        MemoryManager.getInstance().setDebugMode(debugMode);
    }

    /**
     *
     * @return Current active session of the user
     * Active session contain the access token , access token type
     */

    public static SparkSession getActiveSession()
    {
        return SparkSession.getActiveSession();
    }

    private void sparkInit(Context context, String appKey, String appSecret,int envType) {
        this.mContext = context;

        // set the keys
        MemoryManager.getInstance().setAppKeySecret(appKey, appSecret,envType);

        // check that all configured well
        if (checkConfiguration()) {

            // create network utils for the api calls
            mNetworkUtils = new NetworkUtils(mContext);

            // init the sub variables for api calls
            SparkAuthentication.getInstance().setNetworkUtils(mNetworkUtils);

            SparkDrive.getInstance().setNetworkUtils(mNetworkUtils);

            SparkPrint.getInstance().setNetworkUtils(mNetworkUtils);


        }

    }


    private NetworkUtils getNetworkUtils()
    {
        return mNetworkUtils;
    }


    /**
     *  Get current context
     * @return Current context
     */

    public static Context getContext()
    {
        return getInstance().mContext;
    }

    /**  Check Pre Configuration before running spark API calls.
     *
     * @return true if the configuration is valid.
     * valid configuration when is when Spark.init is called
     */
    public static boolean checkPreConfiguration()
    {

        // check for existance of the access tokens
        if (MemoryManager.getInstance().getAccessToken().equals(""))
        {
            DebugModeUtils.logErrorMessage(TAG, Constants.SPARK_EXCEPTION_CONFIGURATION_ERROR,
                                                Constants.SPARK_EXCEPTION_CONFIGURATION_GET_TOKEN,
                                                Constants.SPARK_EXCEPTION_SPARK_TEAM);

            return false;
        }

        // Check pre Acces token
        if (!checkPreAccessToken())
        {
            return false;
        }

        return true;

    }

    /**  Check Pre Access Token before running spark API calls.
     *
     * @return true if the configuration is valid.
     * valid configuration when is when Accesstoken available.
     */
    public static boolean checkPreAccessToken()
    {

        // check for the init
        if (Spark.getInstance().getNetworkUtils() == null)
        {
            // init not called
            DebugModeUtils.logErrorMessage(TAG, Constants.SPARK_EXCEPTION_CONFIGURATION_ERROR,
                                                Constants.SPARK_EXCEPTION_CONFIGURATION_ADD_INIT,
                                                Constants.SPARK_EXCEPTION_SPARK_TEAM);


            return false;
        }

        return true;


    }

    private static boolean checkConfiguration()  {

        // check for permissions
        if (!Utils.checkForPermissions())
        {
            // permissions not found
            DebugModeUtils.logErrorMessage(TAG, Constants.SPARK_EXCEPTION_PERMISSIONS_ERROR,
                                                Constants.SPARK_EXCEPTION_PERMISSION_INTERNET,
                                                Constants.SPARK_EXCEPTION_PERMISSION_NETWORK,
                                                Constants.SPARK_EXCEPTION_SPARK_TEAM);

            return false;
        }

        //check for valid api keys
        if (MemoryManager.getInstance().getAppKey() == null)
        {

            // app key not found
            DebugModeUtils.logErrorMessage(TAG, Constants.SPARK_EXCEPTION_PERMISSIONS_ERROR,
                                                Constants.SPARK_EXCEPTION_CONFIGURATION_APPKEY,
                                                Constants.SPARK_EXCEPTION_SPARK_TEAM);


            return false;
        }



        return true;

    }




}
