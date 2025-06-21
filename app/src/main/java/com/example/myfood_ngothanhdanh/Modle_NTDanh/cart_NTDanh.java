package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class cart_NTDanh {
    private int cartID;
    private int foodID;
    private int quantity;
    private int userID;

    public cart_NTDanh() {}


    public cart_NTDanh(int userID, int quantity, int foodID) {
        this.userID = userID;
        this.quantity = quantity;
        this.foodID = foodID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
