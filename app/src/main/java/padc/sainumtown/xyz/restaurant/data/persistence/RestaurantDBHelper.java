package padc.sainumtown.xyz.restaurant.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import padc.sainumtown.xyz.restaurant.data.persistence.RestaurantContract.RestaurantEntry;

/**
 * Created by sainumtown on 6/26/17.
 */

public class RestaurantDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restaurants.db";


    // create table
    private static final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
            RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_ADDR_SHORT + " TEXT, " +
            RestaurantEntry.COLUMN_TAGS + " TEXT, " +
            RestaurantEntry.COLUMN_IMAGE + " TEXT , " +
            RestaurantEntry.COLUMN_TOTAL_RATING_COUNT + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE + " DOUBLE NOT NULL, " +
            RestaurantEntry.COLUMN_IS_AD + " BOOLEAN NOT NULL, " +
            RestaurantEntry.COLUMN_IS_NEW + " BOOLEAN NOT NULL, " +
            RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " INTEGER NOT NULL, " +

            " UNIQUE (" + RestaurantEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    // drop table
    private static final String SQL_DROP_RESTAURANT_TABLE = "DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME;

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropTables(sqLiteDatabase);
        onCreate(sqLiteDatabase);

    }

    private void dropTables(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DROP_RESTAURANT_TABLE);
    }
}
