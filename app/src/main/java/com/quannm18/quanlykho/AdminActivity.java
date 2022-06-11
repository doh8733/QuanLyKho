package com.quannm18.quanlykho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.VpadminAdapter;

public class AdminActivity extends AppCompatActivity {
    private ViewPager vpViewAdmin;
    private BottomNavigationView navadmin;
    private TextInputLayout tilFind;
    private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tilFind = (TextInputLayout) findViewById(R.id.tilFind);
        vpViewAdmin =  findViewById(R.id.vpViewAdmin);
        navadmin =  findViewById(R.id.navadmin);
        tvName = findViewById(R.id.tvName);

        setUpVpAdmin();
        navadmin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homead:
                        vpViewAdmin.setCurrentItem(1);
                        break;
                    case R.id.billad:
                        vpViewAdmin.setCurrentItem(0);
                        break;
                    case R.id.personnalad:
                        vpViewAdmin.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });



    }
    public void setUpVpAdmin(){
        VpadminAdapter vpadminAdapter = new VpadminAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpViewAdmin.setAdapter(vpadminAdapter);
        vpViewAdmin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 1:
                        navadmin.getMenu().findItem(R.id.homead).setChecked(true);
                        break;
                    case 0:
                        navadmin.getMenu().findItem(R.id.billad).setChecked(true);
                        break;
                    case 2:
                        navadmin.getMenu().findItem(R.id.personnalad).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}