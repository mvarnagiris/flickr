package com.code44.flickr.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code44.flickr.R;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;

public class MainFragment extends Fragment {
    private AsymmetricGridView grid_V;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get views
        grid_V = (AsymmetricGridView) view.findViewById(R.id.grid_V);
    }
}
