package com.quannm18.quanlykho.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Activity_HoaDon;
import com.quannm18.quanlykho.FindBillActivity;
import com.quannm18.quanlykho.NhanVienActivity;
import com.quannm18.quanlykho.R;
import com.quannm18.quanlykho.TongQuatKhoActivity;

public class HomeFragment extends Fragment {
    private MaterialButton btnDepot;
    private MaterialButton btnBill;
    private CardView profileImage;
    private TextInputLayout tilFind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tilFind = (TextInputLayout) view.findViewById(R.id.tilFind);
        profileImage = (CardView) view.findViewById(R.id.profile_image);
        btnBill = (MaterialButton) view.findViewById(R.id.btn_bill);
        btnDepot = (MaterialButton) view.findViewById(R.id.btn_depot);

        btnDepot = (MaterialButton) view.findViewById(R.id.btn_depot);
        btnBill = (MaterialButton) view.findViewById(R.id.btn_bill);
        btnDepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TongQuatKhoActivity.class);
                startActivity(intent);
            }
        });
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_HoaDon.class);
                startActivity(intent);
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
    }
}