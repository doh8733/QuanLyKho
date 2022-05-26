package com.quannm18.quanlykho;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.quannm18.quanlykho.Adapter.FragHoaDonAdapter;

public class Activity_HoaDon extends AppCompatActivity  {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private Toolbar toolbar;
    private FragHoaDonAdapter fragHoaDonAdapter;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        tabLayout =findViewById(R.id.tabLayout_HDN);
        viewPager2 =findViewById(R.id.viewPager_HD);
        toolbar = findViewById(R.id.toolbar_HDN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable drawable=getResources().getDrawable(R.drawable.ic_left);
        drawable.setColorFilter(Color.parseColor("#28BB8E"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        tabLayout.addTab(tabLayout.newTab().setText("Entry"));
        tabLayout.addTab(tabLayout.newTab().setText("Outlet"));

        fm = getSupportFragmentManager();
        fragHoaDonAdapter = new FragHoaDonAdapter(fm, getLifecycle());
        viewPager2.setAdapter(fragHoaDonAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        return true;

    }

}