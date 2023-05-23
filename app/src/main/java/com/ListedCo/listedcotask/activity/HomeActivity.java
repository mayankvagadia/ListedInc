package com.ListedCo.listedcotask.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ListedCo.listedcotask.R;
import com.ListedCo.listedcotask.adapter.LinksAdapter;
import com.ListedCo.listedcotask.api.Builder.Apibuilder;
import com.ListedCo.listedcotask.api.Interface.ApiInterface;
import com.ListedCo.listedcotask.api.response.RecentLink;
import com.ListedCo.listedcotask.api.response.dashboardNew;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_links;
    LinksAdapter adapter;
    TextView txt_good_morning, txt_top_links, txt_recent_links;
    LinearLayout ll_progress;
    public ArrayList<RecentLink> recent_links;
    public ArrayList<RecentLink> top_links;
    public JsonObject chartData;
    LineChart gr_chart;
    ArrayList<String> dates;


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
        dates = new ArrayList<String>();
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
            public void onResponse(Call<dashboardNew> call, Response<dashboardNew> response) {
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
        float counter = 1f;
        for (int i = 30; i >= 0; i--) {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
            Calendar c1 = Calendar.getInstance();
            String currentDate = df.format(date);
            c1.add(Calendar.DAY_OF_YEAR, -i);
            df = new SimpleDateFormat("yyyy-MM-dd");
            Date resultDate = c1.getTime();
            dates.add(df.format(resultDate));
            counter++;
            series.add(new Entry(counter, chartData.get(df.format(resultDate)).getAsFloat()));
        }
        LineDataSet set = new LineDataSet(series, "Graph");
        set.setCircleColor(getResources().getColor(R.color.main_background));
        LineData data = new LineData(set);
        gr_chart.setData(data);
        gr_chart.setBackgroundColor(getResources().getColor(R.color.white));
    }
}