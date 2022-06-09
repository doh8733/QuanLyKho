package com.quannm18.quanlykho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

        tilFind = (TextInputLayout) findViewById(R.id.tilFind);
        vpViewAdmin =  findViewById(R.id.vpViewAdmin);
        navadmin =  findViewById(R.id.navadmin);
        tvName = findViewById(R.id.tvName);
        SharedPreferences sdf = getSharedPreferences("USER_FILE",MODE_PRIVATE);

        tvName.setText(sdf.getString("USER", ""));
        setUpVpAdmin();
        navadmin.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homead:
                        vpViewAdmin.setCurrentItem(0);
                        break;
                    case R.id.billad:
                        vpViewAdmin.setCurrentItem(1);
                        break;
                }
            }
        });
        tilFind.getEditText().setEnabled(true);
        tilFind.getEditText().setTextIsSelectable(true);
        tilFind.getEditText().setFocusable(false);
        tilFind.getEditText().setFocusableInTouchMode(false);
        tilFind.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,FindBillActivity.class);
                startActivity(intent);

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
                    case 0:
                        navadmin.getMenu().findItem(R.id.homead).setChecked(true);
                        break;
                    case 1:
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