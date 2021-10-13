package com.example.lab_a1_a2_android_ritika_c0816968;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lab_a1_a2_android_ritika_c0816968.Db.DbConnect;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Product;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Provider;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.AddUpdateProduct;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.AddUpdateProvider;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.ProductFragment;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.ProviderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab_a1_a2_android_ritika_c0816968.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView back;
    TextView Add;
    TextView txt_title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         back = (TextView) findViewById(R.id.drawer_icon);
         Add = (TextView) findViewById(R.id.add);
         txt_title = (TextView) findViewById(R.id.txt_title);
        ProductFragment fragment = new ProductFragment();
        loadFragment(fragment);
        back.setVisibility(View.GONE);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        int productCount = DbConnect.getInstance(getApplicationContext()).getProductDao().getAll().size();
        if(productCount == 0){
            assigment1();
        }
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seletedItem = navigation.getMenu().findItem(navigation.getSelectedItemId()).toString();
              if( seletedItem.equals("Products")){
                  Intent intent = new Intent(getApplicationContext(), AddUpdateProduct.class);
                  intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                  getApplicationContext().startActivity(intent);
              }
              else{
                  Intent intent = new Intent(getApplicationContext(), AddUpdateProvider.class);
                  intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                  getApplicationContext().startActivity(intent);
              }

            }
        });




    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.products:
                    txt_title.setText("Products");
                    fragment=new ProductFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.providers:
                    txt_title.setText("Providers");
                    fragment=new ProviderFragment();
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void assigment1(){
        List<Provider> arrProviders = new ArrayList<>();
        arrProviders.add(new Provider("Tata", "tata@tata.com", "+919876543210",18.9316635, 68.3503086));
        arrProviders.add(new Provider("Audi", "audi@audi.com", "+919876543210",38.9530312, -95.3238268));
        arrProviders.add(new Provider("Bmw", "bmw@bmw.com", "+919876543210",38.9530312, -95.3238268));
        DbConnect.getInstance(getApplicationContext()).getProviderDao().insertProviders(arrProviders);
        arrProviders = DbConnect.getInstance(getApplicationContext()).getProviderDao().getAllProviders();
        List<Product> arrProducts = new ArrayList<>();
        for (int i = 0; i < arrProviders.size(); i++) {
            if (arrProviders.get(i).getProvider_name().equals("Tata")) {
                arrProducts.add(new Product("Indigo", "Sedan", "20000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("Harrier", "SUV", "30000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("Indica", "Hatchbag", "15000", arrProviders.get(i).getProvider_id()));

            }
            else if(arrProviders.get(i).getProvider_name().equals("Bmw")){
                arrProducts.add(new Product("X6", "SUV", "35000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("X1", "Hatchbag", "25000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("3 series", "Sedan", "40000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("X5", "Suv", "50000", arrProviders.get(i).getProvider_id()));
            }
            else {
                arrProducts.add(new Product("R8", "Sports car", "120000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("Q7", "Suv", "80000", arrProviders.get(i).getProvider_id()));
                arrProducts.add(new Product("A6", "Sedan", "60000", arrProviders.get(i).getProvider_id()));

            }
        }
        DbConnect.getInstance(getApplicationContext()).getProductDao().insertProducts(arrProducts);
    }

}