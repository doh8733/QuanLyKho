package com.quannm18.quanlykho.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MonthlyFinanceFragment extends Fragment {
    private PieChart chartMonthly;
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


        final int[] pieColors = {
                Color.parseColor("#D76E68"),
                Color.parseColor("#70D0B3")
        };
        List<PieEntry> pieEntryList = new ArrayList<>();
        pieEntryList.add(new PieEntry(1000,"Thu"));
        pieEntryList.add(new PieEntry(1000,"Chi"));

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
}