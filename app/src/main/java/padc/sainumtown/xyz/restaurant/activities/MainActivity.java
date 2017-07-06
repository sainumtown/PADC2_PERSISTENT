package padc.sainumtown.xyz.restaurant.activities;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import padc.sainumtown.xyz.restaurant.R;
import padc.sainumtown.xyz.restaurant.RestaurantApp;
import padc.sainumtown.xyz.restaurant.RestaurantConstants;
import padc.sainumtown.xyz.restaurant.adapters.RestaurantAdapter;
import padc.sainumtown.xyz.restaurant.data.agents.retrofit.RetrofitDataAgent;
import padc.sainumtown.xyz.restaurant.data.models.RestaurantModel;
import padc.sainumtown.xyz.restaurant.data.persistence.RestaurantContract;
import padc.sainumtown.xyz.restaurant.data.vos.RestaurantVO;
import padc.sainumtown.xyz.restaurant.events.DataEvent;

public class MainActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_restaurant)
    RecyclerView rvRestaurant;

    RestaurantAdapter mRestaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        // network call
        // Context context = getApplicationContext();
        // RetrofitDataAgent.getInstance().loadRestaurants(context);

        mRestaurantAdapter = new RestaurantAdapter(this);
        rvRestaurant.setAdapter(mRestaurantAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvRestaurant.setLayoutManager(llm);

        getSupportLoaderManager().initLoader(RestaurantConstants.RESTAURANT_LIST_LOADER, null, this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!EventBus.getDefault().isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadRestaurantEvent(DataEvent.RestaurantDataLoadEvent event) {

        List<RestaurantVO> restaurantList = event.getRestaurantListResponse();
        mRestaurantAdapter.setNewData(restaurantList);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                RestaurantContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                RestaurantContract.RestaurantEntry.COLUMN_TITLE + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RestaurantVO> restaurantList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);

                restaurantList.add(restaurant);
            } while (data.moveToNext());
        }

        Log.d(RestaurantApp.TAG, "Retrieved restaurants DESC : " + restaurantList.size());
        mRestaurantAdapter.setNewData(restaurantList);
        //AttractionModel.getInstance().setStoredData(attractionList);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
