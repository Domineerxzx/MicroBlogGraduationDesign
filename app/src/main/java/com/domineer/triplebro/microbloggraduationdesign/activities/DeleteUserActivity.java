package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.DeleteUserAdatper;
import com.domineer.triplebro.microbloggraduationdesign.managers.AdminManager;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.TwoButtonDialog;

import java.util.List;

public class DeleteUserActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView iv_close_delete_user;
    private AdminManager adminManager;
    private List<UserInfo> userInfoList;
    private ListView lv_delete_user;
    private DeleteUserAdatper deleteUserAdatper;

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
        lv_delete_user = (ListView) findViewById(R.id.lv_delete_user);
    }

    private void initData() {
        adminManager = new AdminManager(this);
        userInfoList = adminManager.getAllUserInfoList();
        deleteUserAdatper = new DeleteUserAdatper(this, userInfoList);
        lv_delete_user.setAdapter(deleteUserAdatper);
    }

    private void setOnClickListener() {
        iv_close_delete_user.setOnClickListener(this);
        lv_delete_user.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_delete_user:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
        twoButtonDialog.show("删除用户？", "是否删除该用户？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adminManager.deleteUser(userInfoList.get(position).get_id());
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        },getFragmentManager());
    }
}
