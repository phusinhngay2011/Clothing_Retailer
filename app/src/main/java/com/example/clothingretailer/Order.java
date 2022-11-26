package com.example.clothingretailer;

public class Order {
    private String cart_id; // PRIMARY KEY
    private double total_prices;
    private double shipping_fee;
    private int payment_method;
    private boolean paid;

    public Order(String cart_id, double total_prices,
                 double shipping_fee, int payment_method, boolean paid) {
        this.cart_id = cart_id;
        this.total_prices = total_prices;
        this.shipping_fee = shipping_fee;
        this.payment_method = payment_method;
        this.paid = paid;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public double getTotal_prices() {
        return total_prices;
    }

    public void setTotal_prices(double total_prices) {
        this.total_prices = total_prices;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart_id='" + cart_id + '\'' +
                ", total_prices=" + total_prices +
                ", shipping_fee=" + shipping_fee +
                ", payment_method=" + payment_method +
                ", paid=" + paid +
                '}';
    }
}
