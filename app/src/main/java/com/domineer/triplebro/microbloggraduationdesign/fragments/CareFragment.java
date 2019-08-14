package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domineer.triplebro.microbloggraduationdesign.R;

public class CareFragment extends Fragment {

    private View fragment_care;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_care = inflater.inflate(R.layout.fragment_care, null);
        return fragment_care;
    }
}
