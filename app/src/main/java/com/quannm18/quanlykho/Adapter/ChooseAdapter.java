package com.quannm18.quanlykho.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.quannm18.quanlykho.API.GetListPosition;
import com.quannm18.quanlykho.ChooseDepotActivity;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.MainActivity;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.Model.Position;
import com.quannm18.quanlykho.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private List<Position> depotList;
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
    private boolean statusInsertHDN;
    private boolean statusUpdateStatusOP;
    private boolean statusUpdateUI;

    private AlertDialog alertDialog;

    private TextInputEditText hdxMaHDXAdd;
    private TextInputEditText hdxNgayXuatAdd;
    private TextInputEditText hdxThanhtienAdd;
    private TextInputEditText hdxTrangthaiAdd;
    private TextInputEditText hdxDescriptionsAdd;
    private AppCompatButton hdxBtnAdd;
    private AppCompatButton hdxBtnClose;

    private HoaDonNhap detailHDN;


    private CardView cvHdnCT;
    private TextView hdnTxtMaHDNCT;
    private TextView hdnTxtProductCT;
    private TextView hdnTxtProductTypeCT;
    private TextView hdnTxtVitriCT;
    private TextView hdnTxtQuantityCT;
    private TextView hdnTxtNgayNhapCT;
    private TextView hdnTxtFreeCT;
    private TextView hdnTxtDescriptionsCT;
    private AppCompatButton hdnBtnCloseCT;

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
        if (!depot.getStatus().equalsIgnoreCase("false")) {
            holder.layoutChooseAdapter.setBackgroundColor(Color.parseColor("#28BB8E"));
            holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_bill_entry_chitiet, null);
                    builder.setView(view1);
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    cvHdnCT = (CardView) view1.findViewById(R.id.cv_hdn_CT);
                    hdnTxtMaHDNCT = (TextView) view1.findViewById(R.id.hdn_txt_maHDN_CT);
                    hdnTxtProductCT = (TextView) view1.findViewById(R.id.hdn_txt_product_CT);
                    hdnTxtProductTypeCT = (TextView) view1.findViewById(R.id.hdn_txt_product_type_CT);
                    hdnTxtVitriCT = (TextView) view1.findViewById(R.id.hdn_txt_vitri_CT);
                    hdnTxtQuantityCT = (TextView) view1.findViewById(R.id.hdn_txt_quantity_CT);
                    hdnTxtNgayNhapCT = (TextView) view1.findViewById(R.id.hdn_txt_ngayNhap_CT);
                    hdnTxtFreeCT = (TextView) view1.findViewById(R.id.hdn_txt_free_CT);
                    hdnTxtDescriptionsCT = (TextView) view1.findViewById(R.id.hdn_txt_descriptions_CT);
                    hdnBtnCloseCT = (AppCompatButton) view1.findViewById(R.id.hdn_btn_close_CT);
                    HoaDonNhap hoaDonNhap = getBillFromPos(depot, view.getContext(),holder.getAdapterPosition()-1);


                    hdnBtnCloseCT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    return false;
                }
            });
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                View view1 = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.activity_add_new_entry, null);
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

                if (depot.getStatus().equalsIgnoreCase("false")) {
                    dialog.show();
                    txt_hdn_vitri_add.setText("" + depot.getNamePosition());
                    AppCompatButton hdn_btn_add = view1.findViewById(R.id.hdn_btn_add);
                    AppCompatButton hdn_btn_close_add = view1.findViewById(R.id.hdn_btn_close_add);
                    hdn_btn_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String maHDN = txt_hdn_maHDN_add.getText().toString();
                            String tenAdd = txt_hdn_name_add.getText().toString();
                            String productType = txt_hdn_productType_add.getText().toString();
                            String vitriHDN = txt_hdn_vitri_add.getText().toString();
                            String feeHDN = txt_hdn_free_add.getText().toString();
                            String quantityHDN = txt_hdn_quantity_add.getText().toString();
                            String desHDN = txt_hdn_descriptions_add.getText().toString();
                            if (maHDN.isEmpty() || tenAdd.isEmpty() ||
                                    productType.isEmpty() || vitriHDN.isEmpty()
                                    || feeHDN.isEmpty() || quantityHDN.isEmpty()
                                    || desHDN.isEmpty()) {
                                Toast.makeText(view.getContext(), "Fill data please!", Toast.LENGTH_SHORT).show();
                            } else {
                                insertDataHDN(view.getContext(),depot,dialog);
                            }

                        }
                    });


                    hdn_btn_close_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                } else {
                    AlertDialog.Builder askDialog = new AlertDialog.Builder(view.getContext());
                    askDialog.setTitle("Notification");
                    askDialog.setMessage("Do you want to export products?");
                    askDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                            showDialogExport(depotList.get(holder.getAdapterPosition()), view.getContext(), new HoaDonXuat());
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog = askDialog.create();
                    alertDialog.show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
//        return depotList.get(0).getRow()*depotList.get(0).getFloor()* depotList.get(0).getPosition();
        return depotList.size();
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


    public boolean insertDataHDN(Context context, Position pos,AlertDialog dialog){

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
                if (response.isSuccessful()){
                    if (hoaDonNhap != null) {
                        Toast.makeText(context, "Thêm xong hđ nhập!", Toast.LENGTH_SHORT).show();
                        statusInsertHDN = true;

                        if (statusInsertHDN){
                           updatePosition(pos,context,dialog);

                        }else {
                            Toast.makeText(context, "Lỗi insert", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Something is not correct!", Toast.LENGTH_SHORT).show();
                        statusInsertHDN = false;
                    }
                }
            }

            @Override
            public void onFailure(Call<HoaDonNhap> call, Throwable t) {
                statusInsertHDN = false;
            }
        });
        return statusInsertHDN;
    }

    public boolean updatePosition(Position pos, Context context,AlertDialog dialog){
        statusUpdateStatusOP = false;
//        if (pos.getStatus().equals("true")){
//            pos.setStatus("false");
//        }
//        if (pos.getStatus().equals("false")){
//            pos.setStatus("true");
//        }
        GetListPosition.getListPosition.postUpdateStatus(pos.get_id(), pos.getStatus().equals("false")?"true":"false", pos.getNamePosition())
                .enqueue(new Callback<Position>() {
                    @Override
                    public void onResponse(Call<Position> call, Response<Position> response) {
                        final Position position = response.body();
                        if (position!=null){
                            statusUpdateStatusOP = true;
                            if (statusUpdateStatusOP){
//                                updateUI(pos,context,dialog);
                                Intent intent = ChooseDepotActivity.getInstance().getIntent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                                ChooseDepotActivity.getInstance().finishAc();
                                Toast.makeText(context, "Update status success", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi update status", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            statusUpdateStatusOP = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<Position> call, Throwable t) {
                        statusUpdateStatusOP = false;
                    }

                });
        return statusUpdateStatusOP;
    }

    public boolean updateUI(Position pos, Context context, AlertDialog dialog){
        statusUpdateUI = false;
        if (pos.getStatus()=="true"){
            pos.setStatus("false");
        }
        if (pos.getStatus()!="false"){
            pos.setStatus("true");
        }

        GetListPosition.getListPosition.postGetListPosition(pos.getIdWarehouse())
                .enqueue(new Callback<List<Position>>() {
                    @Override
                    public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                        final List<Position> positions = response.body();
                        if (positions!=null){
                            setDepotList(positions);
                            statusUpdateUI = true;
                            if (statusUpdateUI){
                                Toast.makeText(context, "Đã update giao diện", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(context, "Lỗi update giao diện", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            statusUpdateUI = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Position>> call, Throwable t) {
                        statusUpdateUI = false;
                    }
                });
        return statusUpdateUI;
    }

    public void setDepotList(List<Position> positions){
        depotList = new ArrayList<>();
        depotList.addAll(positions);
        notifyItemRangeChanged(0,depotList.size());
        notifyDataSetChanged();
    }

    public void insertHDX(Position pos, Context context, AlertDialog dialog){
        HoaDonXuat hoaDonXuat = new HoaDonXuat();
        String datatime = format.format(calendar.getTime());

        hoaDonXuat.setMaHDX(hdxMaHDXAdd.getText().toString());
        hoaDonXuat.setNgayXuat(datatime);
        hoaDonXuat.setThanhTien(Integer.parseInt(hdxThanhtienAdd.getText().toString()));
        hoaDonXuat.setTrangThai(hdxTrangthaiAdd.getText().toString());
        hoaDonXuat.setMoTa(hdxDescriptionsAdd.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<HoaDonXuat> callHDX = apiInterface.postHDX(hoaDonXuat);
        callHDX.enqueue(new Callback<HoaDonXuat>() {
            @Override
            public void onResponse(Call<HoaDonXuat> call, Response<HoaDonXuat> response) {
                updatePosition(pos, context, dialog);
            }

            @Override
            public void onFailure(Call<HoaDonXuat> call, Throwable t) {
                Toast.makeText(context, "Loi api HDX add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void showDialogExport(Position pos, Context context, HoaDonXuat hoaDonXuat){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view1 = LayoutInflater.from(context).inflate(R.layout.activity_add_new_outlet, null);
        builder.setView(view1);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        String datatime = format.format(calendar.getTime());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        hdxMaHDXAdd = view1.findViewById(R.id.hdx_maHDX_add);
        hdxNgayXuatAdd = view1.findViewById(R.id.hdx_ngayXuat_add);
        hdxThanhtienAdd = view1.findViewById(R.id.hdx_thanhtien_add);
        hdxTrangthaiAdd = view1.findViewById(R.id.hdx_trangthai_add);
        hdxDescriptionsAdd = view1.findViewById(R.id.hdx_descriptions_add);
        AppCompatButton hdx_btn_add = view1.findViewById(R.id.hdx_btn_add);
        AppCompatButton hdx_btn_close = view1.findViewById(R.id.hdx_btn_close);

        hdxMaHDXAdd.setText(pos.get_id());
        hdxMaHDXAdd.setEnabled(false);
        hdxNgayXuatAdd.setText(datatime);
        hdxNgayXuatAdd.setEnabled(false);
        hdx_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHDX(pos, context, dialog);
            }
        });
        hdx_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    HoaDonNhap getBillFromPos(Position position, Context context,int pos){
        detailHDN = new HoaDonNhap();
        ApiInterface.ApiInterface.getHDNFromPosition(position.getNamePosition()).enqueue(new Callback<List<HoaDonNhap>>() {
            @Override
            public void onResponse(Call<List<HoaDonNhap>> call, Response<List<HoaDonNhap>> response) {
                if (response.isSuccessful()){
                    final List<HoaDonNhap> hoaDonNhap= response.body();
                    detailHDN = hoaDonNhap.get(hoaDonNhap.size()-1);
                    hdnTxtMaHDNCT.setText(detailHDN.getMaHoaDonNhap()+"");
                    hdnTxtProductCT.setText(detailHDN.getTenSP()+"");
                    hdnTxtProductTypeCT.setText(detailHDN.getLoaiSP()+"");
                    hdnTxtVitriCT.setText(detailHDN.getViTri()+"");
                    hdnTxtNgayNhapCT.setText(detailHDN.getNgayNhap()+"");
                    hdnTxtQuantityCT.setText(detailHDN.getSoLuong()+"");
                    hdnTxtFreeCT.setText(detailHDN.getDonGia()+"");
                    hdnTxtDescriptionsCT.setText(detailHDN.getMoTa()+"");
                }
            }

            @Override
            public void onFailure(Call<List<HoaDonNhap>> call, Throwable t) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        return detailHDN;
    }
}
