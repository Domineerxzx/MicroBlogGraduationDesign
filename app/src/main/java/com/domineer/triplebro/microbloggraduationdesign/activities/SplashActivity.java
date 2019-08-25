package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.handlers.AdPictureHandler;
import com.domineer.triplebro.microbloggraduationdesign.managers.DataInitManager;
import com.domineer.triplebro.microbloggraduationdesign.services.NetworkConnectionService;

public class SplashActivity extends Activity implements View.OnClickListener {

    private TextView tv_skip;
    private ImageView iv_ad;
    private AdPictureHandler adPictureHandler;
    private DataInitManager dataInitManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        iv_ad = (ImageView) findViewById(R.id.iv_ad);
    }

    private void initData() {
        adPictureHandler = new AdPictureHandler(this, iv_ad);
        dataInitManager = new DataInitManager(this,adPictureHandler);
        adPictureHandler.setDataInitManager(dataInitManager);
        Intent intent = new Intent(this, NetworkConnectionService.class);
        startService(intent);
        dataInitManager.getAdPicture();
    }

    private void setOnClickListener() {
        tv_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_skip:
                adPictureHandler.setDataInitManager(null);
                unbindService(dataInitManager);
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                finish();
                break;
        }
    }
}
