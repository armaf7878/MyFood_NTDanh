package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class restaurantwdistance {
    public restaurant_NTDanh restaurant;
    public float distance;

    public restaurantwdistance(restaurant_NTDanh restaurant, float distance) {
        this.restaurant = restaurant;
        this.distance = distance;
    }

    public restaurant_NTDanh getRestaurant() {
        return restaurant;
    }

    public float getDistance() {
        return distance;
    }
}
