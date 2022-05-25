package com.quannm18.quanlykho.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.quannm18.quanlykho.Fragment.AdminFragment;

public class VpadminAdapter extends FragmentStatePagerAdapter {
    public VpadminAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               return  new AdminFragment();
           default:
               return new AdminFragment();


       }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
