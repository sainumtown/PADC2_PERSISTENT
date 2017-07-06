package padc.sainumtown.xyz.restaurant.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import padc.sainumtown.xyz.restaurant.R;
import padc.sainumtown.xyz.restaurant.data.vos.RestaurantVO;
import padc.sainumtown.xyz.restaurant.views.holders.RestaurantViewHolder;

/**
 * Created by sainumtown on 6/20/17.
 */

public class RestaurantAdapter extends BaseRecycleAdapter<RestaurantViewHolder, RestaurantVO> {

    public RestaurantAdapter(Context context) {
        super(context);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
    
}



