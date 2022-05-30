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
import com.quannm18.quanlykho.Activity_add_new_entry;
import com.quannm18.quanlykho.R;

public class Frag_HDN_Entry extends Fragment {
    FloatingActionButton actionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_hdn_entry,container,false);
        actionButton=view.findViewById(R.id.fla_HDN_entry);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Activity_add_new_entry.class));
            }
        });
        return view;


    }
}
