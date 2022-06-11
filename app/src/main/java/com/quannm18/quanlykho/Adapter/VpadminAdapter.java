package com.quannm18.quanlykho.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.quannm18.quanlykho.Fragment.AdminFragment;
import com.quannm18.quanlykho.Fragment.Frag_HDN_Entry;
import com.quannm18.quanlykho.Fragment.ProfileFragment;

public class VpadminAdapter extends FragmentStatePagerAdapter {
    public VpadminAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               return  new Frag_HDN_Entry();
           case 1:
               return new AdminFragment();
           case 2:
               return new ProfileFragment();
           default:
               return new AdminFragment();
       }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
