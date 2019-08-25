package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.IssueContentActivity;
import com.domineer.triplebro.microbloggraduationdesign.adapters.HotAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,3:12
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HotContentFragment extends Fragment {

    private View fragment_hot_content;
    private ListView lv_hot;
    private HotAdapter hotAdapter;
    private HotManager hotManager;
    private String type;
    private List<IssueInfo> issueInfoList;
    private List<List<IssueImageInfo>> issueImageInfoList;
    private List<String> imageUrlList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_hot_content = inflater.inflate(R.layout.fragment_hot_content, null);
        initView();
        initData();
        return fragment_hot_content;
    }

    private void initView() {
        lv_hot = fragment_hot_content.findViewById(R.id.lv_hot);
    }

    private void initData() {
        hotManager = new HotManager(getActivity());
        issueInfoList = hotManager.getIssueInfoListByType(type);
        issueImageInfoList = hotManager.getIssueImageInfoListByIssueInfoList(issueInfoList);
        hotAdapter = new HotAdapter(getActivity(), issueInfoList, issueImageInfoList);
        hotAdapter.setHotManager(hotManager);
        lv_hot.setAdapter(hotAdapter);
    }
}
