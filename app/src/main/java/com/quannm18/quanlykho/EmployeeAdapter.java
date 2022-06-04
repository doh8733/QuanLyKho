package com.quannm18.quanlykho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private Context context;
    private List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.name.setText(employee.getEmployeeName());
        holder.gender.setText(employee.getEmployeeGender());
        holder.address.setText(employee.getEmployeeAddress());
        holder.startWorkDate.setText(employee.getEmployeeStartWorkDate());
        holder.avatar.setImageResource(R.drawable.avatar);
    }

    @Override
    public int getItemCount() {
        if (employees == null) return 0;
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, gender, address, startWorkDate;
        public ImageView avatar;

        public ViewHolder(@NonNull View view) {
            super(view);

            name = view.findViewById(R.id.tvName);
            gender = view.findViewById(R.id.tvGender);
            address = view.findViewById(R.id.tvAddress);
            startWorkDate = view.findViewById(R.id.tvStartWorkDate);
            avatar = view.findViewById(R.id.ivAvatar);
        }
    }
}
