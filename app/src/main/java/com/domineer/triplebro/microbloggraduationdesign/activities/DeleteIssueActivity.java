package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.DeleteIssueAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;

import java.util.List;

public class DeleteIssueActivity extends Activity implements View.OnClickListener{

    private ImageView iv_close_delete_issue;
    private HotManager hotManager;
    private List<IssueInfo> issueInfoList;
    private List<List<IssueImageInfo>> issueImageInfoList;
    private ListView lv_delete_issue;
    private DeleteIssueAdapter deleteIssueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_issue);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_delete_issue = (ImageView) findViewById(R.id.iv_close_delete_issue);
        lv_delete_issue = (ListView) findViewById(R.id.lv_delete_issue);
    }

    private void initData() {
        hotManager = new HotManager(this);
        issueInfoList = hotManager.getIssueInfoListByType("全部");
        issueImageInfoList = hotManager.getIssueImageInfoListByIssueInfoList(issueInfoList);
        deleteIssueAdapter = new DeleteIssueAdapter(this, issueInfoList, issueImageInfoList);
        deleteIssueAdapter.setHotManager(hotManager);
        lv_delete_issue.setAdapter(deleteIssueAdapter);
    }

    private void setOnClickListener() {
        iv_close_delete_issue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_delete_issue:
                finish();
                break;
        }
    }
}
