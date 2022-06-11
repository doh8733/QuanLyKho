package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.VpFindHoaDonAdapter;

public class FindBillActivity extends AppCompatActivity {
    private TextInputLayout tilFind;
    private BottomNavigationView navadmin;
    private TabLayout tabLayout;
    private ViewPager2 vpViewFind;
    private ImageView imvBackFindbill;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bill);



        imvBackFindbill = (ImageView) findViewById(R.id.imv_backFindbill);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        navadmin = (BottomNavigationView) findViewById(R.id.navadmin);
        vpViewFind = (ViewPager2) findViewById(R.id.vpViewFind);

        tabLayout.addTab(tabLayout.newTab().setText("HDN"));
        tabLayout.addTab(tabLayout.newTab().setText("HDX"));
        imvBackFindbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences sdf = getSharedPreferences("USER_FILE", MODE_PRIVATE);
//                String name = sdf.getString("USER", "");
//                Intent intent;
//                if (name.equalsIgnoreCase("admin")) {
//                    intent = new Intent(FindBillActivity.this, AdminActivity.class);
//                }
//                else {
//                    intent = new Intent(FindBillActivity.this, NhanVienActivity.class);
//                }
//                startActivity(intent);
                onBackPressed();
            }
        });
        fm = getSupportFragmentManager();
        VpFindHoaDonAdapter vpFindHoaDonAdapter = new VpFindHoaDonAdapter(fm,getLifecycle());
        vpViewFind.setAdapter(vpFindHoaDonAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpViewFind.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vpViewFind.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}