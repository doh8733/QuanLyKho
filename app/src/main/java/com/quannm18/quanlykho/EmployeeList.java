package com.quannm18.quanlykho;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends AppCompatActivity {
    private RecyclerView employeeList;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> employees;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        employeeList = findViewById(R.id.recyclerView);
        employeeList.setHasFixedSize(true);
        employees = new ArrayList<>();

        //Test
        for (int i = 0; i<20; i++) {
            Employee employee = new Employee();
            employee.setEmployeeName("Name: " + i);
            employee.setEmployeeGender("Gender: " + i);
            employee.setEmployeeAddress("Address: " + i);
            employee.setEmployeeStartWorkDate("Start Work Date: " + i);
            employees.add(employee);
        }

        linearLayoutManager = new LinearLayoutManager(this);
        employeeAdapter = new EmployeeAdapter(this, employees);
        employeeList.setAdapter(employeeAdapter);
        employeeList.setLayoutManager(linearLayoutManager);
    }
}