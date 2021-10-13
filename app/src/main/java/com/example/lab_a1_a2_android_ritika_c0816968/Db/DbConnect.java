package com.example.lab_a1_a2_android_ritika_c0816968.Db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.w3c.dom.ProcessingInstruction;

@Database(entities = { Product.class, Provider.class}, version = 1)
 public abstract class DbConnect extends RoomDatabase {
 public abstract ProductDao getProductDao();
 public abstract ProviderDao getProviderDao();
 private static DbConnect product;

 public static DbConnect getInstance(Context context) {
  if (null == product) {
   product = buildDatabaseInstance(context);
  }
  return product;
 }

 private static DbConnect buildDatabaseInstance(Context context) {
  return Room.databaseBuilder(context,
          DbConnect.class,
          "product")
          .allowMainThreadQueries().build();
 }

}
