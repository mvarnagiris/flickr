package com.code44.flickr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.code44.flickr.data.model.PhotoModel;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

public class FlickrAdapter extends AsymmetricGridViewAdapter<PhotoModel> {

    public FlickrAdapter(Context context, AsymmetricGridView listView) {
        super(context, listView, null);
    }

    @Override public View getActualView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
