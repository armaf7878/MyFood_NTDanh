package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_TodayMenu_NTDanh;
import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_categories_NTDanh;
import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.cateDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.restaurantDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cate_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;


public class Home_NTDanh extends Fragment {
    private RecyclerView RecyclerRes, recycler_todayMenu_NTDanh, recycler_categories_NTDanh;
    private adapter_restaurant_NTDanh adapterRestaurantNtDanh;
    private List<restaurant_NTDanh> restaurant_ntDanhList;
    private adapter_TodayMenu_NTDanh adapterTodayMenuNtDanh;
    private adapter_categories_NTDanh adapterCategoriesNtDanh;

    public Home_NTDanh() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerRes = view.findViewById(R.id.RecyclerRes);
        loadRes_NTDanh(RecyclerRes);

        recycler_todayMenu_NTDanh = view.findViewById(R.id.recycler_todayMenu_NTDanh);
        loadTodayMenu(recycler_todayMenu_NTDanh);

        recycler_categories_NTDanh = view.findViewById(R.id.recycler_categories_NTDanh);
        loadCate(recycler_categories_NTDanh);

        return view;
    }

    private void loadRes_NTDanh( RecyclerView RecyclerRes){
        restaurantDAO_NTDanh restaurantDAO_ntDanh = new restaurantDAO_NTDanh(getContext());

//        restaurant_NTDanh restaurant_ntDanh = new restaurant_NTDanh();
//        restaurant_ntDanh.setRes_name("Lẩu cua đồng quê");
//        restaurant_ntDanh.setRes_phone("0353991094");
//        restaurant_ntDanh.setRes_address("175 Đào Duy Anh, Phường 9, Quận Phú Nhuận, Hồ Chí Minh");
//        restaurant_ntDanh.setRes_img(R.drawable.store_lau);
//        restaurantDAO_ntDanh.insert_NTDanh(restaurant_ntDanh);
//
//        restaurant_NTDanh restaurant_ntDanh1 = new restaurant_NTDanh();
//        restaurant_ntDanh1.setRes_name("Bánh mì Dân Tổ");
//        restaurant_ntDanh1.setRes_phone("0353991094");
//        restaurant_ntDanh1.setRes_address("175 Đào Duy Anh, Phường 9, Quận Phú Nhuận, Hồ Chí Minh");
//        restaurant_ntDanh1.setRes_img(R.drawable.store_banhmi);
//        restaurantDAO_ntDanh.insert_NTDanh(restaurant_ntDanh1);
//
//        restaurant_NTDanh restaurant_ntDanh2 = new restaurant_NTDanh();
//        restaurant_ntDanh2.setRes_name("Phở Lý Quốc Sư");
//        restaurant_ntDanh2.setRes_phone("093323327");
//        restaurant_ntDanh2.setRes_address("14 Nguyễn Oanh, Phường 17, Quận Gò Vấp, Hồ Chí Minh");
//        restaurant_ntDanh2.setRes_img(R.drawable.store_pho);
//        restaurantDAO_ntDanh.insert_NTDanh(restaurant_ntDanh2);

        restaurant_ntDanhList = restaurantDAO_ntDanh.getAll();
        RecyclerRes.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterRestaurantNtDanh = new adapter_restaurant_NTDanh(restaurant_ntDanhList);
        RecyclerRes.setAdapter(adapterRestaurantNtDanh);
    }

    private void loadTodayMenu(RecyclerView recycler_todayMenu_NTDanh){
        foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(getContext());

//        food_NTDanh food_ntDanh = new food_NTDanh();
//        food_ntDanh.setFood_name("Phở Đặc Biệt");
//        food_ntDanh.setFood_des("Mô tả ở đây là mô tả");
//        food_ntDanh.setFood_img(R.drawable.food_phodacbiet);
//        food_ntDanh.setFood_price(45000.0);
//        food_ntDanh.setRes_id(3);
//        food_ntDanh.setCate_id(3);
//        foodDAO_ntDanh.insertFood_NTDanh(food_ntDanh);
//
//        food_NTDanh food_ntDanh1 = new food_NTDanh();
//        food_ntDanh1.setFood_name("Bánh Donut");
//        food_ntDanh1.setFood_des("Mô tả ở đây là mô tả");
//        food_ntDanh1.setFood_img(R.drawable.food_donut);
//        food_ntDanh1.setFood_price(35000.0);
//        food_ntDanh1.setRes_id(1);
//        food_ntDanh1.setCate_id(2);
//        foodDAO_ntDanh.insertFood_NTDanh(food_ntDanh1);
//
//        food_NTDanh food_ntDanh2 = new food_NTDanh();
//        food_ntDanh2.setFood_name("Quẩy Giòn");
//        food_ntDanh2.setFood_des("Mô tả ở đây là mô tả");
//        food_ntDanh2.setFood_img(R.drawable.food_quay);
//        food_ntDanh2.setFood_price(15000.0);
//        food_ntDanh2.setRes_id(3);
//        food_ntDanh2.setCate_id(3);
//        foodDAO_ntDanh.insertFood_NTDanh(food_ntDanh2);

        List<food_NTDanh> food_ntDanhList = foodDAO_ntDanh.getAll();
        adapterTodayMenuNtDanh = new adapter_TodayMenu_NTDanh(food_ntDanhList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_todayMenu_NTDanh.setLayoutManager(layoutManager);
        recycler_todayMenu_NTDanh.setAdapter(adapterTodayMenuNtDanh);
    }

    private void loadCate(RecyclerView recycler_categories_NTDanh){
        cateDAO_NTDanh cateDAO_ntDanh = new cateDAO_NTDanh(getContext());
        List<cate_NTDanh> cate_ntDanhList = cateDAO_ntDanh.getAll();
        adapterCategoriesNtDanh = new adapter_categories_NTDanh(cate_ntDanhList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_categories_NTDanh.setLayoutManager(linearLayoutManager);
        recycler_categories_NTDanh.setAdapter(adapterCategoriesNtDanh);
    }
}