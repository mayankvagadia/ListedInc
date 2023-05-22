package com.ListedCo.listedcotask.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/***
 *Created By Mayank
 **/
public class dashboardNew implements Serializable {

    @SerializedName("status")
    public String status;

    @SerializedName("statusCode")
    public int statusCode;

    @SerializedName("message")
    public String message;

    @SerializedName("support_whatsapp_number")
    public String support_whatsapp_number;

    @SerializedName("extra_income")
    public Float extra_income;

    @SerializedName("total_links")
    public int total_links;

    @SerializedName("total_clicks")
    public int total_clicks;

    @SerializedName("today_clicks")
    public int today_clicks;

    @SerializedName("top_source")
    public String top_source;

    @SerializedName("top_location")
    public String top_location;

    @SerializedName("startTime")
    public String startTime;

    @SerializedName("links_created_today")
    public int links_created_today;

    @SerializedName("applied_campaign")
    public int applied_campaign;

    @SerializedName("data")
    public data data;


}
