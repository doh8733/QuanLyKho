package com.quannm18.quanlykho.Adapter;

import static com.quannm18.quanlykho.Adapter.SubChooseAdapter.floor_size;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Model.Depot;
import com.quannm18.quanlykho.R;

import java.util.List;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ChooseHolder> {
    private List<Depot> depotList;

    public ChooseAdapter(List<Depot> depotList) {
        this.depotList = depotList;
    }

    @NonNull
    @Override
    public ChooseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_choose,parent,false);
        return new ChooseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseHolder holder, int position) {
        final Depot depot = depotList.get(0);
//        holder.textView.setText(depot.getRow()+" - "+ depot.getFloor()+ " - "+(position+1));


    }


    @Override
    public int getItemCount() {
//        return depotList.get(0).getRow()*depotList.get(0).getFloor()* depotList.get(0).getPosition();
        return (depotList.get(0).getRow()*depotList.get(0).getPosition());
    }

    public class ChooseHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ChooseHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
