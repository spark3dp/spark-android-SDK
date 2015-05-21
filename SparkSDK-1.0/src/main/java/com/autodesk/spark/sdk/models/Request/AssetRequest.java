package com.autodesk.spark.sdk.models.Request;

import com.google.gson.Gson;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class AssetRequest {

    public String asset_id;

    public String asset_name;
    public String asset_description;
    public String asset_tags;

    public AssetRequest(String asset_id) {
        this.asset_id = asset_id;
    }

    public AssetRequest(String assetName, String assetDescription, String assetTags)
    {
        this.asset_name = assetName;
        this.asset_description = assetDescription;
        this.asset_tags = assetTags;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this, AssetRequest.class);

    }

}
