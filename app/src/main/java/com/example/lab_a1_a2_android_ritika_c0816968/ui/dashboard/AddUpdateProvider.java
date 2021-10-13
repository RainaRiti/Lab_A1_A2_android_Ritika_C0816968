package com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Insert;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab_a1_a2_android_ritika_c0816968.Db.DbConnect;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Product;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Provider;
import com.example.lab_a1_a2_android_ritika_c0816968.R;

public class AddUpdateProvider extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email;
    EditText lat;
    EditText longi;
    AppCompatButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_provider);
        name = (EditText) findViewById(R.id.provider_name);
        phone = (EditText) findViewById(R.id.provider_phone);
        email = (EditText) findViewById(R.id.provider_email);
        lat = (EditText) findViewById(R.id.provider_lat);
        longi = (EditText) findViewById(R.id.provider_long);
        add = (AppCompatButton) findViewById(R.id.add_provider);
        add.setOnClickListener(view -> handleAdd());
    }
    public void handleAdd(){
        Provider p = new Provider(name.getText().toString(),email.getText().toString(),phone.getText().toString(),Double.parseDouble(lat.getText().toString()),Double.parseDouble(longi.getText().toString()));
        DbConnect.getInstance(getApplicationContext()).getProviderDao().insertProvider(p);
        Toast.makeText(getApplicationContext(),"Successfully added!", Toast.LENGTH_LONG).show();
        finish();
    }
}