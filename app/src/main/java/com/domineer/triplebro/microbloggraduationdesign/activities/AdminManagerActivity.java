package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;

public class AdminManagerActivity extends Activity implements View.OnClickListener {

    private TextView tv_shut_up;
    private ImageView iv_close_admin_manager;
    private TextView tv_delete_issue;
    private TextView tv_delete_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        initView();
        setOnClickListener();
    }

    private void initView() {
        tv_shut_up = (TextView) findViewById(R.id.tv_shut_up);
        iv_close_admin_manager = (ImageView) findViewById(R.id.iv_close_admin_manager);
        tv_delete_issue = (TextView) findViewById(R.id.tv_delete_issue);
        tv_delete_user = (TextView) findViewById(R.id.tv_delete_user);
    }

    private void setOnClickListener() {
        tv_shut_up.setOnClickListener(this);
        tv_delete_issue.setOnClickListener(this);
        tv_delete_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_shut_up:
                Intent shutUp = new Intent(this, ShutUpActivity.class);
                startActivity(shutUp);
                break;
            case R.id.tv_delete_issue:
                Intent deleteIssue = new Intent(this, DeleteIssueActivity.class);
                startActivity(deleteIssue);
                break;
            case R.id.tv_delete_user:
                Intent deleteUser = new Intent(this, DeleteUserActivity.class);
                startActivity(deleteUser);
                break;
            case R.id.iv_close_admin_manager:
                finish();
                break;
        }
    }
}
