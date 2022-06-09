package com.quannm18.quanlykho.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quannm18.quanlykho.Fragment.AdminFragment;
import com.quannm18.quanlykho.Fragment.FindHDNFragment;
import com.quannm18.quanlykho.Fragment.FindHDXFragment;
import com.quannm18.quanlykho.Fragment.Frag_HDN_Entry;
import com.quannm18.quanlykho.Fragment.Frag_HDX_Outlet;

public class VpFindHoaDonAdapter extends FragmentStateAdapter {


    public VpFindHoaDonAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FindHDNFragment();
        }
        return new FindHDXFragment();

    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
