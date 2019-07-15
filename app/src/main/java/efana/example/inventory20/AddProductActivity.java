package efana.example.inventory20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import efana.example.inventory20.database.InventoryDatabase;
import efana.example.inventory20.database.ProductEntry;

public class AddProductActivity extends AppCompatActivity {

    // Extra for the product ID to be received in the intent
    public static final String EXTRA_PRODUCT_ID = "extraProductId";
    // Extra for the product ID to be received after rotation
    public static final String INSTANCE_PRODUCT_ID = "instanceTaskId";


    // Constant for default product id to be used when not in update mode
    private static final int DEFAULT_PRODUCT_ID = -1;
    // Constant for logging
    private static final String TAG = AddProductActivity.class.getSimpleName();
    // Fields for views
    EditText mProductName;
    EditText mQuantity;
    EditText mPrice;

    Button mButton;

    private int mProductId = DEFAULT_PRODUCT_ID;

    // Member variable for the Database
    private InventoryDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mProductName = findViewById(R.id.eTProductName);
        mQuantity = findViewById(R.id.eTQuantity);
        mPrice = findViewById(R.id.eTPrice);


        mButton = findViewById(R.id.addProductBtn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveProductClicked();
            }
        });

        mDb = InventoryDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_PRODUCT_ID)) {
            mProductId = savedInstanceState.getInt(INSTANCE_PRODUCT_ID, DEFAULT_PRODUCT_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PRODUCT_ID)) {
            mButton.setText("Update Product");
            if (mProductId == DEFAULT_PRODUCT_ID) {
                // populate the UI
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_PRODUCT_ID, mProductId);
        super.onSaveInstanceState(outState);
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param product the productEntry to populate the UI
     */
    private void populateUI(ProductEntry product) {
        if (product == null) {
            return;
        }

        mProductName.setText(product.getProductName());
        mQuantity.setText(product.getQuantity());
        mPrice.setText(product.getPrice());

    }

    /*



    /**
     * onSaveProductClicked is called when the "add product" button is clicked.
     * It retrieves user input and inserts that new product data into the underlying database.
     */
    public void onSaveProductClicked() {

        String productName = mProductName.getText().toString();
        int quantity = Integer.parseInt(mQuantity.getText().toString());
        int price = Integer.parseInt(mPrice.getText().toString());

        ProductEntry productEntry = new ProductEntry(productName, quantity, price);
        mDb.productDao().insertProduct(productEntry);
        finish();

        final ProductEntry product = new ProductEntry(productName, quantity, price);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mProductId == DEFAULT_PRODUCT_ID) {
                    // insert new product
                    mDb.productDao().insertProduct(product);
                } else {
                    //update product
                    product.setId(mProductId);
                    mDb.productDao().updateProduct(product);
                }
                finish();
            }
        });
    }


}
