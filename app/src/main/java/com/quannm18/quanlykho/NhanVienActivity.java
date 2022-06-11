package com.quannm18.quanlykho;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.VpAdapter;

public class NhanVienActivity extends AppCompatActivity {
    private BottomNavigationView navView;
    private ViewPager vpView;
    private TextInputLayout tilFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        navView = findViewById(R.id.nav);
        vpView = findViewById(R.id.vpView);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUpViewPager();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        vpView.setCurrentItem(1);
                        break;
                    case R.id.bill:
                        vpView.setCurrentItem(0);
                        break;
                    case R.id.personnal:
                        vpView.setCurrentItem(2);
                        break;

                }
                return false;
            }
        });

    }

    private void setUpViewPager() {
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpView.setAdapter(vpAdapter);
        vpView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 1:
                        navView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 0:
                        navView.getMenu().findItem(R.id.bill).setChecked(true);
                        break;
                    case 2:
                        navView.getMenu().findItem(R.id.personnal).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}