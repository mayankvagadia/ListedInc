package com.ListedCo.listedcotask.api.response;

import com.google.gson.annotations.SerializedName;

/***
 *Created By Mayank
 **/
public class RecentLink {

    @SerializedName("url_id")
    public int url_id;

    @SerializedName("web_link")
    public String web_link;

    @SerializedName("smart_link")
    public String smart_link;

    @SerializedName("title")
    public String title;

    @SerializedName("total_clicks")
    public int total_clicks;

    @SerializedName("original_image")
    public String original_image;

    @SerializedName("thumbnail")
    public String thumbnail;

    @SerializedName("times_ago")
    public String times_ago;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("domain_id")
    public String domain_id;

    @SerializedName("url_prefix")
    public String url_prefix;

    @SerializedName("url_suffix")
    public String url_suffix;

    @SerializedName("app")
    public String app;
}