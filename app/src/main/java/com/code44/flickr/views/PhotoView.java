package com.code44.flickr.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.code44.flickr.R;
import com.code44.flickr.data.model.PhotoModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PhotoView extends RelativeLayout {
    private static final Transformation AVATAR_TRANSFORMATION = new AvatarTransformation();

    private final ImageView photo_IV;
    private final ImageView gradient_IV;
    private final TextView title_TV;
    private final TextView author_TV;
    private final ImageView avatar_IV;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.v_photo, this);

        // Get views
        photo_IV = (ImageView) findViewById(R.id.photo_IV);
        gradient_IV = (ImageView) findViewById(R.id.gradient_IV);
        title_TV = (TextView) findViewById(R.id.title_TV);
        author_TV = (TextView) findViewById(R.id.author_TV);
        avatar_IV = (ImageView) findViewById(R.id.avatar_IV);

        // Setup
        setType(Type.WIDE);
    }

    public void setPhoto(PhotoModel photo) {
        Picasso.with(getContext()).load(photo.getPhotoUrl()).fit().centerCrop().into(photo_IV);
        title_TV.setText(photo.getTitle());
        author_TV.setText(getResources().getString(R.string.f_by_x, photo.getAuthor()));
        if (avatar_IV.getVisibility() == VISIBLE) {
            Picasso.with(getContext()).load(photo.getAuthorAvatarUrl()).fit().centerCrop().transform(AVATAR_TRANSFORMATION).into(avatar_IV);
        }
    }

    public void setType(Type type) {
        switch (type) {
            case WIDE:
                setFixedHeight(getResources().getDimensionPixelSize(R.dimen.wide_photo_height));
                setGradientHeightRatio(0.5f);
                title_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_title));
                author_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_author));
                avatar_IV.setVisibility(VISIBLE);
                break;
            case LONG:
                setGradientHeightRatio(0.25f);
                title_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_title_small));
                author_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_author_small));
                avatar_IV.setVisibility(GONE);
                break;
            case SQUARE:
                setGradientHeightRatio(0.5f);
                title_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_title_small));
                author_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_author_small));
                avatar_IV.setVisibility(GONE);
                break;
            default:
                throw new IllegalArgumentException("Type " + type + " is not supported");
        }
        requestLayout();
    }

    private void setFixedHeight(int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            setLayoutParams(params);
        } else {
            params.height = height;
        }
    }

    private void setGradientHeightRatio(float gradientHeightRatio) {
        ((LinearLayout.LayoutParams) gradient_IV.getLayoutParams()).weight = gradientHeightRatio;
    }

    public static enum Type {
        WIDE, LONG, SQUARE
    }

    private static class AvatarTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            // TODO Transformation
            return source;
        }

        @Override public String key() {
            return "avatar";
        }
    }
}
