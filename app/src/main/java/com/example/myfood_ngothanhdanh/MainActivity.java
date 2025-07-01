package com.example.myfood_ngothanhdanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.MyViewPagerAdapter_NTDanh;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setUserInputEnabled(false);

        MyViewPagerAdapter_NTDanh adapter = new MyViewPagerAdapter_NTDanh(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    if (position == 0) {tab.setText("Home"); tab.setIcon(R.drawable.icon_home_selector);}
                    if (position == 1) {tab.setText("Location"); tab.setIcon(R.drawable.icon_location_selector);}
                    if (position == 2) {tab.setText("My Cart"); tab.setIcon(R.drawable.icon_cart_selector);}
                    if (position == 3) {tab.setText("Me"); tab.setIcon(R.drawable.icon_user_selector);}
                }).attach();

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("fragment_to_open_ntdanh")){
            Log.d("JUMPHERE", "1");
            String target = intent.getStringExtra("fragment_to_open_ntdanh");
            Log.d("JUMPHERE", target);
            if ("cart".equals(target)) {
                viewPager2.setCurrentItem(2);
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}