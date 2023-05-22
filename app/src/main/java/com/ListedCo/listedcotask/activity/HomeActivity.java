package com.ListedCo.listedcotask.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ListedCo.listedcotask.R;
import com.ListedCo.listedcotask.adapter.LinksAdapter;
import com.ListedCo.listedcotask.api.Builder.Apibuilder;
import com.ListedCo.listedcotask.api.Interface.ApiInterface;
import com.ListedCo.listedcotask.api.response.RecentLink;
import com.ListedCo.listedcotask.api.response.dashboardNew;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Callback;


public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_links;
    LinksAdapter adapter;
    TextView txt_good_morning, txt_top_links, txt_recent_links;
    LinearLayout ll_progress;
    public ArrayList<RecentLink> recent_links;
    public ArrayList<RecentLink> top_links;
    public JsonObject chartData;
    LineChart gr_chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Init();
        ApiCall();
        setGreeting();
        OnclickEvents();

    }

    private void Init() {
        rv_links = findViewById(R.id.rv_product);
        txt_good_morning = findViewById(R.id.txt_good_morning);
        txt_recent_links = findViewById(R.id.recent_links);
        ll_progress = findViewById(R.id.ll_progress);
        txt_top_links = findViewById(R.id.top_links);
        gr_chart = findViewById(R.id.gr_chart);
        recent_links = new ArrayList<>();
        top_links = new ArrayList<>();
        rv_links.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LinksAdapter(HomeActivity.this);
        rv_links.setAdapter(adapter);
        ll_progress.setVisibility(View.VISIBLE);
    }

    private void OnclickEvents() {
        txt_recent_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_recent_links.setBackground(getResources().getDrawable(R.drawable.bg_txt_links));
                txt_top_links.setBackground(null);
                txt_top_links.setTextColor(getResources().getColor(R.color.black));
                txt_recent_links.setTextColor(getResources().getColor(R.color.white));
                adapter.setData(recent_links);
            }
        });

        txt_top_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_top_links.setBackground(getResources().getDrawable(R.drawable.bg_txt_links));
                txt_recent_links.setBackground(null);
                txt_recent_links.setTextColor(getResources().getColor(R.color.black));
                txt_top_links.setTextColor(getResources().getColor(R.color.white));
                adapter.setData(top_links);
            }
        });
    }

    private void setGreeting() {
        Calendar cal = Calendar.getInstance();
        int timeOfDay = cal.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            txt_good_morning.setText("Good Morning");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            txt_good_morning.setText("Good Afternoon");
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            txt_good_morning.setText("Good Evening");
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            txt_good_morning.setText("Good Night");
        }
    }

    private void ApiCall() {
        Apibuilder.getRetrofitClient(this).create(ApiInterface.class).dashboardNew().enqueue(new Callback<dashboardNew>() {
            @Override
            public void onResponse(retrofit2.Call<dashboardNew> call, retrofit2.Response<dashboardNew> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        recent_links = response.body().data.recent_links;
                        top_links = response.body().data.top_links;
                        adapter.setData(top_links);
                        chartData = response.body().data.overall_url_chart;

                        setGraph();
                    }
                }
                ll_progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(retrofit2.Call<dashboardNew> call, Throwable t) {
                Log.d("ResponseError", t.getMessage());
                ll_progress.setVisibility(View.GONE);
            }
        });
    }

    private void setGraph() {
        ArrayList<Entry> series = new ArrayList<>();
        series.add(new Entry(1f, chartData.get("2023-04-22").getAsFloat()));
        series.add(new Entry(2f, chartData.get("2023-04-23").getAsFloat()));
        series.add(new Entry(3f, chartData.get("2023-04-24").getAsFloat()));
        series.add(new Entry(4f, chartData.get("2023-04-25").getAsFloat()));
        series.add(new Entry(5f, chartData.get("2023-04-26").getAsFloat()));
        series.add(new Entry(6f, chartData.get("2023-04-27").getAsFloat()));
        series.add(new Entry(7f, chartData.get("2023-04-28").getAsFloat()));
        series.add(new Entry(8f, chartData.get("2023-04-29").getAsFloat()));
        series.add(new Entry(9f, chartData.get("2023-04-30").getAsFloat()));
        series.add(new Entry(10f, chartData.get("2023-05-01").getAsFloat()));
        series.add(new Entry(11f, chartData.get("2023-05-02").getAsFloat()));
        series.add(new Entry(12f, chartData.get("2023-05-03").getAsFloat()));
        series.add(new Entry(13f, chartData.get("2023-05-04").getAsFloat()));
        series.add(new Entry(14f, chartData.get("2023-05-05").getAsFloat()));
        series.add(new Entry(15f, chartData.get("2023-05-06").getAsFloat()));
        series.add(new Entry(16f, chartData.get("2023-05-07").getAsFloat()));
        series.add(new Entry(17f, chartData.get("2023-05-08").getAsFloat()));
        series.add(new Entry(18f, chartData.get("2023-05-09").getAsFloat()));
        series.add(new Entry(19f, chartData.get("2023-05-10").getAsFloat()));
        series.add(new Entry(20f, chartData.get("2023-05-11").getAsFloat()));
        series.add(new Entry(21f, chartData.get("2023-05-12").getAsFloat()));
        series.add(new Entry(22f, chartData.get("2023-05-13").getAsFloat()));
        series.add(new Entry(23f, chartData.get("2023-05-14").getAsFloat()));
        series.add(new Entry(24f, chartData.get("2023-05-15").getAsFloat()));
        series.add(new Entry(25f, chartData.get("2023-05-16").getAsFloat()));
        series.add(new Entry(26f, chartData.get("2023-05-17").getAsFloat()));
        series.add(new Entry(27f, chartData.get("2023-05-18").getAsFloat()));
        series.add(new Entry(28f, chartData.get("2023-05-19").getAsFloat()));
        series.add(new Entry(29f, chartData.get("2023-05-20").getAsFloat()));
        series.add(new Entry(30f, chartData.get("2023-05-21").getAsFloat()));
        series.add(new Entry(31f, chartData.get("2023-05-22").getAsFloat()));

        LineDataSet set = new LineDataSet(series, "Graph");
        set.setCircleColor(getResources().getColor(R.color.main_background));
//        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineData data = new LineData(set);
        gr_chart.setData(data);
        gr_chart.setBackgroundColor(getResources().getColor(R.color.white));
//        gr_chart.animateXY(2000, 2000, Easing.);

    }
}