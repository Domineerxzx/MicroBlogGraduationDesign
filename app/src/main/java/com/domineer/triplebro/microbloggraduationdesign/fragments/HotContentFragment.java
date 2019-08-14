package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domineer.triplebro.microbloggraduationdesign.R;

/**
 * @author Domineer
 * @data 2019/8/15,3:12
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HotContentFragment extends Fragment {

    private View fragment_hot_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_hot_content = inflater.inflate(R.layout.fragment_hot_content, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_hot_content;
    }

    private void initView() {

    }

    private void initData() {

    }

    private void setOnClickListener() {
    }
}
