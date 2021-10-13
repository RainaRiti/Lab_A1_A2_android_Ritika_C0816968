package com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lab_a1_a2_android_ritika_c0816968.Adapter.ProviderAdapter;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.DbConnect;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Provider;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.ProviderDao;
import com.example.lab_a1_a2_android_ritika_c0816968.R;

import java.util.ArrayList;
import java.util.List;


public class ProviderFragment extends Fragment {
    EditText search;
    RecyclerView recyclerView;
    private ProviderDao providerDao;
    private List<Provider> providers;
    private ProviderAdapter adapter;
    DbConnect database;


    public ProviderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_provider, container, false);
        search=(EditText)view.findViewById(R.id.search_txt);
        recyclerView=(RecyclerView) view.findViewById(R.id.provider_recycler);
        database = DbConnect.getInstance(getActivity());
        providerDao = database.getProviderDao();
        providers = providerDao.getAllProviders();
        adapter = new ProviderAdapter(getContext(),providers) {
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
        providers =  DbConnect.getInstance(getContext()).getProviderDao().getAllProviders();
        List<Provider> temp = new ArrayList();
        for (Provider n :providers) {
            if(n.getProvider_name().toLowerCase().contains(text.toLowerCase()) || n.getProvider_email().toLowerCase().contains(text.toLowerCase())){
                temp.add(n);
            }
        }
        adapter.updateList(temp);

    }
}