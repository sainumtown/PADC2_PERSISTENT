package padc.sainumtown.xyz.restaurant.events;

import java.util.List;

import padc.sainumtown.xyz.restaurant.data.responses.RestaurantListResponse;
import padc.sainumtown.xyz.restaurant.data.vos.RestaurantVO;

/**
 * Created by sainumtown on 6/19/17.
 */

public class DataEvent {
    public static class RestaurantDataLoadEvent {
        private List<RestaurantVO> restaurantListResponse;

        public RestaurantDataLoadEvent(List<RestaurantVO> restaurantListResponse) {
            this.restaurantListResponse = restaurantListResponse;
        }

        public List<RestaurantVO> getRestaurantListResponse() {
            return restaurantListResponse;
        }


    }
}
