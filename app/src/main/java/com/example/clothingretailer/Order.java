package com.example.clothingretailer;

public class Order {
    private int cart_id; // PRIMARY KEY
    private int total_price;
    private int shipping_fee;
    private String payment_method;
    private int paid;

    public Order(int cart_id, int total_price, int shipping_fee, String payment_method, int paid) {
        this.cart_id = cart_id;
        this.total_price = total_price;
        this.shipping_fee = shipping_fee;
        this.payment_method = payment_method;
        this.paid = paid;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(int shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart_id='" + cart_id + '\'' +
                ", total_price=" + total_price +
                ", shipping_fee=" + shipping_fee +
                ", payment_method=" + payment_method +
                ", paid=" + paid +
                '}';
    }

}
