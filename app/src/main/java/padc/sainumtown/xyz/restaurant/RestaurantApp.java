package padc.sainumtown.xyz.restaurant;

import android.app.Application;

import padc.sainumtown.xyz.restaurant.data.models.RestaurantModel;

/**
 * Created by sainumtown on 6/26/17.
 */

public class RestaurantApp extends Application{

    public static final String TAG = "Restaurant";

    @Override
    public void onCreate() {
        super.onCreate();
        RestaurantModel.getInstance().LoadRestaurants(getApplicationContext());
    }
}
