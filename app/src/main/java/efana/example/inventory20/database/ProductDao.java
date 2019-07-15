package efana.example.inventory20.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product ORDER BY id")
    List<ProductEntry> loadAllProducts();

    @Insert
    void insertProduct(ProductEntry productEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProduct(ProductEntry productEntry);

    @Delete
    void deleteProduct(ProductEntry productEntry);

    @Query("SELECT * FROM product WHERE id = :id")
    ProductEntry loadProductById(int id);

}
