package test.com.flickrgallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class DetailActivity extends AppCompatActivity {
    private final static String KEY_PHOTO = "test.com.flickrgallery.DetailActivity/key photo";
    private final static String KEY_URI = "test.com.flickrgallery.DetailActivity/key url";
    private static final String KEY_TITLE = "test.com.flickrgallery.DetailActivity/key title";
    private static final String KEY_COLOR_BACKGROUND = "test.com.flickrgallery.DetailActivity/text/key color";
    private static final String KEY_COLOR_BUTTONS = "test.com.flickrgallery.DetailActivity/text/key color buttons";
    private static final String TEXT_PLAIN = "test.com.flickrgallery.DetailActivity/text/plain";

    @BindView(R.id.progress_bar_detail)
    ProgressBar progressBarDetail;
    @BindView(R.id.image_share)
    ImageView imageShare;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.image_open_in_browser)
    ImageView imageOpenInBrowser;
    @BindView(R.id.detail_image)
    ImageView detailImage;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.layout_background)
    RelativeLayout layoutBackground;
    @BindView(R.id.title_view)
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        cardView.setBackgroundColor(getBackgroundColor());
        layoutBackground.setBackgroundColor(getBackgroundColor());
        imageBack.setColorFilter(getBackButtonsColor());
        imageShare.setColorFilter(getBackgroundColor());
        imageOpenInBrowser.setColorFilter(getBackgroundColor());

        if (!isInternetAvailable(this)) {
            showNoInternetConnection(this);
        }

        Glide.with(this).load(getPhotoUri()).apply(RequestOptions.errorOf(R.drawable.holder)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBarDetail.setVisibility(View.GONE);
                imageShare.setColorFilter(getBackButtonsColor());
                imageOpenInBrowser.setColorFilter(getBackButtonsColor());
                titleView.setText(getPhotoTitle());
                titleView.setTextColor(getBackButtonsColor());
                return false;
            }
        }).into(detailImage);
    }

    public static void startDetailActivity(Context context,
                                           String photoUri,
                                           String fullUri,
                                           String title,
                                           int backgroundColor,
                                           int backButtonsColor) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_PHOTO, photoUri);
        intent.putExtra(KEY_URI, fullUri);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_COLOR_BACKGROUND, backgroundColor);
        intent.putExtra(KEY_COLOR_BUTTONS, backButtonsColor);
        context.startActivity(intent);
    }

    @OnClick({R.id.image_share, R.id.image_back, R.id.image_open_in_browser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType(TEXT_PLAIN);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.look_what_picture_I_found_on_flickr));
                shareIntent.putExtra(Intent.EXTRA_TEXT, getPhotoUri());
                if (shareIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
                    view.getContext().startActivity(shareIntent);
                }
                break;
            case R.id.image_back:
                onBackPressed();
                break;
            case R.id.image_open_in_browser:
                Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getFullUri()));
                if (siteIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(siteIntent);
                }
                break;
        }
    }

    private String getPhotoUri() {
        return getIntent().getStringExtra(KEY_PHOTO);
    }

    private String getFullUri() {
        String string = getIntent().getStringExtra(KEY_URI);
        if (TextUtils.isEmpty(string)) {
            string = getString(R.string.url_flickr);
        }
        return string;
    }

    private String getPhotoTitle() {
        return getIntent().getStringExtra(KEY_TITLE);
    }

    private int getBackgroundColor() {
        return getIntent().getIntExtra(KEY_COLOR_BACKGROUND, 0);
    }

    private int getBackButtonsColor() {
        return getIntent().getIntExtra(KEY_COLOR_BUTTONS, 0);
    }

    private static boolean isInternetAvailable(@NonNull final Context context) {
        final ConnectivityManager mConMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConMgr != null
                && mConMgr.getActiveNetworkInfo() != null
                && mConMgr.getActiveNetworkInfo().isAvailable()
                && mConMgr.getActiveNetworkInfo().isConnected();
    }

    private void showNoInternetConnection(Context context) {
        Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
