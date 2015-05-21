package com.autodesk.spark.sdk.network.wrappers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.autodesk.spark.sdk.models.Request.AssetRequest;
import com.autodesk.spark.sdk.models.Request.AuthCodeRequest;
import com.autodesk.spark.sdk.models.Request.CommandSendRequest;
import com.autodesk.spark.sdk.models.Request.CreateJobRequest;
import com.autodesk.spark.sdk.models.Request.FileRequest;
import com.autodesk.spark.sdk.models.Request.MemberRequest;
import com.autodesk.spark.sdk.models.Request.PrinterJobStatusRequest;
import com.autodesk.spark.sdk.models.Request.PrinterRegisterRequest;
import com.autodesk.spark.sdk.models.Request.PrinterUnregisterRequest;
import com.autodesk.spark.sdk.models.Request.RefreshAccessTokenRequest;
import com.autodesk.spark.sdk.models.Response.AccessTokenResponse;
import com.autodesk.spark.sdk.models.Response.AssetResponse;
import com.autodesk.spark.sdk.models.Response.AssetsListResponse;
import com.autodesk.spark.sdk.models.Response.CommandSendResponse;
import com.autodesk.spark.sdk.models.Response.CreateJobResponse;
import com.autodesk.spark.sdk.models.Response.FilesResponse;
import com.autodesk.spark.sdk.models.Response.MemberResponse;
import com.autodesk.spark.sdk.models.Response.PrinterJobStatusResponse;
import com.autodesk.spark.sdk.models.Response.PrinterRegisterResponse;
import com.autodesk.spark.sdk.network.authentication.AccessTokenTask;
import com.autodesk.spark.sdk.network.authentication.GuestTokenTask;
import com.autodesk.spark.sdk.network.authentication.RefreshTokenTask;
import com.autodesk.spark.sdk.network.drive.AssetTask;
import com.autodesk.spark.sdk.network.drive.AssetsListRequest;
import com.autodesk.spark.sdk.network.drive.CreateAssetTask;
import com.autodesk.spark.sdk.network.drive.CreateFileTask;
import com.autodesk.spark.sdk.network.drive.MemberAssetsListRequest;
import com.autodesk.spark.sdk.network.drive.MemberTask;
import com.autodesk.spark.sdk.network.print.CommandSendTask;
import com.autodesk.spark.sdk.network.print.CreateJobTask;
import com.autodesk.spark.sdk.network.print.PrinterJobStatusTask;
import com.autodesk.spark.sdk.network.print.RegisterPrinterTask;
import com.autodesk.spark.sdk.network.print.UnregisterPrinterTask;
import com.autodesk.spark.sdk.interfaces.ISparkResponse;
import com.autodesk.spark.sdk.queue_manager.QueueManager;

/**
 * Created by ronnyremsnik on 5/12/15.
 */
public class VolleyWrapper extends BaseNetworkWrapper {

    private QueueManager mQueueManager;

    public VolleyWrapper(Context context)
    {
      super(context);

      mQueueManager = new QueueManager(context);

    }

    public RequestQueue getQueue()
    {
        return mQueueManager.getQueue();
    }

    @Override
    public void sparkGetGuestToken(ISparkResponse<AccessTokenResponse> onGuestTokenResponse) {
        new GuestTokenTask(onGuestTokenResponse).execute(getQueue());
    }

    @Override
    public void sparkGetAccessToken(AuthCodeRequest authCode, ISparkResponse<AccessTokenResponse> onAccessTokenResponse) {
        new AccessTokenTask(authCode,onAccessTokenResponse).execute(getQueue());
    }

    @Override
    public void sparkGetRefreshToken(RefreshAccessTokenRequest refreshCode, ISparkResponse<AccessTokenResponse> onRefreshTokenResponse) {
        new RefreshTokenTask(refreshCode,onRefreshTokenResponse).execute(getQueue());
    }

    @Override
    public void sparkGetAsset(AssetRequest asset, ISparkResponse<AssetResponse> onAssetResponse) {
        new AssetTask(asset, onAssetResponse).execute(getQueue());

    }

    @Override
    public void sparkGetAssets(ISparkResponse<AssetsListResponse> onAssetResponse) {
        new AssetsListRequest(onAssetResponse).execute(getQueue());

    }

    @Override
    public void sparkGetMemberAssets(MemberRequest member, ISparkResponse<AssetsListResponse> onAssetsResponse) {
        new MemberAssetsListRequest(member,onAssetsResponse).execute(getQueue());
    }

    @Override
    public void sparkGetMember(MemberRequest member, ISparkResponse<MemberResponse> onMemberResponse) {
        new MemberTask(member,onMemberResponse).execute(getQueue());

    }

    @Override
    public void sparkCreateAsset(AssetRequest asset, ISparkResponse<AssetResponse> onCreateAssetResponse) {
        new CreateAssetTask(asset, onCreateAssetResponse).execute(getQueue());

    }

    @Override
    public void sparkCreateFile(FileRequest file, ISparkResponse<FilesResponse> onSparkResponse) {
        new CreateFileTask(file, onSparkResponse).execute(getQueue());

    }

    @Override
    public void sparkRegisterPrinter(PrinterRegisterRequest printer, ISparkResponse<PrinterRegisterResponse> onSparkResponse) {
        new RegisterPrinterTask(printer, onSparkResponse).execute(getQueue());

    }

    @Override
    public void sparkUnregisterPrinter(PrinterUnregisterRequest printer, ISparkResponse<Object> onSparkResponse) {
        new UnregisterPrinterTask(printer,onSparkResponse).execute(getQueue());

    }

    @Override
    public void sparkCreateJob(CreateJobRequest printerJob, ISparkResponse<CreateJobResponse> onSparkResponse)
    {
        new CreateJobTask(printerJob, onSparkResponse).execute(getQueue());
    }

    @Override
    public void sparkCommandSend(CommandSendRequest command, ISparkResponse<CommandSendResponse> onSparkResponse)
    {
        new CommandSendTask(command, onSparkResponse).execute(getQueue());
    }

    @Override
    public void sparkJobStatus(PrinterJobStatusRequest job, ISparkResponse<PrinterJobStatusResponse> onSparkResponse) {

        new PrinterJobStatusTask(job,onSparkResponse).execute(getQueue());

    }
}
