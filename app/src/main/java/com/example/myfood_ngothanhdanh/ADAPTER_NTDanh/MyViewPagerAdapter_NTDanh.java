package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh.Home_NTDanh;
import com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh.Location_NTDanh;
import com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh.Me_NTDanh;
import com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh.MyCart_NTDanh;

public class MyViewPagerAdapter_NTDanh extends FragmentStateAdapter {
    public MyViewPagerAdapter_NTDanh(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return new Home_NTDanh();
            case 1:
                return new Location_NTDanh();
            case 2:
                return new MyCart_NTDanh();
            case 3:
                return new Me_NTDanh();
            default:
                return new Home_NTDanh();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Số lượng tab
    }
}
