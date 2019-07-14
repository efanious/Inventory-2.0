package efana.example.inventory20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import efana.example.inventory20.database.ProductEntry;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    // Class variables for the List that holds product data and the Context
    private List<ProductEntry> mProductEntries;
    private Context mContext;

    /**
     * Constructor for the ProductAdapter that initializes the Context.
     *
     * @param context  the current Context
     */
    public ProductAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        // Determine the values of the wanted data
        ProductEntry productEntry = mProductEntries.get(position);
        String productName = productEntry.getProductName();
        int quantity = productEntry.getQuantity();
        int price = productEntry.getPrice();

        //Set values
        holder.productName.setText(productName);
        holder.quantity.setText(String.valueOf(quantity));
        holder.price.setText(String.valueOf(price));

    }

    @Override
    public int getItemCount() {
        if (mProductEntries == null) {
            return 0;
        }
        return mProductEntries.size();
    }

    public List<ProductEntry> getProducts() {
        return mProductEntries;
    }

    public void setProducts(List<ProductEntry> productEntries) {
        mProductEntries = productEntries;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView quantity;
        TextView price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);

        }

    }
}
