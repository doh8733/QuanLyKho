package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.DepotAdapter;
import com.quannm18.quanlykho.Adapter.FindingAdapter;
import com.quannm18.quanlykho.Interface.GetDepot;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindActivity extends AppCompatActivity {
    private TextInputLayout tilFinding;
    private RecyclerView rcvFind;
    private BottomNavigationView navFind;
    private List<KhoHangModel> hangModelList;
    private List<KhoHangModel> depotList;
    private List<KhoHangModel> findList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        tilFinding = (TextInputLayout) findViewById(R.id.tilFinding);
        rcvFind = (RecyclerView) findViewById(R.id.rcvFind);
        navFind = (BottomNavigationView) findViewById(R.id.navFind);

        hangModelList = new ArrayList<>();
        hangModelList = getAllDaTa(FindActivity.this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(FindActivity.this);
        rcvFind.setLayoutManager(layoutManager);
        getAllDaTa();
        tilFinding.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                findList = new ArrayList<>();
                String name = tilFinding.getEditText().getText().toString();
                int pos = -1;
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    for (int j = 0; j < hangModelList.size(); j++) {
                        if (name.equalsIgnoreCase(hangModelList.get(j).getName())){
                            findList.clear();
                            findList.add(hangModelList.get(j));
                            pos = j;
                        }
                        if (pos !=-1){
                            Toast.makeText(FindActivity.this, "Đã tìm thấy " + name, Toast.LENGTH_SHORT).show();
                            LinearLayoutManager layoutManager = new LinearLayoutManager(FindActivity.this);
                            rcvFind.setLayoutManager(layoutManager);
                            DepotAdapter depotAdapter = new DepotAdapter(FindActivity.this,findList);
                            rcvFind.setAdapter(depotAdapter);
                        }
                        else {
                            Toast.makeText(FindActivity.this, "Khong the tim thay" + name, Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                return false;
            }
        });

    }

    public List<KhoHangModel> getAllDaTa(Context context) {
        List<KhoHangModel> khoHangModelList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDepot getDepot = retrofit.create(GetDepot.class);
        Call<List<KhoHangModel>> call = getDepot.getKho();
        call.enqueue(new Callback<List<KhoHangModel>>() {
            @Override
            public void onResponse(Call<List<KhoHangModel>> call, Response<List<KhoHangModel>> response) {
                List<KhoHangModel> lisdepot = response.body();
                for (KhoHangModel khoHangModel : lisdepot) {
                    khoHangModelList.add(khoHangModel);
                }
            }

            @Override
            public void onFailure(Call<List<KhoHangModel>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "Loi", Toast.LENGTH_SHORT).show();
            }
        });
        return khoHangModelList;

    }

    public void getAllDaTa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDepot getDepot = retrofit.create(GetDepot.class);
        Call<List<KhoHangModel>> call = getDepot.getKho();
        call.enqueue(new Callback<List<KhoHangModel>>() {
            @Override
            public void onResponse(Call<List<KhoHangModel>> call, Response<List<KhoHangModel>> response) {
                depotList = response.body();
                FindingAdapter findingAdapter = new FindingAdapter(depotList);
                rcvFind.setAdapter(findingAdapter);
            }

            @Override
            public void onFailure(Call<List<KhoHangModel>> call, Throwable t) {
                Toast.makeText(FindActivity.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}