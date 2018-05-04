package test.com.flickrgallery.Model;

import android.net.Uri;

public final class PhotoItem {
    private String owner;
    private String server;
    private int farm;
    private String id;
    private String secret;
    private String title;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoPageUri() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";
    }

    public String getFullPhotoPageUri() {
        return String.valueOf(Uri.parse("https://www.flickr.com/photos/")
                .buildUpon()
                .appendPath(owner)
                .appendPath(id)
                .build());
    }
}
