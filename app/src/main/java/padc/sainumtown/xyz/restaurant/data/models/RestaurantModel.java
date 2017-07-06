package padc.sainumtown.xyz.restaurant.data.models;

import android.content.Context;

import java.util.List;

import padc.sainumtown.xyz.restaurant.data.vos.RestaurantVO;

/**
 * Created by sainumtown on 6/19/17.
 */

public class RestaurantModel extends BaseModel {

    private static RestaurantModel objInstance;
    private List<RestaurantVO> mRestaurantList;

    public static RestaurantModel getInstance() {
        if (objInstance == null) {
            objInstance = new RestaurantModel();
        }
        return objInstance;
    }

    public List<RestaurantVO> getRestaurantList() {
        return mRestaurantList;
    }

    public void notifyRestaurantsLoaded(List<RestaurantVO> restaurantList, Context context) {

        // mRestaurantList = restaurantList;
        RestaurantVO.saveRestaurants(restaurantList, context);
    }

    public void notifyErrorInLoadingRestaurants(String message) {

    }

    public void LoadRestaurants(Context context) {
        dataAgent.loadRestaurants(context);
    }

    public void notifyRestaurantsFailed() {

    }
}
