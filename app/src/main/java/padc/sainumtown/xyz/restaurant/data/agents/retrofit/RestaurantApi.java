package padc.sainumtown.xyz.restaurant.data.agents.retrofit;

import padc.sainumtown.xyz.restaurant.RestaurantConstants;
import padc.sainumtown.xyz.restaurant.data.responses.RestaurantListResponse;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestaurantApi {


    @POST(RestaurantConstants.API_GET_RESTAURANT_LIST)
    Call<RestaurantListResponse> loadRestaurants();
}
