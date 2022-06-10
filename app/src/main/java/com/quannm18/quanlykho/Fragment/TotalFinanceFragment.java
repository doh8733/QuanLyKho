package com.quannm18.quanlykho.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.JsonObject;
import com.quannm18.quanlykho.API.FinanceAPI;
import com.quannm18.quanlykho.Model.Finance;
import com.quannm18.quanlykho.Model.TotalStatistic;
import com.quannm18.quanlykho.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalFinanceFragment extends Fragment {
    private PieChart chartTotal;
    private int totalEntry;
    private int totalX;
    private TextView tvBoxRevenueValue;
    private TextView tvBoxDamageValue;

    List<PieEntry> pieEntryList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalEntry = 1;
        totalX = 1;

        PieEntry pieEntry = new PieEntry(totalEntry,"Thu");
        pieEntryList.add(pieEntry);
        PieEntry pieEntry1 = new PieEntry(totalX,"Chi");
        pieEntryList.add(pieEntry1);
    }
    void getData(){
        FinanceAPI.financeAPI.getStatisticTotal(showTime(1),showTime(0))
                .enqueue(new Callback<TotalStatistic>() {
                    @Override
                    public void onResponse(Call<TotalStatistic> call, Response<TotalStatistic> response) {
                        final  TotalStatistic financeTotal = response.body();

                        Log.e("fin",financeTotal.getTongTienNhap()+"");
                        Log.e("fin",financeTotal.getTongTienXuat()+"");
                        totalEntry = financeTotal.getTongTienNhap();
                        totalX = financeTotal.getTongTienXuat();

                        if (totalX!=100||totalEntry!=200){

                            PieEntry pieEntry = new PieEntry(totalEntry,"Thu");
                            pieEntryList.add(pieEntry);
                            PieEntry pieEntry1 = new PieEntry(totalX,"Chi");
                            pieEntryList.add(pieEntry1);

                            tvBoxDamageValue.setText(totalX+"");
                            tvBoxRevenueValue.setText(totalEntry+"");

                            Toast.makeText(getContext(), "Show", Toast.LENGTH_SHORT).show();
                        }

                        chartTotal.notifyDataSetChanged();
                        chartTotal.invalidate();
                    }

                    @Override
                    public void onFailure(Call<TotalStatistic> call, Throwable t) {
                        Log.e("fin","Error");
                    }
                });
    }

    Date showTime(int i){
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            c.setTime(simpleDateFormat.parse((""+referenceDate.getYear()+"-"+referenceDate.getMonth()+"-"+referenceDate.getDay()+" "+referenceDate.getTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, -i);
        return c.getTime();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_total_finance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chartTotal = (PieChart) view.findViewById(R.id.chartTotal);


        tvBoxRevenueValue = (TextView) view.findViewById(R.id.tvBoxRevenueValue);
        tvBoxDamageValue = (TextView) view.findViewById(R.id.tvBoxDamageValue);

        chartTotal.notifyDataSetChanged();

        tvBoxDamageValue.setText(totalX+"");
        tvBoxRevenueValue.setText(totalEntry+"");

        final int[] pieColors = {
                Color.parseColor("#D76E68"),
                Color.parseColor("#70D0B3")
        };

        PieDataSet pieDataSet = new PieDataSet(pieEntryList,"Student");
        pieDataSet.setColors(pieColors);

        // gia tri ngay tren chart
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueTextColor(Color.parseColor("#FFFFFF"));
        Typeface typeface = ResourcesCompat.getFont(requireContext(), R.font.sen_bold);

        pieDataSet.setValueTypeface(typeface);
        pieDataSet.setValueTextSize(16);
        //chu thich ngay tren chart
        chartTotal.setEntryLabelTextSize(0);
        chartTotal.setData(new PieData(pieDataSet));
        chartTotal.getDescription().setEnabled(false);
        chartTotal.setCenterText("Revenue");
        chartTotal.setCenterTextTypeface(typeface);
        chartTotal.setCenterTextSize(16);
        chartTotal.setCenterTextColor(Color.parseColor("#878787"));
        chartTotal.setHoleRadius(46);

        chartTotal.getLegend().setEnabled(false);
        chartTotal.getLegend().mNeededHeight = 100;
        chartTotal.getLegend().mNeededWidth = 100;
        chartTotal.getLegend().setFormSize(40);
        chartTotal.getLegend().setTextSize(16);
        chartTotal.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        chartTotal.getLegend().setForm(Legend.LegendForm.CIRCLE);
        chartTotal.getLegend().setWordWrapEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            Log.e("cs",showTime(1).toString());
            getData();

        }
    }
}