package com.example.lab_a1_a2_android_ritika_c0816968.Db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (foreignKeys = {
        @ForeignKey(entity = Provider.class,
                parentColumns = "provider_id", childColumns = "provider_id", onDelete = ForeignKey.CASCADE)
})
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int product_id;
    private String name;
    private String desc;
    private String price;
    @NonNull
    private int provider_id;

    public Product(String name, String desc, String price, int provider_id) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.provider_id = provider_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }
}