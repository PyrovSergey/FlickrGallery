package test.com.flickrgallery;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import test.com.flickrgallery.IdlingResource.SimpleIdlingResource;
import test.com.flickrgallery.Model.PhotoItem;
import test.com.flickrgallery.Model.Response;

public final class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String FLICKR_PHOTOS_SEARCH = "flickr.photos.search";
    private static final String FLICKR_PHOTOS_GET_RECENT = "flickr.photos.getRecent";
    private static final String KEY = "b5b12ac4cb833aac7e7e5268027c0ac3";
    private static final String FORMAT_RESPONSE = "json";
    private static final String IMAGE_TYPE = "url_s";
    @Nullable
    private SimpleIdlingResource idlingResource;

    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.photo_recycler_view)
    RecyclerView photoRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private List<PhotoItem> photoItems = new ArrayList<>();
    private PhotoAdapter photoAdapter;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getIdlingResource();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
        search.setIconifiedByDefault(true);
        swipeRefresh.setOnRefreshListener(this);
        photoRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        photoAdapter = new PhotoAdapter(this, photoItems, idlingResource);
        photoRecyclerView.setAdapter(photoAdapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPhotos(query);
                search.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                getNewPhotos();
                return false;
            }
        });

        getNewPhotos();
    }

    @Override
    public void onRefresh() {
        search.setQuery("", false);
        search.clearFocus();
        getNewPhotos();
    }

    private void getNewPhotos() {
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        if (isInternetAvailable(this)) {
            App.getApi()
                    .getData(FLICKR_PHOTOS_GET_RECENT,
                            KEY,
                            FORMAT_RESPONSE,
                            1, IMAGE_TYPE)
                    .enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            updateData(response);
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            //showError();
                        }
                    });
        } else {
            showNoInternetConnection(this);
        }
    }

    private void searchPhotos(String query) {
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        if (isInternetAvailable(this)) {
        App.getApi()
                .searchData(FLICKR_PHOTOS_SEARCH,
                        KEY,
                        FORMAT_RESPONSE,
                        1, IMAGE_TYPE, query)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        updateData(response);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        //showError();
                    }
                });
        } else {
            showNoInternetConnection(this);
        }
    }

    private void updateData(retrofit2.Response<Response> response) {

        photoItems = response.body().getPhotos().getPhoto();
        photoAdapter.setDataChanged(photoItems);
        swipeRefresh.setRefreshing(false);
    }

    private void showNoInternetConnection(Context context) {
        Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        swipeRefresh.setRefreshing(false);
    }

    private static boolean isInternetAvailable(@NonNull final Context context) {
        final ConnectivityManager mConMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConMgr != null
                && mConMgr.getActiveNetworkInfo() != null
                && mConMgr.getActiveNetworkInfo().isAvailable()
                && mConMgr.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
        search.clearFocus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
