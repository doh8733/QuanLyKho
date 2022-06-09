package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

public class FindingAdapter extends RecyclerView.Adapter<FindingAdapter.FindingHolder>{
    List<KhoHangModel> listKho = new ArrayList<>();

    public FindingAdapter(List<KhoHangModel> listKho) {
        this.listKho = listKho;
    }

    @NonNull
    @Override
    public FindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tong_quat_kho, parent, false);
        FindingHolder findingHolder = new FindingHolder(view);
        return findingHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindingHolder holder, int position) {
        final KhoHangModel depot = listKho.get(position);
        Context context = holder.itemView.getContext();
        holder.tvName.setText(String.valueOf(depot.getName()));
        holder.tvRow.setText(String.valueOf(depot.getRow()));
        holder.tvFloors.setText(String.valueOf(depot.getFloors()));
        holder.tvPosition.setText(depot.getPosition());
    }

    @Override
    public int getItemCount() {
        return listKho.size();
    }

    public class FindingHolder extends RecyclerView.ViewHolder {
        private TextView tvRow;
        private TextView tvFloors;
        private TextView tvPosition;
        private TextView tvUsed;
        private TextView tvAvailable;
        private TextView tvProductTypes;
        private TextView tvBroken;
        private TextView tvFinished;
        private TextView tvName;
        private CardView layout;
        public FindingHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_item);
            tvName = itemView.findViewById(R.id.tvName);
            tvRow = (TextView) itemView.findViewById(R.id.tvRow);
            tvFloors = (TextView) itemView.findViewById(R.id.tvFloors);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);

        }
    }
}
