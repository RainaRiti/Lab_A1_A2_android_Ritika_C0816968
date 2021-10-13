package com.example.lab_a1_a2_android_ritika_c0816968.Adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_a1_a2_android_ritika_c0816968.Db.DbConnect;
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Product;
import com.example.lab_a1_a2_android_ritika_c0816968.R;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.AddUpdateProduct;

import java.util.List;

public abstract class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder> {

    Context context;
    public List<Product> products;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.products = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        holder.name.setText(products.get(position).getName());
        holder.desc.setText(products.get(position).getDesc());
        holder.price.setText(products.get(position).getPrice());
        holder.provider.setText(DbConnect.getInstance(context).getProviderDao().getProviderById(products.get(position).getProvider_id()).getProvider_name());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateList(List<Product> temp) {
        products = temp;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name,price,provider, desc;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            price=(TextView)itemView.findViewById(R.id.price);
            provider =  (TextView)itemView.findViewById(R.id.provider);
            desc =  (TextView)itemView.findViewById(R.id.desc);
            itemView.setOnClickListener(v ->{
                Intent intent = new Intent(context, AddUpdateProduct.class);
                intent.putExtra("product_id", products.get(getAdapterPosition()).getProduct_id());
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            itemView.setOnLongClickListener(v -> {
                longPressIsInvoke(getAdapterPosition());
                return true;
            });
        }
    }

    public abstract void updateScreen(int i);
    public abstract void longPressIsInvoke(int i);


}

