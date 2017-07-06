package padc.sainumtown.xyz.restaurant.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import padc.sainumtown.xyz.restaurant.RestaurantApp;
import padc.sainumtown.xyz.restaurant.RestaurantConstants;
import padc.sainumtown.xyz.restaurant.data.persistence.RestaurantContract;

public class RestaurantVO extends BaseVO<RestaurantVO> {

    @SerializedName("title")
    private String title;
    @SerializedName("addr-short")
    private String addrShort;
    @SerializedName("image")
    private String image;
    @SerializedName("total-rating-count")
    private Integer totalRatingCount;
    @SerializedName("average-rating-value")
    private Double averageRatingValue;
    @SerializedName("is-ad")
    private Boolean isAd;
    @SerializedName("is-new")
    private Boolean isNew;
    @SerializedName("tags")
    private List<String> tags = new ArrayList<String>();
    @SerializedName("lead-time-in-min")
    private Integer leadTimeInMin;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(Integer totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public Double getAverageRatingValue() {
        return averageRatingValue;
    }

    public void setAverageRatingValue(Double averageRatingValue) {
        this.averageRatingValue = averageRatingValue;
    }

    public Boolean getIsAd() {
        return isAd;
    }

    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public void setLeadTimeInMin(Integer leadTimeInMin) {
        this.leadTimeInMin = leadTimeInMin;
    }


    public static RestaurantVO parseFromCursor(Cursor data) {

        RestaurantVO restaurant = new RestaurantVO();
        restaurant.title = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TITLE));
        restaurant.addrShort = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_ADDR_SHORT));
        restaurant.totalRatingCount = Integer.valueOf(data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT)));
        restaurant.averageRatingValue = Double.valueOf(data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE)));
        restaurant.isAd = Boolean.valueOf(data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IS_AD)));
        restaurant.isNew = Boolean.valueOf(data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IS_NEW)));
        //TODO to change many to many relationship
        restaurant.tags = Arrays.asList(data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TAGS)));
        restaurant.leadTimeInMin = Integer.valueOf(data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN)));
        return restaurant;
    }


    public ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_ADDR_SHORT, addrShort);
        //TODO change to many to many for tags
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TAGS, TextUtils.join(", ", tags));
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCount);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE, averageRatingValue);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IS_NEW, isNew);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, leadTimeInMin);

        return cv;
    }

    public static void saveRestaurants(List<RestaurantVO> restaurantList, Context context) {

        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];
        for (int index = 0; index < restaurantList.size(); index++) {
            RestaurantVO restaurant = restaurantList.get(index);
            restaurantCVs[index] = restaurant.parseToContentValues();

            //Bulk insert into images.
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(RestaurantContract.RestaurantEntry.CONTENT_URI, restaurantCVs);
        Log.d(RestaurantApp.TAG, "Bulk inserted into " + RestaurantContract.PATH_RESTAURANTS + "table : " + insertedCount);
    }

}