package padc.sainumtown.xyz.restaurant.utilities;

import com.google.gson.Gson;

/**
 * Created by sainumtown on 6/19/17.
 */

public class CommonInstances {
    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
