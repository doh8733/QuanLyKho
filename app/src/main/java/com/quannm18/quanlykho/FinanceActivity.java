package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.quannm18.quanlykho.Fragment.FinanceAdapter;

public class FinanceActivity extends AppCompatActivity {
    private Toolbar tbFinance;
    private TabLayout tabLayout;
    private ViewPager2 vpFinance;
    private FragmentManager fm;
    private FinanceAdapter financeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tbFinance = (Toolbar) findViewById(R.id.tbFinance);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        vpFinance = (ViewPager2) findViewById(R.id.vpFinance);
        fm = getSupportFragmentManager();

        financeAdapter = new FinanceAdapter(fm, getLifecycle());

        vpFinance.setAdapter(financeAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpFinance.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vpFinance.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}