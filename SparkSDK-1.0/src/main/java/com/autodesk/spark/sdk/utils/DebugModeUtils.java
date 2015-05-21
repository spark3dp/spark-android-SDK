package com.autodesk.spark.sdk.utils;

import android.util.Log;

/**
 * Created by Itsik Sweiry on 02/03/2015.
 * Debug mode helper for easier debug log handling
 */
public class DebugModeUtils {

    public static void logDebugMessage(String tag, String... debugMessages) {
        for (int i = 0; i < debugMessages.length; i++) {
            Log.d(tag, "==   " + debugMessages[i] + "   ==");
        }
    }

    public static void logErrorMessage(String tag, String... debugMessages) {
        if (debugMessages == null) {
            return;
        }

        String line = "=====================================================";

        Log.e(tag, line);
        for (int i = 0; i < debugMessages.length; i++) {
            Log.e(tag, "==   " + centerizeString(debugMessages[i],line.length() - 10) + "   ==");
        }
        Log.e(tag, line);
    }

    // Make nice look of log message
    private static String centerizeString(String str,int maxLength)
    {
        if (str.length() > maxLength) {
            return str;
        }
        else {
            int padLength = (maxLength - str.length()) / 2;
            String result = "";
            for (int i = 0; i < padLength; i++) {
                result += " ";
            }
            result += str;
            for (int i = 0; i < padLength; i++) {
                result += " ";
            }

            // parity
            if (str.length() % 2 == 0)
                result += " ";

            return result;
        }

    }
}
