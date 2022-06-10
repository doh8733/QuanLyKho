package com.quannm18.quanlykho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.EmployeeAdapter;
import com.quannm18.quanlykho.Adapter.GenderAdapter;
import com.quannm18.quanlykho.Interface.EmployeeList;
import com.quannm18.quanlykho.Model.NhanVien;
import com.quannm18.quanlykho.Model.UserAdding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageButton btnSearch;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private List<NhanVien> nhanVienList;
    private AlertDialog alertDialog;
    private AlertDialog subAlert;

    private TextView tvError;
    private TextView textView12;
    private TextInputLayout tilName;
    private TextInputLayout tilUsername;
    private Spinner spGender;
    private TextInputLayout tilAge;
    private TextInputLayout tilAddress;
    private TextInputLayout tilStartWorkDate;
    private AppCompatButton btnCreate;
    private AppCompatButton btnClose;

    private List<String> genderList;
    private GenderAdapter genderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        nhanVienList = new ArrayList<>();
        nhanVienList = getEmployeeList();
        employeeAdapter = new EmployeeAdapter(EmployeeActivity.this,nhanVienList);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeActivity.this));
        recyclerView.setAdapter(employeeAdapter);


        genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");

        genderAdapter = new GenderAdapter(EmployeeActivity.this,R.layout.row_gender,genderList);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeActivity.this);
                View view1 = LayoutInflater.from(EmployeeActivity.this).inflate(R.layout.dialog_add_employee,null,false);
                textView12 = (TextView) view1.findViewById(R.id.textView12);
                tilName = (TextInputLayout) view1.findViewById(R.id.tilName);
                tilUsername = (TextInputLayout) view1.findViewById(R.id.tilUsername);
                spGender = (Spinner) view1.findViewById(R.id.spGender);
                tilAge = (TextInputLayout) view1.findViewById(R.id.tilAge);
                tilAddress = (TextInputLayout) view1.findViewById(R.id.tilAddress);
                tilStartWorkDate = (TextInputLayout) view1.findViewById(R.id.tilStartWorkDate);
                btnCreate = (AppCompatButton) view1.findViewById(R.id.btnCreate);
                btnClose = (AppCompatButton) view1.findViewById(R.id.btnClose);
                tvError = (TextView) view1.findViewById(R.id.tvError);

                builder.setView(view1);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                final Calendar newCalendar = Calendar.getInstance();
                final SimpleDateFormat dateFormat = new SimpleDateFormat();
                final DatePickerDialog  StartTime = new DatePickerDialog(EmployeeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tilStartWorkDate.getEditText().setText(year+"/"+monthOfYear+"/"+dayOfMonth);
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                tilStartWorkDate.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StartTime.show();
                    }
                });

                spGender.setAdapter(genderAdapter);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = tilName.getEditText().getText().toString();
                        String username = tilUsername.getEditText().getText().toString();
                        String gender = spGender.getSelectedItem().toString();
                        String age = tilAge.getEditText().getText().toString();
                        String address = tilAddress.getEditText().getText().toString();
                        String startWorkDate = tilStartWorkDate.getEditText().getText().toString();

                        if (name.trim().isEmpty() ||gender.trim().isEmpty() ||age.trim().isEmpty()||
                                address.trim().isEmpty() || startWorkDate.trim().isEmpty()||username.trim().isEmpty()){
                            tvError.setText("Data must be entered");
                        }else {
                            if (Integer.parseInt(age) < 15 || Integer.parseInt(age) > 100){
                                tvError.setText("Age must be more than 15 and less than 100");
                            }
                            else if (username.trim().length()<6){
                                tvError.setText("Username must be more than 6");
                            }
                            else {
                                tvError.setText("");
                                EmployeeList.employeeList.addNewUser(name,username,"123@abc",gender,age,address,startWorkDate,"User")
                                        .enqueue(new Callback<UserAdding>() {
                                            @Override
                                            public void onResponse(Call<UserAdding> call, Response<UserAdding> response) {
                                                String alert = response.body().getMessage();
                                                Toast.makeText(EmployeeActivity.this, ""+alert, Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                                nhanVienList = getEmployeeList();
                                                employeeAdapter = new EmployeeAdapter(EmployeeActivity.this,nhanVienList);
                                                recyclerView.setAdapter(employeeAdapter);
                                            }

                                            @Override
                                            public void onFailure(Call<UserAdding> call, Throwable t) {
                                                Log.e("Fail",t.getMessage()+"");
                                            }
                                        });
                            }
                        }
                    }
                });
            }
        });
    }

    List<NhanVien> getEmployeeList(){
        List<NhanVien> tempList = new ArrayList<>();
        EmployeeList.employeeList.getNhanVienList().enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                List<NhanVien> list= response.body();
                for (int i = 0; i < list.size(); i++) {
                    tempList.add(list.get(i));

                    employeeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                Toast.makeText(EmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: "+t );
            }
        });
        return tempList;
    }
}