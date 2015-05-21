package com.autodesk.spark.sdk.models.Request;

/**
 * Created by ronnyremsnik on 5/17/15.
 */
public class CreateJobSettings
{
    public int FirstApproachRPM;
    public int FirstZLiftMicrons;
    public int FirstSeparationMicronsPerSec;
    public int FirstApproachMicronsPerSec;
    public int FirstRotationMilliDegrees;

    public CreateJobSettings(int firstApproachRPM, int firstZLiftMicrons, int firstSeparationMicronsPerSec, int firstApproachMicronsPerSec, int firstRotationMilliDegrees) {
        FirstApproachRPM = firstApproachRPM;
        FirstZLiftMicrons = firstZLiftMicrons;
        FirstSeparationMicronsPerSec = firstSeparationMicronsPerSec;
        FirstApproachMicronsPerSec = firstApproachMicronsPerSec;
        FirstRotationMilliDegrees = firstRotationMilliDegrees;
    }

    public static CreateJobSettings getDefaultSettings()
    {
        CreateJobSettings settings = new CreateJobSettings(6,2000,5000,5000,60000);
        return settings;
    }

}