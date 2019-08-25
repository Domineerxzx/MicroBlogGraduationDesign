package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.SearchActivity;
import com.domineer.triplebro.microbloggraduationdesign.adapters.ViewPagerAdapter;
import com.domineer.triplebro.microbloggraduationdesign.views.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

public class HotFragment extends Fragment implements View.OnClickListener {

    private View fragment_hot;
    private NavitationLayout nl_hot;
    private ViewPager vp_hot;
    private List<Fragment> hotFragmentList;
    private ViewPagerAdapter pagerAdapter;
    private TextView tv_search;
    private ImageView iv_search;
    private FrameLayout fl_search;
    private String[] titles;

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
        tv_search = fragment_hot.findViewById(R.id.tv_search);
        iv_search = fragment_hot.findViewById(R.id.iv_search);
        fl_search = fragment_hot.findViewById(R.id.fl_search);
    }

    private void initData() {
        titles = new String[]{"全部", "明星", "搞笑", "情感", "美女", "体育", "影视"};
        nl_hot.setViewPager(getActivity(), titles, vp_hot, R.color.colorGray, R.color.colorAppStyle, 16, 16, 0, 0, true);
        nl_hot.setBgLine(getActivity(), 1, R.color.colorLine);
        nl_hot.setNavLine(getActivity(), 2, R.color.colorAppStyle, 0);
        hotFragmentList = new ArrayList<>();
        HotContentFragment hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("全部");
        hotFragmentList.add(hotContentFragment);
        hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("明星");
        hotFragmentList.add(hotContentFragment);
        hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("搞笑");
        hotFragmentList.add(hotContentFragment);
        hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("情感");
        hotFragmentList.add(hotContentFragment);
        hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("美女");
        hotFragmentList.add(hotContentFragment);
        hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("体育");
        hotFragmentList.add(hotContentFragment);
        hotContentFragment = new HotContentFragment();
        hotContentFragment.setType("影视");
        hotFragmentList.add(hotContentFragment);
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), hotFragmentList);
        vp_hot.setAdapter(pagerAdapter);
        vp_hot.setClickable(false);
    }

    private void setOnClickListener() {
        tv_search.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        fl_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
            case R.id.tv_search:
            case R.id.fl_search:
                Intent search = new Intent(getActivity(), SearchActivity.class);
                startActivity(search);
                break;
        }
    }
}
