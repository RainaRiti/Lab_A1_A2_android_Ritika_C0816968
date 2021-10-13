package com.example.lab_a1_a2_android_ritika_c0816968.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ProviderDao {

    @Query("SELECT * FROM Provider")
    List<Provider> getAllProviders();

    @Query("SELECT * FROM Provider WHERE provider_id = :id")
    Provider getProviderById(int id);

    @Query("SELECT * FROM Provider WHERE provider_name = :name")
    Provider getProviderByNm(String name);

    @Insert
    void insertProviders(List<Provider> providers);
    @Insert
    void insertProvider(Provider provider);

    @Update
    void updateProvider(Provider provider);

    @Delete
    void deleteProvider(Provider provider);

}
