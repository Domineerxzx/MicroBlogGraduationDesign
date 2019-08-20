package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.fragments.BottomFragment;
import com.domineer.triplebro.microbloggraduationdesign.fragments.HotFragment;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.services.NetworkConnectionService;
import com.domineer.triplebro.microbloggraduationdesign.utils.PermissionUtil;
import com.domineer.triplebro.microbloggraduationdesign.utils.ossUtils.InitOssClient;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl_bottom;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //InitOssClient.initOssClient(this, ProjectProperties.TOKEN_ADDRESS,ProjectProperties.ENDPOINT);
        Intent service = new Intent(this, NetworkConnectionService.class);
        startService(service);
        PermissionUtil.requestPower(this, this, "android.permission.CAMERA"); //请求权限
        PermissionUtil.requestPower(this, this, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private void initView() {
        fl_bottom = (FrameLayout) findViewById(R.id.fl_bottom);
        fl_bottom.bringToFront();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, new HotFragment());
        transaction.replace(R.id.fl_bottom, new BottomFragment());
        transaction.commit();
    }
}
