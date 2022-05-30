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
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

public class TotalFinanceFragment extends Fragment {
    private PieChart chartTotal;
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
    }
}