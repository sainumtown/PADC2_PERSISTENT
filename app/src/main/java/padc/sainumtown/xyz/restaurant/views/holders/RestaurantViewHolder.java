package padc.sainumtown.xyz.restaurant.views.holders;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import padc.sainumtown.xyz.restaurant.R;
import padc.sainumtown.xyz.restaurant.data.vos.RestaurantVO;

/**
 * Created by sainumtown on 6/19/17.
 */

public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_tags)
    TextView tvTags;

    @BindView(R.id.tv_deliver_time)
    TextView tvDeliverTime;

    @BindView(R.id.iv_advertisment)
    ImageView ivAdvertisment;

    @BindView(R.id.rb_Restaurant)
    RatingBar rbRestaurant;

    @BindView(R.id.tv_rating_count)
    TextView tvRatingCount;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RestaurantVO data) {

        bindData(data);

    }

    private void bindData(RestaurantVO data) {
        tvTitle.setText(getTitleWithShortAddress(data));
        tvTags.setText(getTags(data));
        tvDeliverTime.setText(getDeliverMin(data));
        if (!data.getIsAd()) {
            ivAdvertisment.setVisibility(View.GONE);
        }
        rbRestaurant.setRating(getRating(data));
        tvRatingCount.setText(" ( " + data.getTotalRatingCount().toString() + " ) ");
    }

    private float getRating(RestaurantVO data) {
        return Float.parseFloat(data.getAverageRatingValue().toString());
    }

    @NonNull
    private String getDeliverMin(RestaurantVO data) {
        return data.getLeadTimeInMin().toString() + " min.";
    }

    private String getTags(RestaurantVO data) {
        return TextUtils.join(", ", data.getTags());
    }

    @NonNull
    private String getTitleWithShortAddress(RestaurantVO data) {
        if (data.getAddrShort() == null || TextUtils.isEmpty(data.getAddrShort())) {
            return data.getTitle();
        } else {
            return data.getTitle() + " ( " + data.getAddrShort() + " ) ";
        }

    }
}
