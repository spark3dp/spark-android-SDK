package com.autodesk.spark.sdk.models.Response;

import java.util.ArrayList;

/**
 * Created by ronnyremsnik on 3/2/15.
 */
public class AssetResponse {

  public String _link;
  public int asset_id;
   public String asset_name;
   public int asset_status_code;
   public String date_submitted;
   public String delivery_media;
   public String description;
   public int used_in_project_count;
   public int child_asset_count;
   public int download_count;
   public ArrayList<String> favorited_by;
   public int favorite_count;
   public int view_count;
   public boolean is_authorized_download;
   public boolean is_deleted;
   public boolean is_download_asset;
   public int progress_status;
   public boolean is_member_favorite;
   public boolean is_gallery_asset;
   public boolean is_online_viewer_available;
   public boolean is_premium;
   public boolean is_repurchasable;
   public boolean is_reviewable;
   public boolean is_store_asset;
   public String keywords;
   public String language;
   //public int model_type;
   public String media_type;
   public int status_post_count;
   public String parent_asset_id;
   public String purchase_date;
   public boolean mesh;
   public String region;
   public int software;


}
