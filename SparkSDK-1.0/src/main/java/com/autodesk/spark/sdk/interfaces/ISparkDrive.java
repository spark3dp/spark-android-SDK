package com.autodesk.spark.sdk.interfaces;

import com.autodesk.spark.sdk.models.Request.AssetRequest;
import com.autodesk.spark.sdk.models.Request.FileRequest;
import com.autodesk.spark.sdk.models.Request.MemberRequest;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.models.Response.FilesResponse;
import com.autodesk.spark.sdk.models.Response.MemberResponse;

/**
 * Created by ronnyremsnik on 4/28/15.
 */
public interface ISparkDrive {

    void sparkGetAsset(AssetRequest asset, ISparkResponse<AssetResponse> onAssetResponse);

    void sparkGetAssets(ISparkResponse<AssetsListResponse> onAssetResponse);

    void sparkGetMemberAssets(MemberRequest member, ISparkResponse<AssetsListResponse> onAssetsResponse);

    void sparkGetMember(MemberRequest member, ISparkResponse<MemberResponse> onMemberResponse);

    void sparkCreateAsset(AssetRequest asset, ISparkResponse<AssetResponse> onCreateAssetResponse);

    void sparkCreateFile(FileRequest file, ISparkResponse<FilesResponse> onSparkResponse);

}
