package com.quannm18.quanlykho.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.ChooseDepotActivity;
import com.quannm18.quanlykho.Model.Depot;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.R;

import java.util.List;

public class SubChooseAdapter extends RecyclerView.Adapter<SubChooseAdapter.ChooseHolder> {
    private List<Integer> floorList;
    private int current_index;
    public static int floor_size;
    public SubChooseAdapter(List<Integer> floorList) {
        this.floorList = floorList;
    }

    @NonNull
    @Override
    public ChooseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subrow_choose,parent,false);
        return new ChooseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseHolder holder, int position) {
        final Integer floor = floorList.get(position);
//        current_index = -1;
        holder.tvSubRCV.setText(floor+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_index = holder.getAdapterPosition();
                floor_size = floorList.get(holder.getAdapterPosition());
                notifyDataSetChanged();
                ChooseDepotActivity.getInstance().setNewList(floor);
            }
        });
        if (current_index== position){
            holder.bgSubRow.setBackgroundResource(R.drawable.customs_bg_choose_selected);
        }else {
            holder.bgSubRow.setBackgroundResource(0);
        }

    }

    @Override
    public int getItemCount() {
        return floorList.size();
    }

    public class ChooseHolder extends RecyclerView.ViewHolder {
        private TextView tvSubRCV;
        private ConstraintLayout bgSubRow;
        public ChooseHolder(@NonNull View itemView) {
            super(itemView);
            tvSubRCV = (TextView) itemView.findViewById(R.id.tvSubRCV);
            bgSubRow = (ConstraintLayout) itemView.findViewById(R.id.bg_sub_row);

        }
    }
}
