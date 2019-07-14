package efana.example.inventory20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import efana.example.inventory20.database.InventoryDatabase;

import static android.widget.GridLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    // Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;

    private InventoryDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.rvProducts);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new ProductAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to start an AddProductActivity
                Intent addProductIntent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(addProductIntent);
            }
        });

        mDb = InventoryDatabase.getInstance(getApplicationContext());
//        retrieveProducts();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setProducts(mDb.productDao().loadAllProducts());
    }

    private void retrieveProducts() {
//        Log.d(TAG, "Actively retrieving the products from the DataBase");
//
//        LiveData<List<ProductEntry>> products = mDb.productDao().loadAllProducts();
//        // COMPLETED (5) Observe tasks and move the logic from runOnUiThread to onChanged
//        products.observe(this, new Observer<List<ProductEntry>>() {
//            @Override
//            public void onChanged(@Nullable List<ProductEntry> productEntries) {
//                Log.d(TAG, "Receiving database update from LiveData");
//                mAdapter.setProducts(productEntries);
//            }
//        });
    }
}
