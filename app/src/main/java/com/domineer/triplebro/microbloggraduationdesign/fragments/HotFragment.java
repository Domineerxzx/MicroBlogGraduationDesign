package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.MainActivity;
import com.domineer.triplebro.microbloggraduationdesign.adapters.ViewPagerAdapter;
import com.domineer.triplebro.microbloggraduationdesign.views.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

public class HotFragment extends Fragment {

    private View fragment_hot;
    private NavitationLayout nl_hot;
    private ViewPager vp_hot;
    private List<Fragment> hotFragmentList;
    private ViewPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_hot = inflater.inflate(R.layout.fragment_hot, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_hot;
    }

    private void initView() {
        nl_hot = (NavitationLayout) fragment_hot.findViewById(R.id.nl_hot);
        vp_hot = (ViewPager) fragment_hot.findViewById(R.id.vp_hot);
    }

    private void initData() {
        String titles[] = new String[]{"全部", "明星", "搞笑", "情感", "美女", "体育", "影视"};
        nl_hot.setViewPager(getActivity(), titles, vp_hot, R.color.colorGray, R.color.colorAppStyle, 16, 16, 0, 0, true);
        nl_hot.setBgLine(getActivity(), 1, R.color.colorLine);
        nl_hot.setNavLine(getActivity(), 2, R.color.colorAppStyle, 0);
        hotFragmentList = new ArrayList<>();
        hotFragmentList.add(new HotContentFragment());
        hotFragmentList.add(new HotContentFragment());
        hotFragmentList.add(new HotContentFragment());
        hotFragmentList.add(new HotContentFragment());
        hotFragmentList.add(new HotContentFragment());
        hotFragmentList.add(new HotContentFragment());
        hotFragmentList.add(new HotContentFragment());
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), hotFragmentList);
        vp_hot.setAdapter(pagerAdapter);
    }

    private void setOnClickListener() {

    }
}
