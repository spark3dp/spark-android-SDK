package com.autodesk.spark.sdk.interfaces;

/**
 * Created by ronnyremsnik on 5/12/15.
 */
public interface ISparkResponse<T>
{
    void onSparkSuccess(T responseObject);
    void onSparkFailure(String errorMessage);

}