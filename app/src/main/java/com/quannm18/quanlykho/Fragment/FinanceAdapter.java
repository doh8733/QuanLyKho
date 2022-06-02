package com.quannm18.quanlykho.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FinanceAdapter extends FragmentStateAdapter {
    public FinanceAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return  position==0? new TotalFinanceFragment() : new MonthlyFinanceFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
