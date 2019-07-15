package efana.example.inventory20.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductEntry.class}, version = 1, exportSchema = false)
public abstract class InventoryDatabase extends RoomDatabase {

    private static final String LOG_TAG = InventoryDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "inventory";
    private static InventoryDatabase sInstance;

    public static InventoryDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating a new databse instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        InventoryDatabase.class, InventoryDatabase.DATABASE_NAME)
//                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract ProductDao productDao();
}
