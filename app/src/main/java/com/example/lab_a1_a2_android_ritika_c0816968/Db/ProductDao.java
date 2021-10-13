package com.example.lab_a1_a2_android_ritika_c0816968.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE provider_id = :id")
    Product getProduct(int id);

    @Insert
    void insertProducts(List<Product> Products);

    @Insert
    void insert(Product Product);


    @Update
    void update(Product repos);


    @Delete
    void delete(Product Product);


    @Delete
    void delete(Product... Product);

}
