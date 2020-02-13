package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.managers.AdminManager;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

public class DeleteUserActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_delete_user;
    private AdminManager adminManager;
    private List<UserInfo> userInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_delete_user = (ImageView) findViewById(R.id.iv_close_delete_user);
    }

    private void initData() {
        adminManager = new AdminManager(this);
        userInfoList = adminManager.getAllUserInfoList();
    }

    private void setOnClickListener() {
        iv_close_delete_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_delete_user:
                finish();
                break;
        }
    }
}
