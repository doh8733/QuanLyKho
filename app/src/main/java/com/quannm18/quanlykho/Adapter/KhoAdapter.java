package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Model.Kho;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

public class KhoAdapter extends RecyclerView.Adapter<KhoAdapter.KhoViewholder> {
    Context context;
    List<Kho> listKho = new ArrayList<>();

    public KhoAdapter(Context context, List<Kho> listKho) {
        this.listKho = listKho;
    }

    public KhoAdapter(List<Kho> khoList) {
    }

    @NonNull
    @Override
    public KhoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tong_quat_kho,parent,false);
        KhoViewholder khoViewholder = new KhoViewholder(view);

        return khoViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull KhoViewholder holder, int position) {
        final  Kho kho = listKho.get(position);
        context = holder.itemView.getContext();
        holder.tvRow.setText(String.valueOf(kho.getRow()));
        holder.tvFloors.setText(String.valueOf(kho.getFloors()));
        holder.tvPosition.setText(String.valueOf(kho.getPosition()));
        holder.tvUsed.setText(String.valueOf(kho.getUsed()));
        holder.tvAvailable.setText(String.valueOf(kho.getAvailable()));
        holder.tvProductTypes.setText(String.valueOf(kho.getProduct()));
        holder.tvBroken.setText(String.valueOf(kho.getBroken()));
        holder.tvFinished.setText(String.valueOf(kho.getFinished()));

    }

    @Override
    public int getItemCount() {
        return listKho.size();
    }

    public class KhoViewholder extends RecyclerView.ViewHolder {
        private TextView tvRow;
        private TextView tvFloors;
        private TextView tvPosition;
        private TextView tvUsed;
        private TextView tvAvailable;
        private TextView tvProductTypes;
        private TextView tvBroken;
        private TextView tvFinished;
        public KhoViewholder(@NonNull View itemView) {

            super(itemView);
            tvRow = (TextView) itemView.findViewById(R.id.tvRow);
            tvFloors = (TextView) itemView.findViewById(R.id.tvFloors);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
            tvUsed = (TextView) itemView.findViewById(R.id.tvUsed);
            tvAvailable = (TextView) itemView.findViewById(R.id.tvAvailable);
            tvProductTypes = (TextView) itemView.findViewById(R.id.tvProduct);
            tvBroken = (TextView) itemView.findViewById(R.id.tvBroke);
            tvFinished = (TextView) itemView.findViewById(R.id.tvFinished);


        }
    }
}
