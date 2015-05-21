package com.autodesk.spark.sdk.spark;

import com.autodesk.spark.sdk.models.Request.AssetRequest;
import com.autodesk.spark.sdk.models.Request.FileRequest;
import com.autodesk.spark.sdk.models.Request.MemberRequest;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.models.Response.FilesResponse;
import com.autodesk.spark.sdk.models.Response.MemberResponse;
import com.autodesk.spark.sdk.network.NetworkUtils;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;

/**
 * Created by ronnyremsnik on 4/29/15.
 */
public class SparkDrive {

    private NetworkUtils mNetworkUtils;

    public void setNetworkUtils (NetworkUtils NetworkUtils)
    {
        mNetworkUtils = NetworkUtils;
    }
    public NetworkUtils getNetworkUtils()
    {
        return mNetworkUtils;
    }

    private static SparkDrive mInstance;

    protected static SparkDrive getInstance()
    {
        if (mInstance == null)
            mInstance = new SparkDrive();

        return mInstance;

    }

    /** Get asset by asset ID.
     *
     * @param asset Asset request object containing the asset ID.
     * @param onAssetResponse Asset object response.
     */
    public static  void getAsset(AssetRequest asset,ISparkResponse<AssetResponse> onAssetResponse) {
            getInstance().sparkGetAsset(asset,onAssetResponse);
      }

    public void sparkGetAsset(AssetRequest asset,ISparkResponse<AssetResponse> onAssetResponse) {

        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().getAsset(asset, onAssetResponse);
        }

    }

    /** Get the current available assets.
     *
     * @param onAssetResponse Assets response object.
     */
    public static  void getAssets(ISparkResponse<AssetsListResponse> onAssetResponse) {
            getInstance().sparkGetAssets(onAssetResponse);
    }

    private void sparkGetAssets(ISparkResponse<AssetsListResponse> onAssetResponse) {
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().getAssets(onAssetResponse);
        }

    }

    /** Get Assets of Member, filled with member ID.
     *
     * @param member Member of the assets.
     * @param onAssetsResponse Assets response Object
     */
    public static void getMemberAssets(MemberRequest member, ISparkResponse<AssetsListResponse> onAssetsResponse)
    {
        getInstance().sparkGetMemberAssets(member,onAssetsResponse);
    }

    private void sparkGetMemberAssets(MemberRequest member,ISparkResponse<AssetsListResponse> onAssetResponse) {
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().getMemberAssets(member,onAssetResponse);
        }

    }


    /** Get member by member ID.
     *
     * @param member Member request object, usually filled with member ID.
     * @param onMemberResponse Member response object.
     */
    public static  void getMember(MemberRequest member,ISparkResponse<MemberResponse> onMemberResponse) {
        getInstance().sparkGetMember(member,onMemberResponse);

    }

    private void sparkGetMember(MemberRequest member,ISparkResponse<MemberResponse> onMemberResponse) {
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().getMember(member, onMemberResponse);
        }
    }

    /** Get the current member details
     *
     * @param onMemberResponse Member response object
     */
    public static void getCurrentMember(ISparkResponse<MemberResponse> onMemberResponse) {
        getInstance().sparkGetCurrentMember(onMemberResponse);
    }
    private void sparkGetCurrentMember(ISparkResponse<MemberResponse> onMemberResponse) {
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().getMember(new MemberRequest(""), onMemberResponse);
        }
    }

    /** Create new asset object.
     *
     * @param asset Asset object to create, filled with asset data.
     * @param onCreateAssetResponse Asset created response oobject.
     */
    public static  void createAsset(AssetRequest asset, ISparkResponse<AssetResponse> onCreateAssetResponse) {
        getInstance().sparkCreateAsset(asset, onCreateAssetResponse);
    }

    private void sparkCreateAsset(AssetRequest asset, ISparkResponse<AssetResponse> onCreateAssetResponse) {
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().createAsset(asset, onCreateAssetResponse);
        }
    }

    /** Create new file and upload it to the server.
     *
     * @param file New file to create, filled with file data and path.
     * @param onSparkResponse File response object.
     */
    public static void createFile(FileRequest file, ISparkResponse<FilesResponse> onSparkResponse){
        getInstance().sparkCreateFile(file, onSparkResponse);
    }

    private void sparkCreateFile(FileRequest file, ISparkResponse<FilesResponse> onSparkResponse){
        if (Spark.checkPreConfiguration()) {
            getNetworkUtils().createFile(file, onSparkResponse);
        }
    }



}
