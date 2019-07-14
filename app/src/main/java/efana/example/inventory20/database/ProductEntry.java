package efana.example.inventory20.database;

import androidx.core.graphics.drawable.IconCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class ProductEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "product_name")
    private String productName;
    private int quantity;
    private String price;

    @Ignore
    public ProductEntry(String productName, int quantity, String price) {
        this.setProductName(productName);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public ProductEntry(int id, String productName, int quantity, String price) {
        this.setId(id);
        this.setProductName(productName);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
