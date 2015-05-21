package com.autodesk.spark.sdk.network.wrappers;

/**
 * Created by ronnyremsnik on 5/12/15.
 */
import android.content.Context;

import com.autodesk.spark.sdk.interfaces.ISparkAuthentication;
import com.autodesk.spark.sdk.interfaces.ISparkDrive;
import com.autodesk.spark.sdk.interfaces.ISparkPrint;


public abstract class BaseNetworkWrapper implements ISparkAuthentication,
                                             ISparkDrive,
                                             ISparkPrint {

    private Context mContext;

    public BaseNetworkWrapper(Context context)
    {
           mContext = context;
    }

    public Context getContext()
    {
        return mContext;
    }
}
