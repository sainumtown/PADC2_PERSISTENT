package padc.sainumtown.xyz.restaurant.data.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import padc.sainumtown.xyz.restaurant.data.vos.RestaurantVO;

public class RestaurantListResponse {

    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("restaurants")
    private List<RestaurantVO> restaurants = new ArrayList<RestaurantVO>();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<RestaurantVO> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantVO> restaurants) {
        this.restaurants = restaurants;
    }


}