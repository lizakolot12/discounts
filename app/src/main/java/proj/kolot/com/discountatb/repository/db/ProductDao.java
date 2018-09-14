package proj.kolot.com.discountatb.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import proj.kolot.com.discountatb.model.Product;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Product> list);

    @Query("DELETE FROM products_table where category = :category")
    void deleteByCategory(int category);

    @Query("SELECT * from products_table where category = :category")
    List<Product> getAllProductsByCategory(int category);
}