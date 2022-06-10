package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Model.NhanVien;
import com.quannm18.quanlykho.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private Context context;
    private List<NhanVien> employees;

    public EmployeeAdapter(Context context, List<NhanVien> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_employee_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NhanVien employee = employees.get(position);
        holder.tvName.setText("Name: \n"+employee.getFullName());
        holder.tvGender.setText("Gender: "+employee.getGender());
        holder.tvAddress.setText("Address: \n"+employee.getAddress());
        holder.tvStartWorkDate.setText("Start Work Date: \n"+employee.getStartWorkDate());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvGender;
        private TextView tvAddress;
        private TextView tvStartWorkDate;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvGender = (TextView) view.findViewById(R.id.tvGender);
            tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            tvStartWorkDate = (TextView) view.findViewById(R.id.tvStartWorkDate);

        }
    }
}
