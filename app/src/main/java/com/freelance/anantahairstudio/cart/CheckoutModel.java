package com.freelance.anantahairstudio.cart;

public class CheckoutModel {

    String name;
    int position;



    public CheckoutModel(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
