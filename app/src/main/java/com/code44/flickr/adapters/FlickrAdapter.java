package com.code44.flickr.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.code44.flickr.data.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

public class FlickrAdapter extends BaseAdapter {
    final List<PhotoModel> photos = new ArrayList<>();

    @Override public int getCount() {
        return photos.size() / 2 + Math.max(photos.size() % 4, 1);
    }

    @Override public Object getItem(int position) {
        return photos.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setPhotos(List<PhotoModel> photos) {
        this.photos.clear();
        if (photos != null) {
            this.photos.addAll(photos);
        }
        notifyDataSetChanged();
    }
}
