package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.CareAdapter;
import com.domineer.triplebro.microbloggraduationdesign.adapters.HotAdapter;
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.managers.CareManager;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

public class CareFragment extends Fragment implements OnItemClickListener {

    private View fragment_care;
    private RecyclerView rv_care;
    private ListView lv_care;
    private CareManager careManager;
    private int user_id;
    private SharedPreferences localUserInfo;
    private List<UserInfo> caredUserInfoList;
    private CareAdapter careAdapter;
    private HotManager hotManager;
    private List<IssueInfo> issueInfoList;
    private List<List<IssueImageInfo>> issueImageInfoListByIssueInfoList;
    private HotAdapter hotAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_care = inflater.inflate(R.layout.fragment_care, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_care;
    }

    private void initView() {
        rv_care = fragment_care.findViewById(R.id.rv_care);
        lv_care = fragment_care.findViewById(R.id.lv_care);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_care.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        careManager = new CareManager(getActivity());
        localUserInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = localUserInfo.getInt("user_id", 0);
        caredUserInfoList = careManager.queryAllUserInfoListByUserId(user_id);
        careAdapter = new CareAdapter(getActivity(), caredUserInfoList);
        rv_care.setAdapter(careAdapter);
        hotManager = new HotManager(getActivity());
        issueInfoList = hotManager.getIssueInfoListByUserId(caredUserInfoList.get(0).get_id());
        issueImageInfoListByIssueInfoList = hotManager.getIssueImageInfoListByIssueInfoList(issueInfoList);
        hotAdapter = new HotAdapter(getActivity(), issueInfoList, issueImageInfoListByIssueInfoList);
        hotAdapter.setHotManager(hotManager);
        lv_care.setAdapter(hotAdapter);
    }

    private void setOnClickListener() {
        careAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        issueInfoList = hotManager.getIssueInfoListByUserId(caredUserInfoList.get(position).get_id());
        issueImageInfoListByIssueInfoList = hotManager.getIssueImageInfoListByIssueInfoList(issueInfoList);
        hotAdapter.setIssueInfoListAndIssueImageInfoList(issueInfoList,issueImageInfoListByIssueInfoList);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

}
