package test.com.flickrgallery;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class App extends Application {
    public static final String BASE_URL = "https://api.flickr.com";
    private static FlickrApi flickrApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        flickrApi = retrofit.create(FlickrApi.class);
    }

    public static FlickrApi getApi() {
        return flickrApi;
    }
}
