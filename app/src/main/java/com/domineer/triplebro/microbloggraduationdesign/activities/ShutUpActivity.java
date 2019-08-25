package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.ShutUpAdatper;
import com.domineer.triplebro.microbloggraduationdesign.managers.AdminManager;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

public class ShutUpActivity extends Activity implements View.OnClickListener {

    private ListView lv_shut_up;
    private ImageView iv_close_shut_up;
    private AdminManager adminManager;
    private List<UserInfo> userInfoList;
    private ShutUpAdatper shutUpAdatper;
    private int admin_id;
    private SharedPreferences adminInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shut_up);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        lv_shut_up = (ListView) findViewById(R.id.lv_shut_up);
        iv_close_shut_up = (ImageView) findViewById(R.id.iv_close_shut_up);
    }

    private void initData() {
        adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        admin_id = adminInfo.getInt("admin_id", 0);
        adminManager = new AdminManager(this);
        userInfoList = adminManager.getAllUserInfoList();
        shutUpAdatper = new ShutUpAdatper(this, userInfoList);
        lv_shut_up.setAdapter(shutUpAdatper);
    }

    private void setOnClickListener() {
        iv_close_shut_up.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_shut_up:
                adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
                SharedPreferences.Editor edit = adminInfo.edit();
                edit.clear();
                edit.commit();
                finish();
                break;
        }
    }
}
