package com.autodesk.spark.sdk.memory_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.autodesk.spark.sdk.spark.Spark;
import com.autodesk.spark.sdk.utils.Constants;

import java.io.UnsupportedEncodingException;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class MemoryManager {

    private static final String TAG = MemoryManager.class.getSimpleName();

    private SharedPreferences mPrefs;

    private String mAccessToken;
    private String mRefreshToken;
    private String mAppKey;
    private String mAppSecret;
    private String mAppKeySecretBase64;
    private int    mAuthorizationType;
    private boolean mDebugMode;
    private long    mExpiresAt;
    private int mBaseURLType;

    private static MemoryManager mInstance;

    private MemoryManager()
    {
        mPrefs = Spark.getContext().getSharedPreferences(Spark.getContext().getPackageName(),Context.MODE_PRIVATE);
        loadSettings();
    }

    public static MemoryManager getInstance() {
        if (mInstance == null) {
            mInstance = new MemoryManager();
        }

        return mInstance;
    }

    private void loadSettings() {
        mAccessToken = mPrefs.getString(Constants.SHARED_PREFERENCE_ACCESS_TOKEN,"");
        mRefreshToken = mPrefs.getString(Constants.SHARED_PREFERENCE_REFRESH_TOKEN,"");
        mAuthorizationType = mPrefs.getInt(Constants.SHARED_PREFERENCE_AUTHORIZATION_TYPE, Constants.SPARK_AUTHORIZATION_TOKEN_TYPE_NONE);
        mExpiresAt = mPrefs.getLong(Constants.SHARED_PREFERENCE_EXPIRES_AT, 0);
        mAppKey = mPrefs.getString(Constants.SHARED_PREFERENCE_APP_KEY, "");
        mAppSecret = mPrefs.getString(Constants.SHARED_PREFERENCE_APP_SECRET,"");
        mAppKeySecretBase64 = mPrefs.getString(Constants.SHARED_PREFERENCE_APP_KEY_SECRET_BASE64,"");
        mDebugMode = mPrefs.getBoolean(Constants.SHARED_PREFERENCE_DEBUG_MODE, false);
        mBaseURLType = mPrefs.getInt(Constants.SHARED_PREFERENCE_BASE_URL,0);

    }

    private void setStringPreference(String key, String value) {
        SharedPreferences.Editor editor =  mPrefs.edit();
        editor.putString(key,value);
        editor.apply();
    }

    private void setIntPreference(String key, int value) {
        SharedPreferences.Editor editor =  mPrefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private void setLongPreference(String key, long value) {
        SharedPreferences.Editor editor =  mPrefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    private void setBooleanPreference(String key, boolean value) {
        SharedPreferences.Editor editor =  mPrefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
        setStringPreference(Constants.SHARED_PREFERENCE_ACCESS_TOKEN, mAccessToken);
    }

    public String getAccessToken()
    {
        return mAccessToken;
    }

    public void setAppKey(String appKey) {
        mAppKey = appKey;
        setStringPreference(Constants.SHARED_PREFERENCE_APP_KEY, mAppKey);
    }

    public String getAppKey()
    {
        return mAppKey;
    }

    public void setAppSecret(String appSecret) {
        mAppSecret = appSecret;
        setStringPreference(Constants.SHARED_PREFERENCE_APP_SECRET, mAppSecret);
    }

    public String getAppSecret()
    {
        return mAppSecret;
    }

    public void setAppKeySecretBase64(String appKeySecretBase64) {
        mAppKeySecretBase64 = appKeySecretBase64;
        setStringPreference(Constants.SHARED_PREFERENCE_APP_KEY_SECRET_BASE64, mAppKeySecretBase64);
    }

    public String getAppKeySecretBase64()
    {
        return mAppKeySecretBase64;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
        setStringPreference(Constants.SHARED_PREFERENCE_REFRESH_TOKEN, mRefreshToken);
    }

    public String getRefreshToken()
    {
        return mRefreshToken;
    }


    public void setAuthorizationType(int authType) {
        mAuthorizationType = authType;
        setIntPreference(Constants.SHARED_PREFERENCE_AUTHORIZATION_TYPE,authType);
    }

    public int getAuthorizationType()
    {
        return mAuthorizationType;
    }

    public void setExpiresAt(long expiresAt) {
        mExpiresAt = expiresAt;
        setLongPreference(Constants.SHARED_PREFERENCE_EXPIRES_AT,expiresAt);

    }

    public long getExpiresAt()
    {
        return mExpiresAt;
    }

    public boolean getDebugMode ()
    {
        return mDebugMode;
    }

    public void setDebugMode(boolean debugMode)
    {
        mDebugMode = debugMode;
        setBooleanPreference(Constants.SHARED_PREFERENCE_DEBUG_MODE,mDebugMode);
    }

    private void setAppKeySecretBase64()
    {
        String textToEncode = mAppKey + ":" + mAppSecret;
        try {
            byte[] data = textToEncode.getBytes("UTF-8");
            mAppKeySecretBase64 = Base64.encodeToString(data, Base64.NO_WRAP);

            Log.d(TAG, String.format("Spark Config BASE64 : %s", mAppKeySecretBase64));

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setAppKeySecret(String appKey,String appSecret,int envType)
    {
        Log.d(TAG, String.format("Spark Config : %s:%s ", appKey, appSecret));

        setAppKey(appKey);
        setAppSecret(appSecret);
        setAppKeySecretBase64();
        setBaseURLType(envType);
    }

    public void setBaseURLType(int baseURLType)
    {
        mBaseURLType = baseURLType;
        setIntPreference(Constants.SHARED_PREFERENCE_BASE_URL,mBaseURLType);
    }
    public int geBaseURLType()
    {
        return mBaseURLType;
    }

}
