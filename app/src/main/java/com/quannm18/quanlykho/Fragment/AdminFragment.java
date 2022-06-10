package com.quannm18.quanlykho.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.quannm18.quanlykho.Activity_HoaDon;
import com.quannm18.quanlykho.Adapter.CatergoryAdapterSpn;
import com.quannm18.quanlykho.Model.Category;
import com.quannm18.quanlykho.R;
import com.quannm18.quanlykho.TongQuatKhoActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {
    private MaterialButton btnDepot;
    CatergoryAdapterSpn adapterSpn;
    private Spinner spnCategory;
    private Dialog dialog;
    private MaterialButton btnBillAdmin;
    private MaterialButton btnFinance;
    private MaterialButton btnempoloyee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBillAdmin = (MaterialButton) view.findViewById(R.id.btn_bill_admin);
        btnDepot = (MaterialButton) view.findViewById(R.id.imvDepot);
        btnFinance = (MaterialButton) view.findViewById(R.id.btnFinance);
        btnempoloyee = (MaterialButton) view.findViewById(R.id.btnempoloyee);

        btnDepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), TongQuatKhoActivity.class);
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

}