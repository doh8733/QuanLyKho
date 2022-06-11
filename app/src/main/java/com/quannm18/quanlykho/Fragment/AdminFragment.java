package com.quannm18.quanlykho.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Activity_HoaDon;
import com.quannm18.quanlykho.Adapter.CatergoryAdapterSpn;
import com.quannm18.quanlykho.Adapter.DepotAdapter;
import com.quannm18.quanlykho.AdminActivity;
import com.quannm18.quanlykho.EmployeeActivity;
import com.quannm18.quanlykho.FinanceActivity;
import com.quannm18.quanlykho.FindBillActivity;
import com.quannm18.quanlykho.Interface.GetDepot;
import com.quannm18.quanlykho.Interface.PostDepot;
import com.quannm18.quanlykho.Model.Category;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.POST.ResponeDepotPOST;
import com.quannm18.quanlykho.R;
import com.quannm18.quanlykho.TongQuatKhoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminFragment extends Fragment {
    private MaterialButton btnDepot;
    CatergoryAdapterSpn adapterSpn;
    private Spinner spnCategory;
    private Dialog dialog;
    private MaterialButton btnBillAdmin;
    private MaterialButton btnFinance;
    private MaterialButton btnempoloyee;
    private TextInputLayout tilFind;
    private TextView tvhello;
    private TextView tvName;
    private CardView profileImage;
    private MaterialButton imvDepot;
    private AlertDialog alertDialog;

    private TextInputLayout tilNameWarehouse;
    private TextInputLayout tilRow;
    private TextInputLayout tilFloors;
    private TextInputLayout tilPosition;
    private TextInputLayout tilDescription;
    private MaterialButton btnCreate;
    private MaterialButton btnClose;
    private List<KhoHangModel> khoHangModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tilFind = (TextInputLayout) view.findViewById(R.id.tilFind);
        tvhello = (TextView) view.findViewById(R.id.tvhello);
        tvName = (TextView) view.findViewById(R.id.tvName);
        profileImage = (CardView) view.findViewById(R.id.profile_image);

        btnBillAdmin = (MaterialButton) view.findViewById(R.id.btn_bill_admin);
        btnDepot = (MaterialButton) view.findViewById(R.id.btnDepot);
        btnFinance = (MaterialButton) view.findViewById(R.id.btnFinance);
        btnempoloyee = (MaterialButton) view.findViewById(R.id.btnempoloyee);

        SharedPreferences sdf = getActivity().getSharedPreferences("FILE_MODE",MODE_PRIVATE);

        String name = (sdf.getString("NAME",""));

        tvName.setText(name);
        btnDepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Alert");
                alert.setMessage("Waiting a minute...");
                alertDialog = alert.create();
                alertDialog.show();
                CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {
                        getAllDaTa();
                    }

                    @Override
                    public void onFinish() {
                        alertDialog.dismiss();
                        if (khoHangModelList.size()==0){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            LayoutInflater inflater = getActivity().getLayoutInflater();
                            View view1 = inflater.inflate(R.layout.dialog_add_depot, null);
                            builder.setView(view1);
                            alertDialog = builder.create();
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            tilNameWarehouse = (TextInputLayout) view1.findViewById(R.id.tilNameWarehouse);
                            tilRow = (TextInputLayout) view1.findViewById(R.id.tilRow);
                            tilFloors = (TextInputLayout) view1.findViewById(R.id.tilFloors);
                            tilPosition = (TextInputLayout) view1.findViewById(R.id.tilPosition);
                            tilDescription = (TextInputLayout) view1.findViewById(R.id.tilDescription);
                            btnCreate = (MaterialButton) view1.findViewById(R.id.btnCreate);
                            btnClose = (MaterialButton) view1.findViewById(R.id.btnClose);


                            btnCreate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String name = tilNameWarehouse.getEditText().getText().toString();
                                    String row = tilRow.getEditText().getText().toString();
                                    String floors = tilFloors.getEditText().getText().toString();
                                    String position = tilPosition.getEditText().getText().toString();
                                    String description = tilDescription.getEditText().getText().toString();
                                    if (name.trim().isEmpty()) {
                                        tilNameWarehouse.setError("Vui lòng không để trắng thông tin!!!");
                                        return;
                                    } else {
                                        tilNameWarehouse.setErrorEnabled(false);
                                    }
                                    if (row.trim().isEmpty()) {
                                        tilRow.setError("Vui lòng không để trắng thông tin!!!");
                                        return;
                                    }
                                    else if (Integer.parseInt(row.trim())>10) {
                                        tilRow.setError("Vui lòng nhập dưới 10!!!");
                                        return;
                                    }else {
                                        tilRow.setErrorEnabled(false);
                                    }
                                    if (floors.trim().isEmpty()) {
                                        tilFloors.setError("Vui lòng không để trắng thông tin!!!");
                                        return;
                                    }else if (Integer.parseInt(floors.trim())>10) {
                                        tilRow.setError("Vui lòng nhập dưới 10!!!");
                                        return;
                                    } else {
                                        tilFloors.setErrorEnabled(false);
                                    }
                                    if (position.trim().isEmpty()) {
                                        tilPosition.setError("Vui lòng không để trắng thông tin!!!");
                                        return;
                                    }else if (Integer.parseInt(position.trim())>10) {
                                        tilRow.setError("Vui lòng nhập dưới 10!!!");
                                        return;
                                    } else {
                                        tilPosition.setErrorEnabled(false);
                                    }
                                    if (description.trim().isEmpty()) {
                                        tilDescription.setError("Vui lòng không để trắng thông tin!!!");
                                        return;
                                    } else {
                                        tilDescription.setErrorEnabled(false);
                                    }

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("https://agile-server-beco.herokuapp.com/khoHang/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();
                                    PostDepot postDepot = retrofit.create(PostDepot.class);
                                    Call<ResponeDepotPOST> call = postDepot.postData(name, row, floors, position, description);
                                    call.enqueue(new Callback<ResponeDepotPOST>() {
                                        @Override
                                        public void onResponse(Call<ResponeDepotPOST> call, Response<ResponeDepotPOST> response) {
//                                    ResponeDepotPOST responeDepotPOST = response.body();
//                                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
//
//                                    depotList.clear();
//                                    getAllDaTa();
//                                    depotAdapter.notifyDataSetChanged();
                                            Intent i = new Intent(getContext(), TongQuatKhoActivity.class);
                                            startActivity(i);
                                        }

                                        @Override
                                        public void onFailure(Call<ResponeDepotPOST> call, Throwable t) {
                                            Toast.makeText(getContext(), "Loi", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    alertDialog.dismiss();
                                }
                            });
                            btnClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                }
                            });

                            alertDialog.show();
                        }else {
                            Intent i = new Intent(getContext(), TongQuatKhoActivity.class);
                            startActivity(i);
                        }
                    }
                }.start();

            }
        });
        btnFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), FinanceActivity.class);
                startActivity(i);
            }
        });
        btnBillAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Activity_HoaDon.class);
                startActivity(i);
            }
        });
        tilFind.getEditText().setEnabled(true);
        tilFind.getEditText().setTextIsSelectable(true);
        tilFind.getEditText().setFocusable(false);
        tilFind.getEditText().setFocusableInTouchMode(false);
        tilFind.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FindBillActivity.class);
                startActivity(intent);

            }
        });
        btnempoloyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EmployeeActivity.class);
                startActivity(i);
            }
        });

    }

