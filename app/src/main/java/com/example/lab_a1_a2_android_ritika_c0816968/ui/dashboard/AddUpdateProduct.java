package com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_a1_a2_android_ritika_c0816968.Db.DbConnect;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Product;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Provider;
import com.example.lab_a1_a2_android_ritika_c0816968.R;

public class AddUpdateProduct extends AppCompatActivity {
    EditText name;
    EditText desc;
    EditText price;
    EditText provider;
    AppCompatButton add;
    AppCompatButton remove;
    Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_update_product);
        name = (EditText) findViewById(R.id.product_name);
        price = (EditText) findViewById(R.id.product_price);
        provider = (EditText) findViewById(R.id.product_provider);
        desc = (EditText) findViewById(R.id.product_desc);
        add = (AppCompatButton) findViewById(R.id.add_product);
        remove = (AppCompatButton) findViewById(R.id.remove_product);
        add.setOnClickListener(view -> handleAdd());

        Intent i = getIntent();
        int selected_id = i.getIntExtra("product_id",-1);
        if(selected_id != -1){
            remove.setVisibility(View.VISIBLE);
            selectedProduct = DbConnect.getInstance(getApplicationContext()).getProductDao().getProduct(selected_id);
            setupData();
            remove.setOnClickListener(view -> {
                DbConnect.getInstance(getApplicationContext()).getProductDao().delete(selectedProduct);
                finish();
            });
            add.setText("Update");
        }
        else{
            add.setText("Add");
            remove.setVisibility(View.GONE);
        }
    }

    private void setupData() {
        name.setText(selectedProduct.getName());
        price.setText(selectedProduct.getPrice());
        desc.setText(selectedProduct.getDesc());
        provider.setText(DbConnect.getInstance(getApplicationContext()).getProviderDao().getProviderById(selectedProduct.getProvider_id()).getProvider_name());
    }

    public void handleAdd(){
        Provider selected = DbConnect.getInstance(getApplicationContext()).getProviderDao().getProviderByNm(provider.getText().toString());
        if (selected != null){
        if (selectedProduct == null){
            //Add

                Product p = new Product(name.getText().toString(),desc.getText().toString(),price.getText().toString(),selected.getProvider_id());
                DbConnect.getInstance(getApplicationContext()).getProductDao().insert(p);
                Toast.makeText(getApplicationContext(),"Successfully added!", Toast.LENGTH_SHORT).show();
                finish();

        }
        else{
            //Update
            selectedProduct.setName(name.getText().toString());
            selectedProduct.setPrice(price.getText().toString());
            selectedProduct.setDesc(desc.getText().toString());
            selectedProduct.setProvider_id(selected.getProvider_id());
            DbConnect.getInstance(getApplicationContext()).getProductDao().update(selectedProduct);
            Toast.makeText(getApplicationContext(),"Successfully updated!", Toast.LENGTH_SHORT).show();
            finish();
       }
        }
        else{
            Toast.makeText(getApplicationContext(),"Provider not found add it first!", Toast.LENGTH_SHORT).show();
        }


    }
}