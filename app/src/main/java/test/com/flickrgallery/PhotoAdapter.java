package test.com.flickrgallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import test.com.flickrgallery.IdlingResource.SimpleIdlingResource;
import test.com.flickrgallery.Model.PhotoItem;

public final class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private Context context;
    private static List<PhotoItem> photos;
    private SimpleIdlingResource idlingResource;

    public void setDataChanged(List<PhotoItem> newPhotos) {
        photos = newPhotos;
        notifyDataSetChanged();
        if (idlingResource != null) {
            idlingResource.setIdleState(true);
        }
    }

    PhotoAdapter(Context context, List<PhotoItem> photos, SimpleIdlingResource idlingResource) {
        this.context = context;
        this.idlingResource = idlingResource;
        PhotoAdapter.photos = photos;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_item, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoHolder holder, int position) {
        final PhotoItem photoItem = photos.get(position);
        holder.bindPhotoItem(photoItem);
        holder.progressBarItem.setVisibility(View.VISIBLE);

        Glide.with(context).load(photoItem.getPhotoPageUri()).thumbnail(0.50f).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBarItem.setVisibility(View.INVISIBLE);
                        return false;
                    }
                })
                .into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.progress_bar_item)
        ProgressBar progressBarItem;
        @BindView(R.id.item_image_view)
        ImageView itemImageView;

        private PhotoItem photoItem;

        PhotoHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
            progressBarItem = (ProgressBar) itemView.findViewById(R.id.progress_bar_item);
            itemView.setOnClickListener(this);
        }

        void bindPhotoItem(PhotoItem photoItem) {
            this.photoItem = photoItem;
        }

        @Override
        public void onClick(View v) {
            int dominantColor;
            int buttonColor;
            try {
                dominantColor = getDominantColor(((BitmapDrawable) itemImageView.getDrawable()).getBitmap());
            } catch (NullPointerException e) {
                dominantColor = context.getResources().getColor(R.color.colorDetailActivity);
            }

            try {
                buttonColor = getButtonsColor(((BitmapDrawable) itemImageView.getDrawable()).getBitmap());
            } catch (NullPointerException e) {
                buttonColor = context.getResources().getColor(R.color.colorAccent);
            }

            if (dominantColor == buttonColor) {
                buttonColor = context.getResources().getColor(R.color.colorAccent);
            }

            DetailActivity.startDetailActivity(context,
                    photoItem.getPhotoPageUri(),
                    photoItem.getFullPhotoPageUri(),
                    photoItem.getTitle(),
                    dominantColor,
                    buttonColor);
        }
    }

    private static int getDominantColor(Bitmap bitmap) {
        Palette palette = Palette.from(bitmap).generate();
        return palette.getDominantColor(0);
    }

    private static int getButtonsColor(Bitmap bitmap) {
        Palette palette = Palette.from(bitmap).generate();
        return palette.getDarkVibrantColor(0);
    }
}
