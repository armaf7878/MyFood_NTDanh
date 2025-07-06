package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class cart_NTDanh {
    private String cartID;
    private String foodID;
    private int quantity;
    private String userID;

    public cart_NTDanh() {}


    public cart_NTDanh(String cartID, String foodID, int quantity, String userID) {
        this.cartID = cartID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.userID = userID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
