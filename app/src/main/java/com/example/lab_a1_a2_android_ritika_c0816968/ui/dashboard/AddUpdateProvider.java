package com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Insert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    AppCompatButton remove;
    Button call;
    Button emailme;
    Provider selectedProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_provider);
        name = (EditText) findViewById(R.id.provider_name);
        phone = (EditText) findViewById(R.id.provider_phone);
        email = (EditText) findViewById(R.id.provider_email);
        lat = (EditText) findViewById(R.id.provider_lat);
        longi = (EditText) findViewById(R.id.provider_long);
        call = (Button) findViewById(R.id.call);
        emailme = (Button) findViewById(R.id.email);
        add = (AppCompatButton) findViewById(R.id.add_provider);
        remove = (AppCompatButton) findViewById(R.id.remove_provider);
        add.setOnClickListener(view -> handleAdd());
        Intent i = getIntent();
        int selected_id = i.getIntExtra("provider_id",-1);
        if(selected_id != -1){
            remove.setVisibility(View.VISIBLE);
            call.setVisibility(View.VISIBLE);
            emailme.setVisibility(View.VISIBLE);
            selectedProvider = DbConnect.getInstance(getApplicationContext()).getProviderDao().getProviderById(selected_id);
            setupData();
            remove.setOnClickListener(view -> {
                DbConnect.getInstance(getApplicationContext()).getProviderDao().deleteProvider(selectedProvider);
                finish();
            });
            add.setText("Update");
        }
        else{
            remove.setVisibility(View.GONE);
            call.setVisibility(View.GONE);
            emailme.setVisibility(View.GONE);
            add.setText("Add");
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callAction = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + selectedProvider.getProvider_phone().trim()));
                startActivity(callAction);
            }
        });
        emailme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, name.toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, "body");
                startActivity(Intent.createChooser(emailIntent, "Email me"));
            }
        });
    }

    private void setupData() {
        name.setText(selectedProvider.getProvider_name());
        phone.setText(selectedProvider.getProvider_phone());
        email.setText(selectedProvider.getProvider_email());
        lat.setText(""+selectedProvider.getProvider_lat());
        longi.setText(""+selectedProvider.getProvider_long());
    }

    public void handleAdd(){
        if (selectedProvider == null){
            Provider p = new Provider(name.getText().toString(),email.getText().toString(),phone.getText().toString(),Double.parseDouble(lat.getText().toString()),Double.parseDouble(longi.getText().toString()));
            DbConnect.getInstance(getApplicationContext()).getProviderDao().insertProvider(p);
            Toast.makeText(getApplicationContext(),"Successfully added!", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            //Update
            selectedProvider.setProvider_name(name.getText().toString());
            selectedProvider.setProvider_email(email.getText().toString());
            selectedProvider.setProvider_phone(phone.getText().toString());
            selectedProvider.setProvider_lat(Double.parseDouble(lat.getText().toString()));
            selectedProvider.setProvider_long(Double.parseDouble(longi.getText().toString()));
            DbConnect.getInstance(getApplicationContext()).getProviderDao().updateProvider(selectedProvider);
            Toast.makeText(getApplicationContext(),"Successfully updated!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}