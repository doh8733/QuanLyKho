package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Interface.Api;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Interface.EmployeeList;
import com.quannm18.quanlykho.Model.NhanVien;
import com.quannm18.quanlykho.POST.LoginRespone;
import com.quannm18.quanlykho.POST.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageView7;
    private TextView textView8;
    private TextInputLayout textInputLayout;
    private TextInputEditText edtUsername;
    private TextInputLayout textInputLayout2;
    private TextInputEditText edtPassword;
    private AppCompatCheckBox checkBox;
    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;
    private TextView textView9;
    private TextView forget;
    private AppCompatButton btnSignIn;
    private List<NhanVien> nhanVienList;
    private TextView tv;
    private NhanVien NhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView7 = (ImageView) findViewById(R.id.imageView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        tilUsername = (TextInputLayout) findViewById(R.id.tilUsername);
        edtUsername = (TextInputEditText) findViewById(R.id.edtUsername);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        edtPassword = (TextInputEditText) findViewById(R.id.edtPassword);
        checkBox = (AppCompatCheckBox) findViewById(R.id.checkBox);
        textView9 = (TextView) findViewById(R.id.textView9);
        btnSignIn = (AppCompatButton) findViewById(R.id.btnSignIn);
        tv = (TextView) findViewById(R.id.tv);

        getListUser();
        nhanVienList = new ArrayList<>();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = tilUsername.getEditText().getText().toString();
                String password = tilPassword.getEditText().getText().toString();
                if (userName.length() == 0){
                    tilUsername.setErrorEnabled(true);
                    tilUsername.setError("Không được để trống");
                    tilPassword.setEnabled(false);
                }else if (password.length() == 0){
                    tilPassword.setErrorEnabled(true);
                    tilPassword.setError("Không được để trống");
                    tilUsername.setEnabled(false);
                }
                else {
                    Api.api.postLogin(userName,password).enqueue(new Callback<LoginRespone>() {
                        @Override
                        public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                            if (response.body().getNhanVien()!=null){
                                Intent intent;
                                LoginRespone loginRespone = response.body();
                                if (loginRespone.getNhanVien().getRole().equalsIgnoreCase("admin")){
                                    intent = new Intent(LoginActivity.this, AdminActivity.class);
                                    SharedPreferences sharedPreferences = getSharedPreferences("FILE_MODE",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.putString("ID",loginRespone.getNhanVien().get_id());
                                    editor.putString("PASSWORD",tilPassword.getEditText().getText()+"");
                                    editor.putString("ROLE",loginRespone.getNhanVien().getRole());
                                    editor.putString("NAME",loginRespone.getNhanVien().getFullName());
                                    editor.putString("GENDER",loginRespone.getNhanVien().getGender());
                                    editor.putString("AGE",loginRespone.getNhanVien().getAge());
                                    editor.putString("ADDRESS",loginRespone.getNhanVien().getAddress());
                                    editor.commit();
                                }else{
                                    intent = new Intent(LoginActivity.this, NhanVienActivity.class);
                                    SharedPreferences sharedPreferences = getSharedPreferences("FILE_MODE",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.putString("ID",loginRespone.getNhanVien().get_id());
                                    editor.putString("PASSWORD",tilPassword.getEditText().getText()+"");
                                    editor.putString("ROLE",loginRespone.getNhanVien().getRole());
                                    editor.putString("NAME",loginRespone.getNhanVien().getFullName());
                                    editor.putString("GENDER",loginRespone.getNhanVien().getGender());
                                    editor.putString("AGE",loginRespone.getNhanVien().getAge());
                                    editor.putString("ADDRESS",loginRespone.getNhanVien().getAddress());
                                    editor.commit();
                                    //hhhg
                                }
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "Username or password is not correct", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginRespone> call, Throwable t) {

                        }
                    });
                }


            }
        });

    }


    private void getListUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeList apiInterface = retrofit.create(EmployeeList.class);
        Call<List<NhanVien>> call = apiInterface.getNhanVienList();
        call.enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    nhanVienList = response.body();
                    Log.e("User", nhanVienList.size() + "");

                }
            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    List<NhanVien> getEmployeeList() {
        List<NhanVien> tempList = new ArrayList<>();
        EmployeeList.employeeList.getNhanVienList().enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                List<NhanVien> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    tempList.add(list.get(i));
                }

            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t);
            }
        });
        return tempList;
    }

}