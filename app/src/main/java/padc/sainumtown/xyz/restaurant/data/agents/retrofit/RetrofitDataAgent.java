package padc.sainumtown.xyz.restaurant.data.agents.retrofit;


import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import padc.sainumtown.xyz.restaurant.RestaurantConstants;
import padc.sainumtown.xyz.restaurant.data.agents.RestaurantDataAgent;
import padc.sainumtown.xyz.restaurant.data.models.RestaurantModel;
import padc.sainumtown.xyz.restaurant.data.responses.RestaurantListResponse;
import padc.sainumtown.xyz.restaurant.utilities.CommonInstances;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgent implements RestaurantDataAgent {


    private static RetrofitDataAgent objInstance;

    private final RestaurantApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantConstants.RESTAURNAT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RestaurantApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadRestaurants(final Context context) {
        Call<RestaurantListResponse> loadRestaurantsCall = theApi.loadRestaurants();
        loadRestaurantsCall.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {

                RestaurantListResponse restaurantListResponse = response.body();
                if (restaurantListResponse == null) {
                    // notify for fail

                } else {
                    // notify for success
                    // EventBus.getDefault().post(new DataEvent.RestaurantDataLoadEvent(restaurantListResponse.getRestaurants()));

                    RestaurantModel.getInstance().notifyRestaurantsLoaded(restaurantListResponse.getRestaurants(), context);

                }
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {
                // notify for fail
                RestaurantModel.getInstance().notifyRestaurantsFailed();

            }
        });

    }

}