//    public void DialogSelected(){
//
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.dialog_select_depot);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
////        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add1,null);
////        builder.setView(view);
////        Dialog dialog = builder.create();
//
//        spnCategory = (Spinner) dialog.findViewById(R.id.spn_category);
//        spnCategory.setAdapter(adapterSpn);
//        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(),adapterSpn.getItem(i).getName() , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        dialog.show();
//    }
//    private List<Category> getAllListCatergory(){
//        List<Category> list = new ArrayList<>();
//        list.add(new Category("kho hang 1"));
//        list.add(new Category("kho hang 2"));
//        list.add(new Category("kho hang 3"));
//
//        return list;
//    }
public void getAllDaTa() {
        khoHangModelList = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GetDepot getDepot = retrofit.create(GetDepot.class);
    Call<List<KhoHangModel>> call = getDepot.getKho();
    call.enqueue(new Callback<List<KhoHangModel>>() {
        @Override
        public void onResponse(Call<List<KhoHangModel>> call, Response<List<KhoHangModel>> response) {
            khoHangModelList.addAll(response.body());
        }

        @Override
        public void onFailure(Call<List<KhoHangModel>> call, Throwable t) {
            Toast.makeText(getContext(), "Loi", Toast.LENGTH_SHORT).show();
        }
    });
}

}