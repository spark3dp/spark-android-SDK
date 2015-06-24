package com.autodesk.spark.sdk.utils;

/**
 * Created by ronnyremsnik on 3/1/15.
 */
public class Constants {

    public static final String SDK_VERSION = "1.0";

    // Spark Authorization types
    public static final int SPARK_AUTHORIZATION_TOKEN_TYPE_NONE = -1;
    public static final int SPARK_AUTHORIZATION_TOKEN_TYPE_GUEST = 0;
    public static final int SPARK_AUTHORIZATION_TOKEN_TYPE_REGULAR = 1;

    // Spark token types
    public static final String SPARK_TOKEN_TYPE_NONE = "Empty";
    public static final String SPARK_TOKEN_TYPE_GUEST = "Guest Token";
    public static final String SPARK_TOKEN_TYPE_REGULAR = "Access Token";

    // Spark environment types
    public static final int SPARK_ENV_TYPE_SANBOX = 0;
    public static final int SPARK_ENV_TYPE_PRODUCTION = 1;

    // Base Urls
    public static final String SPARK_SCHEME = "https";
    public static final String SPARK_SANDBOX_AUTHORITY_AND_PATH = "sandbox.spark.autodesk.com/api/v1";
    public static final String SPARK_PRODUCTION_AUTHORITY_AND_PATH = "api.spark.autodesk.com/api/v1";
    public static final String SPARK_BASE_URL_SANDBOX = SPARK_SCHEME + "://" + SPARK_SANDBOX_AUTHORITY_AND_PATH;
    public static final String SPARK_BASE_URL_PRODUCTION = SPARK_SCHEME + "://" + SPARK_PRODUCTION_AUTHORITY_AND_PATH;

    public static final String SPARK_BASE_URL_SANDBOX_NO_SCHEME = SPARK_SANDBOX_AUTHORITY_AND_PATH;
    public static final String SPARK_BASE_URL_PRODUCTION_NO_SCHEME = SPARK_PRODUCTION_AUTHORITY_AND_PATH;

    // API CALLS
    public final static String API_PRINTER_PID = "{{PID}}";
    public final static String API_MEMBER_MID = "{{MID}}";

    public final static String API_GET_GUEST_TOKEN="oauth/accesstoken";
    public final static String API_GET_REFRESH_TOKEN="oauth/refreshtoken";
    public final static String API_GET_ASSETS="assets";
    public final static String API_GET_MEMBERS_ASSETS = "members/"+ API_MEMBER_MID +"/assets";
    public final static String API_GET_MEMBERS="members";
    public final static String API_AUTHORIZE="oauth/authorize";
    public final static String API_CREATE_FILE="files/upload";
    public final static String API_PRINTER_REGISTER="print/printers/register";
    public final static String API_PRINTER_UNREGISTER="print/printers";
    public final static String API_PRINTER_CREATE_JOB = "print/printers/"+ API_PRINTER_PID +"/jobs";
    public final static String API_PRINTER_COMMAND_SEND = "print/printers/"+ API_PRINTER_PID +"/command";
    public final static String API_PRINTER_JOB_STATUS = "print/jobs";
    public final static String API_PRINTER_COMMAND_PAUSE = "pause";
    public final static String API_PRINTER_COMMAND_RESUME = "resume";
    public final static String API_PRINTER_COMMAND_CANCEL = "cancel";


    //Shared Preferences
    public static final String SHARED_PREFERENCE_ACCESS_TOKEN = "access_token";
    public static final String SHARED_PREFERENCE_REFRESH_TOKEN = "refresh_token";
    public static final String SHARED_PREFERENCE_AUTHORIZATION_TYPE = "authorization_type";
    public static final String SHARED_PREFERENCE_EXPIRES_AT = "expires_at";

    public static final String SHARED_PREFERENCE_APP_KEY = "app_key";
    public static final String SHARED_PREFERENCE_APP_SECRET = "app_secret";
    public static final String SHARED_PREFERENCE_APP_KEY_SECRET_BASE64 = "key_ssecret_base64";
    public static final String SHARED_PREFERENCE_DEBUG_MODE = "debug_mode";
    public static final String SHARED_PREFERENCE_BASE_URL = "base_url";

    // Spark Login - Query Params
    public static final String SPARK_LOGIN_REQUEST_PARAM_RESPONSE_TYPE = "response_type";
    public static final String SPARK_LOGIN_REQUEST_VALUE_RESPONSE_TYPE = "code";
    public static final String SPARK_LOGIN_REQUEST_PARAM_CLIENT_ID = "client_id";
    public static final String SPARK_LOGIN_REQUEST_PARAM_REDIRECT_URI = "redirect_uri";


    // Spark Login - Redirect URL
    public static final String SPARK_BOGUS_REDIRECT_URL = "http://www.[MY-WEB-ADDRESS-FOR-CALLBACK].com";

    // Spark Exceptions
    public static final String SPARK_EXCEPTION_CONFIGURATION_ERROR = "Configuration Error.";
    public static final String SPARK_EXCEPTION_PERMISSIONS_ERROR = "Permissions Error.";
    public static final String SPARK_EXCEPTION_SPARK_TEAM = "SPARK SDK TEAM.";

    public static final String SPARK_EXCEPTION_PERMISSION_INTERNET = "Please Define Internet Permission.";
    public static final String SPARK_EXCEPTION_PERMISSION_NETWORK = "Please Define Network Permission.";
    public static final String SPARK_EXCEPTION_CONFIGURATION_ADD_INIT = "Please Add Call To [ init ] Method.";
    public static final String SPARK_EXCEPTION_CONFIGURATION_GET_TOKEN = "Please Get Access Token First";
    public static final String SPARK_EXCEPTION_CONFIGURATION_APPKEY = "Please Add App Key.";
    public static final String SPARK_EXCEPTION_ACCESS_TOKEN_REQUIRED = "Guest Token not allows this operation.\nPlease use Access Token.";

    //Spark Server Exceptions
    public static final String SPARK_SERVER_ERROR_ACCESS_TOKEN_EXPIRED = "Access Token expired";
}
