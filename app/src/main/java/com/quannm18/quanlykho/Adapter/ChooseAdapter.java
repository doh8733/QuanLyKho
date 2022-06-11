package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.quannm18.quanlykho.API.GetListPosition;
import com.quannm18.quanlykho.ChooseDepotActivity;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.Position;
import com.quannm18.quanlykho.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ChooseHolder> {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final List<Position> depotList;
    private TextInputEditText txt_hdn_maHDN_add;
    private TextInputEditText txt_hdn_name_add;
    private TextInputEditText txt_hdn_productType_add;
    private TextInputEditText txt_hdn_vitri_add;
    private TextInputEditText txt_hdn_quantity_add;
    private TextInputEditText txt_hdn_free_add;
    private TextInputEditText txt_hdn_descriptions_add;
    private AppCompatButton hdnBtnAdd;
    private AppCompatButton hdnBtnCloseAdd;
    private String id;
    private String status;

    private AlertDialog alertDialog;
    public ChooseAdapter(List<Position> depotList) {
        this.depotList = depotList;
    }

    @NonNull
    @Override
    public ChooseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_choose, parent, false);
        return new ChooseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseHolder holder, int position) {
        final Position depot = depotList.get(position);
        holder.textView.setText(depot.getNamePosition());

        ChooseDepotActivity cdp = new ChooseDepotActivity();

        if (depot.getStatus().equalsIgnoreCase("true")) {
            holder.layoutChooseAdapter.setBackgroundColor(Color.parseColor("#28BB8E"));
            holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (depot.getStatus().equalsIgnoreCase("false")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_add_new_entry, null);
                    builder.setView(view1);
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    txt_hdn_maHDN_add = view1.findViewById(R.id.txt_hdn_maHDN_add);
                    txt_hdn_name_add = view1.findViewById(R.id.txt_hdn_name_add);
                    txt_hdn_productType_add = view1.findViewById(R.id.txt_hdn_productType_add);
                    txt_hdn_vitri_add = view1.findViewById(R.id.txt_hdn_pos_add);
                    txt_hdn_quantity_add = view1.findViewById(R.id.txt_hdn_quantity_add);
                    txt_hdn_free_add = view1.findViewById(R.id.txt_hdn_free_add);
                    txt_hdn_descriptions_add = view1.findViewById(R.id.txt_hdn_descriptions_add);
                    dialog.show();
                    txt_hdn_vitri_add.setText("" + depot.getNamePosition());
                    AppCompatButton hdn_btn_add = view1.findViewById(R.id.hdn_btn_add);
                    AppCompatButton hdn_btn_close_add = view1.findViewById(R.id.hdn_btn_close_add);
                    hdn_btn_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (txt_hdn_maHDN_add.getText().toString().isEmpty() && txt_hdn_name_add.getText().toString().isEmpty() &&
                                    txt_hdn_productType_add.getText().toString().isEmpty() && txt_hdn_vitri_add.getText().toString().isEmpty()
                                    && txt_hdn_free_add.getText().toString().isEmpty() && txt_hdn_quantity_add.getText().toString().isEmpty()
                                    && txt_hdn_descriptions_add.getText().toString().isEmpty()) {
                                Toast.makeText(view.getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
                            } else {
                                id = depot.get_id();
                                status = depot.getStatus();
                                InsertDataHDN(view.getContext());
                                CountDownTimer cdt = new CountDownTimer(1000, 600) {
                                    @Override
                                    public void onTick(long l) {
                                        String checkUpdate = updatePosition(view.getContext(), depot.getStatus(), depot.get_id());
                                        if (checkUpdate != depot.getStatus()) {
                                            dialog.dismiss();
//                                        getListPos(view.getContext(), depot.getIdWarehouse());
                                            ChooseDepotActivity.getInstance().setAdapterForRCV();
                                        }
                                    }
                                    @Override
                                    public void onFinish() {

                                    }
                                }.start();

                            }

                        }
                    });


                    hdn_btn_close_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    showDialogCheck(view.getContext());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
//        return depotList.get(0).getRow()*depotList.get(0).getFloor()* depotList.get(0).getPosition();
        return depotList.size();
    }

    public void InsertDataHDN(Context context) {
        HoaDonNhap hoaDonNhap = new HoaDonNhap();
        String datatime = format.format(calendar.getTime());
        hoaDonNhap.setMaHoaDonNhap(txt_hdn_maHDN_add.getText().toString());
        hoaDonNhap.setTenSP(txt_hdn_name_add.getText().toString());
        hoaDonNhap.setLoaiSP(txt_hdn_productType_add.getText().toString());
        hoaDonNhap.setViTri(txt_hdn_vitri_add.getText().toString());
        hoaDonNhap.setDonGia(Integer.parseInt(txt_hdn_free_add.getText().toString()));
        hoaDonNhap.setSoLuong(Integer.parseInt(txt_hdn_quantity_add.getText().toString()));
        hoaDonNhap.setNgayNhap(datatime);
        hoaDonNhap.setMoTa(txt_hdn_descriptions_add.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<HoaDonNhap> call = apiInterface.postHDN(hoaDonNhap);
        call.enqueue(new Callback<HoaDonNhap>() {
            @Override
            public void onResponse(Call<HoaDonNhap> call, Response<HoaDonNhap> response) {
                HoaDonNhap hoaDonNhap = response.body();
                if (hoaDonNhap != null) {
                    Toast.makeText(context, "Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Something is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HoaDonNhap> call, Throwable t) {
            }
        });
    }

    String updatePosition(Context context, String status, String id) {
        String statusTemp;
        if (status.equalsIgnoreCase("true")) {
            statusTemp = "false";
        } else {
            statusTemp = "true";
        }
        GetListPosition.getListPosition.postUpdateStatus(id + "", statusTemp + "", "")
                .enqueue(new Callback<Position>() {
                    @Override
                    public void onResponse(Call<Position> call, Response<Position> response) {
                        final Position position = response.body();
                        if (statusTemp != position.getStatus()) {
                            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Can't update status!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Position> call, Throwable t) {
                        Toast.makeText(context, "Error update pos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
        return statusTemp;
    }
    public class ChooseHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ConstraintLayout layoutChooseAdapter;

        public ChooseHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            layoutChooseAdapter = (ConstraintLayout) itemView.findViewById(R.id.layoutChooseAdapter);

        }
    }
    void showDialogCheck(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage("Which function would you like to choose?");
        builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        }).setPositiveButton("Outlet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();

    }
    int updateBill(Position position){
        int temp = -1;

        return temp;
    }

}
