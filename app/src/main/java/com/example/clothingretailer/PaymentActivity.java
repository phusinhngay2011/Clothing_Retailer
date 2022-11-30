package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    RadioGroup paymentMethodsRG;
    RadioButton momoMethod;
    RadioButton CODMethod;
    RadioButton bankingMethod;
    TextView SubtotalPriceTV;
    TextView ShippingFeeTV;
    TextView TotalPriceTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        GenerateFindViewById_payment();
        setPrice();
    }

    private void setPrice() {
        try {
            SharedPreferences getPrice = getSharedPreferences(
                    PREFERENCES_PRICE, MODE_PRIVATE);
            String subtotal = getPrice.getString(SUBTOTAL_PRICE, "");
            String shipping = getPrice.getString(SHIPPING_PRICE, "");
            SubtotalPriceTV.setText(subtotal);
            ShippingFeeTV.setText(shipping);
            subtotal = subtotal.replaceAll("[.]", "");
            shipping = shipping.replaceAll("[.]", "");
            if(shipping.equals("Free"))
                shipping = "0";
            TotalPriceTV.setText(formatPriceString(Integer.parseInt(subtotal) +
                    Integer.parseInt(shipping)));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void GenerateFindViewById_payment() {
        paymentMethodsRG = (RadioGroup) findViewById(R.id.paymentMethodsRG);
        momoMethod = (RadioButton) findViewById(R.id.momoMethodRB);
        bankingMethod = (RadioButton) findViewById(R.id.bankingMethodRB);
        CODMethod = (RadioButton) findViewById(R.id.CODMethodRB);
        SubtotalPriceTV = (TextView) findViewById(R.id.subtotalPaymentTV);
        ShippingFeeTV = (TextView) findViewById(R.id.shippingFeeTV);
        TotalPriceTV = (TextView) findViewById(R.id.totalPriceTV);
    }

    public void onPaymentMethods(View view){
        int selectedId = view.getId();
        switch (selectedId){
            case R.id.momoMethodRB:
                updatePaymentMethodsRB(momoMethod);
                break;
            case R.id.bankingMethodRB:
                updatePaymentMethodsRB(bankingMethod);
                break;
            case R.id.CODMethodRB:
                updatePaymentMethodsRB(CODMethod);
                break;
        }
    }

    private void updatePaymentMethodsRB(RadioButton selected){
        momoMethod.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_radio_btn_payment_method_off));

        bankingMethod.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_radio_btn_payment_method_off));

        CODMethod.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_radio_btn_payment_method_off));

        selected.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_radio_btn_payment_method_on));

    }

    public static String formatPriceString(int price) {
        String res = String.valueOf(price);
        int len = res.length();
        int position = len - 3;
        for (int i = 0; i < len / 3; i++) {
            res = res.substring(0, position) + '.' + res.substring(position);
            position -= 3;
        }
        return res;
    }

    public void toCart(View view) {
        Intent switchActivityIntent = new Intent(this, ShoppingCartActivity.class);
        startActivity(switchActivityIntent);
    }

    private String PREFERENCES_PRICE = "TOTAL_PRICE_TEMP";
    private String SUBTOTAL_PRICE = "st";
    private String SHIPPING_PRICE = "sp";

    public void toPlaceOrderSuccessfully(View view) {
        setContentView(R.layout.place_order_successfully);
    }

    public void toHome(View view) {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}