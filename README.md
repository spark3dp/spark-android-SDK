#Spark Android SDK

This open-source SDK library enables you to easily integrate the Spark 3D printing API into your Android applications. 
Spark APIs are web based RESTful APIs providing an open, free and complete toolkit of professional-grade resources for 3D printing and related manufacturing processes. 

For more information about Spark see the [developers’ portal](https://spark.autodesk.com/developers/) and the [documentation home page](https://spark.autodesk.com/developers/reference/).

##Getting Started

Download the Spark Android SDK library (from?) and import the library into your project.

The Spark Android SDK comes with a sample app, located in the library’s App folder.

To use the Spark Android SDK you must first add an app on the [Spark Developer’s Portal](https://spark.autodesk.com/developers/myApps) and save the allocated app key and app secret. For more information see [the tutorial](https://spark.autodesk.com/developers/reference/introduction/tutorials/register-an-app).

##Setting Up the SDK

##1. Initialization

Call the init method passing the app key and app secret allocated by the [developer portal](https://spark.autodesk.com/developers/myApps):

<code>Spark.init(this, [APPKEY], [APPSECRET]);</code>

Enable debug mode to see logcat messages regarding your configuration and any error messages or notifications.<br>
<code>Spark.setDebugMode(true);</code>

##2. Authentication

Spark API use OAUTH 2.0 authentication. 
There are two types of authentication available:
Guest Token - For read only permissions. Gives you access to public data on Spark.
Access Token - For read\write access to a Spark member’s private data. Access Tokens require the user to log-in from your app.

###2.1 Generate a Guest Token

SparkAuthentication.getGuestToken(new ISparkResponse<AccessTokenResponse>() {
            @Override
            public void onSparkSuccess(AccessTokenResponse responseObject) {
                ((EditText) 
			
			// Success !
			// Call Spark API
            }

            @Override
            public void onSparkFailure(String errorMessage) {
			// Failure
// check error message
            }
        });

###2.2  Get Access Token

SparkAuthentication.getAuthorizationCode(getActivity(),new ISparkResponse<AccessTokenResponse>() {
            @Override
            public void onSparkSuccess(AccessTokenResponse responseObject) {
// Success !
			// Call Spark API


           }
         @Override
            public void onSparkFailure(String errorMessage) {
       			// Failure
// check error message
                    
}

        });




##3. Spark API Call Example

###3.1 Get Asset by ID

SparkDrive.getAsset(new AssetRequest([ASSET_ID]), new ISparkResponse<AssetResponse>() {

            @Override
            public void onSparkSuccess(AssetResponse responseObject) {


            }
            @Override
            public void onSparkFailure(String errorMessage) {


                          }
        });

##4. List of the Spark API Available in the SDK

###4.1 Authentication API

	SparkAuthentication.getGuestToken(...);
     SparkAuthentication.getAuthorizationCode(..);

The Authentication API authenticates users and apps and provides access to the other API. The API’s documentation: https://spark.autodesk.com/developers/reference/authentication

###4.2 Drive API

	SparkDrive.createAsset(...);
     SparkDrive.createFile(...);
     SparkDrive.getAsset(...);
SparkDrive.getMemberAssets(...);
     SparkDrive.getCurrentMember(...);
     SparkDrive.getAssets(...);
     SparkDrive.getMember(...);

The Drive API is used to store files and 3D models, to get access to other Spark members , upload files for printing, “like”, “favorite” and attach comments to models. 
API Documentation: https://spark.autodesk.com/developers/reference/drive

###4.3 Printer API

	  SparkPrint.createJob(...);
       SparkPrint.jobStatus(...);
       SparkPrint.commandSend(...);
       SparkPrint.registerPrinter(...);
       SparkPrint.unregisterPrinter(...);

Spark Print API heal and prepare 3D models for printing, register 3D printers for use, send commands and 3D printer job to 3D printers and monitor progress.
API Documentation: https://spark.autodesk.com/developers/reference/print 


##Feedback

Please report bugs or issues to Spark Support or simply let us know what you think of the SDK.
