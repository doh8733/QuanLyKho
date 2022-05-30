package com.quannm18.quanlykho.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quannm18.quanlykho.Activity_add_new_outlet;
import com.quannm18.quanlykho.R;

public class Frag_HDX_Outlet extends Fragment {
    FloatingActionButton actionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_hdx_outlet,container,false);
        actionButton=view.findViewById(R.id.fla_HDX_outlet);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Activity_add_new_outlet.class));
            }
        });
        return view;


    }
}
