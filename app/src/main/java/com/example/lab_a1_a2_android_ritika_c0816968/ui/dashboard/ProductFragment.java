package com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lab_a1_a2_android_ritika_c0816968.Adapter.ProductAdapter;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.DbConnect;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Product;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.ProductDao;
import com.example.lab_a1_a2_android_ritika_c0816968.R;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {
    EditText search;
    RecyclerView recyclerView;
    private ProductDao productDao;
    private List<Product> products;
    private ProductAdapter adapter;
    DbConnect database;


    public ProductFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        search=(EditText)view.findViewById(R.id.search_txt);
        recyclerView=(RecyclerView) view.findViewById(R.id.product_recycler);
        database = DbConnect.getInstance(getActivity());
        productDao = database.getProductDao();
        products = productDao.getAll();
        adapter = new ProductAdapter(getContext(),products) {
            @Override
            public void updateScreen(int i) {

            }

            @Override
            public void longPressIsInvoke(int i) {


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search_note(s.toString());

            }
        });
    return view;
    }

    private void search_note(String text) {
        products =  DbConnect.getInstance(getContext()).getProductDao().getAll();
        List<Product> temp = new ArrayList();
        for (Product n :products) {
            if(n.getName().toLowerCase().contains(text.toLowerCase()) || n.getDesc().toLowerCase().contains(text.toLowerCase())){
                temp.add(n);
            }
        }
        adapter.updateList(temp);

    }
}