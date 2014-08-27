package com.code44.flickr.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.code44.flickr.R;
import com.code44.flickr.data.model.PhotoModel;

/**
 * Since RecyclerView is not available on pre-L officially and the layout seems fixed for this app,
 * this 3-in-1 view is not as bad.
 */
public class PhotoGroupView extends LinearLayout {
    private final PhotoView leftPhoto_V;
    private final PhotoView topRightPhoto_V;
    private final PhotoView bottomRightPhoto_V;

    @SuppressWarnings("UnusedDeclaration") public PhotoGroupView(Context context) {
        this(context, null);
    }

    public PhotoGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoGroupView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.v_photo_group, this);

        // Get views
        leftPhoto_V = (PhotoView) findViewById(R.id.leftPhoto_V);
        topRightPhoto_V = (PhotoView) findViewById(R.id.topRightPhoto_V);
        bottomRightPhoto_V = (PhotoView) findViewById(R.id.bottomRightPhoto_V);

        // Setup
        leftPhoto_V.setType(PhotoView.Type.LONG);
        topRightPhoto_V.setType(PhotoView.Type.SQUARE);
        bottomRightPhoto_V.setType(PhotoView.Type.SQUARE);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int size = getMeasuredWidth();
        setMeasuredDimension(size, size);
    }

    public void setPhotos(PhotoModel leftPhoto, PhotoModel topRightPhoto, PhotoModel bottomRightPhoto) {
        updatePhotoView(leftPhoto_V, leftPhoto);
        updatePhotoView(topRightPhoto_V, topRightPhoto);
        updatePhotoView(bottomRightPhoto_V, bottomRightPhoto);
    }

    private void updatePhotoView(PhotoView view, PhotoModel photo) {
        if (photo == null) {
            view.setVisibility(GONE);
        } else {
            view.setVisibility(VISIBLE);
            view.setPhoto(photo);
        }
    }
}
