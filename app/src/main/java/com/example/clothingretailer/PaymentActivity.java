package com.example.clothingretailer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    RadioGroup paymentMethodsRG;
    RadioButton momoMethod;
    RadioButton CODMethod;
    RadioButton bankingMethod;
    TextView SubtotalPriceTV;
    TextView ShippingFeeTV;
    TextView TotalPriceTV;
    EditText et_name, et_email, et_phone, et_address;
    private DBHandler dbHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        GenerateFindViewById_payment();
        setPrice();
        setupInfo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHandler != null)
        {
            dbHandler.close_DB();
            dbHandler = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHandler == null)
        {
            dbHandler = new DBHandler(getApplicationContext());
        }
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
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_address = findViewById(R.id.et_address);
    }

    private void setupInfo()
    {
        if (GlobalVars.current_user != null && GlobalVars.logged_in == true)
        {
            et_name.setText(GlobalVars.current_user.getFirstname() + " " + GlobalVars.current_user.getLastname());
            et_email.setText(GlobalVars.current_user.getEmail());
            et_phone.setText(GlobalVars.current_user.getPhone());
            et_address.setText(GlobalVars.current_user.getAddress());
        }
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
        if (len % 3 == 0)
            res = res.substring(1);
        return res;
    }

    public void toCart(View view) {
//        Intent switchActivityIntent = new Intent(this, ShoppingCartActivity.class);
//        startActivity(switchActivityIntent);
        onBackPressed();
    }

    private String PREFERENCES_PRICE = "TOTAL_PRICE_TEMP";
    private String SUBTOTAL_PRICE = "st";
    private String SHIPPING_PRICE = "sp";

    public void toPlaceOrderSuccessfully(View view) {
        setContentView(R.layout.place_order_successfully);
    }

    public void processCart(View view)
    {
        //Log.d("global vars", GlobalVars.current_cart_items.get(0).getId() + " " + GlobalVars.current_cart_sizes.get(0) + " " + GlobalVars.current_cart_colors.get(0) + " " + GlobalVars.current_cart_item_counts);
        if (GlobalVars.current_user == null || GlobalVars.logged_in == false)
        {
            try {
                new AlertDialog.Builder(PaymentActivity.this)
                        .setTitle("Requires login")
                        .setMessage("You need to log in to use more features")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Log in", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent switchActivityIntent = new Intent(PaymentActivity.this, SignInMainActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        })

                        .setNegativeButton("Back to homepage", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent switchActivityIntent = new Intent(PaymentActivity.this, MainActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            catch (Exception e){
                Toast.makeText(PaymentActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if (GlobalVars.quick_purchase_mode == false)
            {
                if (GlobalVars.current_cart_items.size() == 0)
                {
                    Toast.makeText(PaymentActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Integer> stock_qty_list = new ArrayList<Integer>();
                for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
                {
                    ArrayList<ItemQuantity> res = dbHandler.search_quantity(GlobalVars.current_cart_items.get(i).getId(),
                            GlobalVars.current_cart_sizes.get(i),
                            GlobalVars.current_cart_colors.get(i));


                    if (res == null || res.size() == 0 || res.get(0).getCount() == 0)
                    {
                        Toast.makeText(PaymentActivity.this, "Sorry, your item \"" + GlobalVars.current_cart_items.get(i).getName()
                                + "\" is out of stock!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (res.get(0).getCount() < GlobalVars.current_cart_item_counts.get(i))
                    {
                        Toast.makeText(PaymentActivity.this, "We only have " + res.get(0).getCount() +  " of your item \"" +
                                GlobalVars.current_cart_items.get(i).getName() + String.format("\" (Size %s, Color %s) left!",
                                GlobalVars.current_cart_sizes.get(i), GlobalVars.current_cart_colors.get(i)), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    stock_qty_list.add(res.get(0).getCount());
                }
                Log.d("qty list", stock_qty_list + "");
                Cart cart = dbHandler.add_cart(GlobalVars.current_user.getUsername());
                Log.d("new cart", String.valueOf(cart.getCart_id()));
                if (cart == null)
                {
                    Toast.makeText(PaymentActivity.this, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
                {
                    Item item = GlobalVars.current_cart_items.get(i);
                    int new_qty = stock_qty_list.get(i) - GlobalVars.current_cart_item_counts.get(i);
                    dbHandler.update_quantity(item.getId(), GlobalVars.current_cart_sizes.get(i), GlobalVars.current_cart_colors.get(i), new_qty);
                    //Log.d("update qty", String.valueOf(item.getId()) + new_qty);
                    dbHandler.add_cart_item(cart.getCart_id(), item.getId(), GlobalVars.current_cart_sizes.get(i), GlobalVars.current_cart_colors.get(i), GlobalVars.current_cart_item_counts.get(i));
                    //Log.d("add cart item", String.valueOf(GlobalVars.current_cart_item_counts.get(i)));
                }
                dbHandler.add_order(cart.getCart_id(),
                        Integer.parseInt(((String) SubtotalPriceTV.getText()).replaceAll("[.]", "")),
                        (!ShippingFeeTV.getText().equals("Free") ? Integer.parseInt(((String) ShippingFeeTV.getText()).replaceAll("[.]", "")) : 0), get_payment_method(), 1);

                GlobalVars.current_cart_items.clear();
                GlobalVars.current_cart_sizes.clear();
                GlobalVars.current_cart_colors.clear();
                GlobalVars.current_cart_item_counts.clear();

                toPlaceOrderSuccessfully(view);
            }
        }
    }

    private String get_payment_method()
    {
        int id = paymentMethodsRG.getCheckedRadioButtonId();
        if (id == momoMethod.getId())
            return "momo";
        else if (id == bankingMethod.getId())
            return "internet-banking";
        else if (id == CODMethod.getId())
            return "cod";

        return null;
    }

    public void toHome(View view) {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}