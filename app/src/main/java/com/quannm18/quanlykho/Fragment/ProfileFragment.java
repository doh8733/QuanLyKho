package com.quannm18.quanlykho.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.quannm18.quanlykho.ChangePasswordActivity;
import com.quannm18.quanlykho.LoginActivity;
import com.quannm18.quanlykho.ProfileActivity;
import com.quannm18.quanlykho.R;
import com.quannm18.quanlykho.TestAdminActivity;
import com.quannm18.quanlykho.testuserActivity;

public class ProfileFragment extends Fragment {
    private ImageButton btnBack;
    private TextView textView10;
    private ImageView imageView11;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;
    private AppCompatButton btnChangePassword;
    private AppCompatButton btnLogout;
    private TextView tvName;
    private TextView tvGender;
    private TextView tvAge;
    private TextView tvAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        btnBack = (ImageButton) view.findViewById(R.id.btnBack);
        textView10 = (TextView) view.findViewById(R.id.textView10);
        imageView11 = (ImageView) view.findViewById(R.id.imageView11);
        textView11 = (TextView) view.findViewById(R.id.textView11);
        textView12 = (TextView) view.findViewById(R.id.textView12);
        textView13 = (TextView) view.findViewById(R.id.textView13);
        textView14 = (TextView) view.findViewById(R.id.textView14);
        btnChangePassword = (AppCompatButton) view.findViewById(R.id.btnChangePassword);
        btnLogout = (AppCompatButton) view.findViewById(R.id.btnLogout);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvGender = (TextView) view.findViewById(R.id.tvGender);
        tvAge = (TextView) view.findViewById(R.id.tvAge);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);

        SharedPreferences sdf = getActivity().getSharedPreferences("FILE_MODE",MODE_PRIVATE);

        String name =sdf.getString("NAME","");
        String gender =sdf.getString("GENDER","");
        String age =sdf.getString("AGE","");
        String address =sdf.getString("ADDRESS","");

        tvName.setText(name);
        tvGender.setText(gender);
        tvAge.setText(age);
        tvAddress.setText(address);

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String role =  sdf.getString("ROLE","");
//                if (role.equalsIgnoreCase("admin")){
//                    Intent intent = new Intent(getContext(), TestAdminActivity.class);
//                    startActivity(intent);
//                }else {
//                    Intent intent = new Intent(getContext(), testuserActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}