package padc.sainumtown.xyz.restaurant.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import padc.sainumtown.xyz.restaurant.views.holders.BaseViewHolder;

/**
 * Created by sainumtown on 6/19/17.
 */

public abstract class BaseRecycleAdapter<T extends BaseViewHolder, W> extends RecyclerView.Adapter<T> {

    protected LayoutInflater mLayoutInflater;
    protected List<W> mData;


    public BaseRecycleAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    public void setNewData(List<W> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
