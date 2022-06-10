package com.quannm18.quanlykho.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.quannm18.quanlykho.API.FinanceAPI;
import com.quannm18.quanlykho.Model.Finance;
import com.quannm18.quanlykho.Model.TotalStatistic;
import com.quannm18.quanlykho.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyFinanceFragment extends Fragment {
    private PieChart chartMonthly;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_monthly_finance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chartMonthly = (PieChart) view.findViewById(R.id.chartMonthly);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            Log.e("cs",showTime(1).toString());
            getData();
        }

        tvBoxRevenueValue = (TextView) view.findViewById(R.id.tvBoxRevenueValue);
        tvBoxDamageValue = (TextView) view.findViewById(R.id.tvBoxDamageValue);

        chartMonthly.notifyDataSetChanged();

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
        chartMonthly.setEntryLabelTextSize(0);
        chartMonthly.setData(new PieData(pieDataSet));
        chartMonthly.getDescription().setEnabled(false);
        chartMonthly.setCenterText("Revenue");
        chartMonthly.setCenterTextTypeface(typeface);
        chartMonthly.setCenterTextSize(16);
        chartMonthly.setCenterTextColor(Color.parseColor("#878787"));
        chartMonthly.setHoleRadius(46);

        chartMonthly.getLegend().setEnabled(false);
        chartMonthly.getLegend().mNeededHeight = 100;
        chartMonthly.getLegend().mNeededWidth = 100;
        chartMonthly.getLegend().setFormSize(40);
        chartMonthly.getLegend().setTextSize(16);
        chartMonthly.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        chartMonthly.getLegend().setForm(Legend.LegendForm.CIRCLE);
        chartMonthly.getLegend().setWordWrapEnabled(true);
    }

    void getData(){
        FinanceAPI.financeAPI.getStatisticMonth(showTime(1),showTime(0))
                .enqueue(new Callback<Finance>() {
                    @Override
                    public void onResponse(Call<Finance> call, Response<Finance> response) {
                        final Finance finance = response.body();

                        Log.e("fin",finance.getTongXuat()+"");
                        Log.e("fin",finance.getTongNhap()+"");
                        totalEntry = finance.getTongNhap();
                        totalX = finance.getTongXuat();

                        if (totalEntry!=200|| totalX!=100){
                            pieEntryList.clear();

                            PieEntry pieEntry = new PieEntry(totalEntry,"Thu");
                            pieEntryList.add(pieEntry);
                            PieEntry pieEntry1 = new PieEntry(totalX,"Chi");
                            pieEntryList.add(pieEntry1);

                            tvBoxDamageValue.setText(totalX+"");
                            tvBoxRevenueValue.setText(totalEntry+"");

                            chartMonthly.notifyDataSetChanged();
                            chartMonthly.invalidate();
                            Toast.makeText(getContext(), "Show", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Finance> call, Throwable t) {
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
}