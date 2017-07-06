package padc.sainumtown.xyz.restaurant.data.models;

import padc.sainumtown.xyz.restaurant.data.agents.RestaurantDataAgent;
import padc.sainumtown.xyz.restaurant.data.agents.retrofit.RetrofitDataAgent;

/**
 * Created by sainumtown on 6/19/17.
 */

public abstract class BaseModel {
    
    protected RestaurantDataAgent dataAgent;

    public BaseModel() {
        dataAgent = RetrofitDataAgent.getInstance();
    }
}
