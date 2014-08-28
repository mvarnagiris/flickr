package com.code44.flickr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.code44.flickr.data.model.PhotoModel;
import com.code44.flickr.views.PhotoGroupView;
import com.code44.flickr.views.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class FlickrAdapter extends BaseAdapter {
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_GROUP = 1;

    private final List<PhotoModel> photos = new ArrayList<>();
    private final Context context;

    public FlickrAdapter(Context context) {
        this.context = context;
    }

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override public int getItemViewType(int position) {
        return position % 2 == 0 ? VIEW_TYPE_NORMAL : VIEW_TYPE_GROUP;
    }

    @Override public int getCount() {
        return photos.size() / 2 + Math.min(photos.size() % 4, 1);
    }

    @Override public Object getItem(int position) {
        return photos.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final int viewType = getItemViewType(position);
        View view = convertView;
        if (view == null) {
            view = newView(context, viewType);
        }
        bindView(view, position, viewType);
        return view;
    }

    public void setPhotos(List<PhotoModel> photos) {
        this.photos.clear();
        if (photos != null) {
            this.photos.addAll(photos);
        }
        notifyDataSetChanged();
    }

    private View newView(Context context, int viewType) {
        if (viewType == VIEW_TYPE_NORMAL) {
            return new PhotoView(context);
        } else {
            return new PhotoGroupView(context);
        }
    }

    private void bindView(View view, int position, int viewType) {
        if (viewType == VIEW_TYPE_NORMAL) {
            final PhotoView photo_V = (PhotoView) view;
            final PhotoModel model = getModel(getFirstModelPositionForAdapterPosition(position, viewType));
            photo_V.setPhoto(model);
        } else {
            final PhotoGroupView photoGroup_V = (PhotoGroupView) view;
            final int firstPosition = getFirstModelPositionForAdapterPosition(position, viewType);
            final PhotoModel modelLeft = getModel(firstPosition);
            final PhotoModel modelTopRight = getModel(firstPosition + 1);
            final PhotoModel modelBottomRight = getModel(firstPosition + 2);
            photoGroup_V.setPhotos(modelLeft, modelTopRight, modelBottomRight);
        }
    }

    private int getFirstModelPositionForAdapterPosition(int adapterPosition, int viewType) {
        return (adapterPosition + 1) * 2 - (viewType == VIEW_TYPE_NORMAL ? 1 : 2) - 1;
    }

    private PhotoModel getModel(int position) {
        if (position < photos.size()) {
            return photos.get(position);
        }
        return null;
    }
}
