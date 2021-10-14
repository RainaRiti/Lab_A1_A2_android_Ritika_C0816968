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
import com.example.lab_a1_a2_android_ritika_c0816968.Db.Provider;
import com.example.lab_a1_a2_android_ritika_c0816968.R;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.AddUpdateProduct;
import com.example.lab_a1_a2_android_ritika_c0816968.ui.dashboard.AddUpdateProvider;

import java.util.List;

public abstract class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.Viewholder> {
    Context context;
    public List<Provider> providerList;

    public ProviderAdapter(Context context, List<Provider> list) {
        this.context = context;
        this.providerList = list;
    }
    @NonNull
    @Override
    public ProviderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.provider_item,parent,false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderAdapter.Viewholder holder, int position) {
        holder.title.setText(providerList.get(position).getProvider_name());
        holder.lat.setText(""+providerList.get(position).getProvider_lat());
        holder.lng.setText(""+providerList.get(position).getProvider_long());
        holder.emailAdd.setText(providerList.get(position).getProvider_email());
        holder.phone.setText(providerList.get(position).getProvider_phone());
        holder.count.setText(""+DbConnect.getInstance(context).getProductDao().getCount(providerList.get(position).getProvider_id()));
    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView phone,title,emailAdd, lat,lng,count;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            count=(TextView)itemView.findViewById(R.id.product_count);
            emailAdd=(TextView)itemView.findViewById(R.id.txtViewemail);
            phone =  (TextView)itemView.findViewById(R.id.txtViewPhone);
            lat =  (TextView)itemView.findViewById(R.id.txtViewlat);
            lng =  (TextView)itemView.findViewById(R.id.txtViewlng);

            itemView.setOnClickListener(v ->{
                Intent intent = new Intent(context, AddUpdateProvider.class);
                intent.putExtra("provider_id", providerList.get(getAdapterPosition()).getProvider_id());
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            itemView.setOnLongClickListener(v -> {
                longPressIsInvoke(getAdapterPosition());
                return true;
            });

        }
    }

    public void updateList(List<Provider> temp) {
        providerList = temp;
        notifyDataSetChanged();
    }
    public abstract void longPressIsInvoke(int i);
}
