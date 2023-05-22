package com.ListedCo.listedcotask.api.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/***
 *Created By Mayank
 **/
public class data {

    @SerializedName("recent_links")
    public ArrayList<RecentLink> recent_links;

    @SerializedName("top_links")
    public ArrayList<RecentLink> top_links;

    @SerializedName("overall_url_chart")
    public JsonObject overall_url_chart;

}