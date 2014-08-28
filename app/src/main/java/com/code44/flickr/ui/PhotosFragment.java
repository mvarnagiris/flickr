package com.code44.flickr.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.code44.flickr.R;
import com.code44.flickr.adapters.FlickrAdapter;
import com.code44.flickr.data.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment {
    private ListView list_V;

    private FlickrAdapter adapter;

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get views
        list_V = (ListView) view.findViewById(R.id.list_V);

        // Setup
        adapter = new FlickrAdapter(getActivity());
        list_V.setAdapter(adapter);

        final List<PhotoModel> photos = new ArrayList<>();
        photos.add(createPhotoModel());
        photos.add(createPhotoModel());
        photos.add(createPhotoModel());
        photos.add(createPhotoModel());
        photos.add(createPhotoModel());
        photos.add(createPhotoModel());
        adapter.setPhotos(photos);
    }

    private PhotoModel createPhotoModel() {
        final PhotoModel model = new PhotoModel();
        model.setTitle("Some title");
        model.setAuthor("Some author");
        model.setAuthorAvatarUrl("http://ny1.createit.netdna-cdn.com/wp-content/uploads/2014/01/avatar.jpg");
        model.setPhotoUrl("http://www.hdwallpapers.in/walls/african_lion_king-wide.jpg");
        return model;
    }
}
