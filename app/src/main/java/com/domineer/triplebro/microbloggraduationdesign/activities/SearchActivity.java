package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.HotAdapter;
import com.domineer.triplebro.microbloggraduationdesign.adapters.SearchHistoryAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.managers.SearchManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.SearchHistoryInfo;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.TwoButtonDialog;
import com.domineer.triplebro.microbloggraduationdesign.views.MyListView;

import java.util.List;

public class SearchActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MyListView lv_history;
    private RecyclerView rv_recommend_search;
    private ListView lv_search_result;
    private ImageView iv_close_search;
    private SearchHistoryAdapter searchHistoryAdapter;
    private EditText et_search;
    private SearchManager searchManager;
    private RelativeLayout rl_history;
    private RelativeLayout rl_no_search;
    private SharedPreferences searchHistory;
    private TextView tv_no_history;
    private TextView tv_clear_history;
    private SharedPreferences userInfo;
    private int user_id;
    private List<SearchHistoryInfo> searchHistoryInfoList;
    private List<IssueInfo> searchIssueInfoList;
    private List<List<IssueImageInfo>> searchIssueImageInfoList;
    private HotAdapter searchResultAdapter;
    private TextView tv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_close_search.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        lv_history.setOnItemClickListener(this);
        tv_clear_history.setOnClickListener(this);
    }

    private void initData() {
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        searchManager = new SearchManager(this);
        searchHistoryInfoList = searchManager.getSearchHistoryById(user_id);
        if(searchHistoryInfoList.size() == 0){
            tv_no_history.setVisibility(View.VISIBLE);
            lv_history.setVisibility(View.GONE);
        }else{
            tv_no_history.setVisibility(View.GONE);
            lv_history.setVisibility(View.VISIBLE);
        }
        searchHistoryAdapter = new SearchHistoryAdapter(this, searchHistoryInfoList,tv_no_history,lv_history);
        lv_history.setAdapter(searchHistoryAdapter);
    }

    private void initView() {
        lv_history = (MyListView) findViewById(R.id.lv_history);
        lv_search_result = (ListView) findViewById(R.id.lv_search_result);
        iv_close_search = (ImageView) findViewById(R.id.iv_close_search);
        et_search = (EditText) findViewById(R.id.et_search);
        rl_history = (RelativeLayout) findViewById(R.id.rl_history);
        rl_no_search = (RelativeLayout) findViewById(R.id.rl_no_search);
        tv_no_history = (TextView) findViewById(R.id.tv_no_history);
        tv_clear_history = (TextView) findViewById(R.id.tv_clear_history);
        tv_search = (TextView) findViewById(R.id.tv_search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_search:
                finish();
                break;
            case R.id.tv_search:
                String s = et_search.getText().toString().trim();
                if (s.length() > 0) {
                    searchIssueInfoList = searchManager.searchIssueInfoList(s.toString().trim());
                    searchIssueImageInfoList = searchManager.searchIssueImageInfoList(searchIssueInfoList);
                    if (searchIssueInfoList.size() != 0) {
                        rl_history.setVisibility(View.GONE);
                        lv_search_result.setVisibility(View.VISIBLE);
                        rl_no_search.setVisibility(View.GONE);
                        searchResultAdapter = new HotAdapter(this, searchIssueInfoList,searchIssueImageInfoList);
                        lv_search_result.setAdapter(searchResultAdapter);
                        HotManager hotManager = new HotManager(this);
                        searchResultAdapter.setHotManager(hotManager);
                        tv_no_history.setVisibility(View.GONE);
                        lv_history.setVisibility(View.VISIBLE);
                        searchManager.addSearchHistory(user_id,s.toString().trim());
                    } else {
                        rl_history.setVisibility(View.GONE);
                        lv_search_result.setVisibility(View.GONE);
                        rl_no_search.setVisibility(View.VISIBLE);
                    }
                } else {
                    rl_history.setVisibility(View.VISIBLE);
                    lv_search_result.setVisibility(View.GONE);
                    rl_no_search.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_clear_history:
                TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
                twoButtonDialog.show("清空搜索历史记录", "是否确认清空搜索历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchManager.deleteAllSearchInfo(user_id);
                        tv_no_history.setVisibility(View.VISIBLE);
                        lv_history.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },getFragmentManager());
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        et_search.setText(searchHistoryInfoList.get(position).getSearchContent());
    }


}
