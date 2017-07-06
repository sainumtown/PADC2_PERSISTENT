package padc.sainumtown.xyz.restaurant.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by sainumtown on 6/25/17.
 */

public abstract class BaseVO<W extends Object> {


    public BaseVO() {
    }


    public ContentValues parseToContentValues() {
        return null;
    }
}
