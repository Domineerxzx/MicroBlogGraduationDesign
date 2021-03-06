package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.LoginActivity;
import com.domineer.triplebro.microbloggraduationdesign.activities.SettingActivity;
import com.domineer.triplebro.microbloggraduationdesign.adapters.HotAdapter;
import com.domineer.triplebro.microbloggraduationdesign.database.MicroBlogDataBase;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.managers.MySelfManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.microbloggraduationdesign.utils.imageUtils.RealPathFromUriUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MyselfFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private View fragment_myself;
    private File userHeadFile;
    private ImageView iv_user_head;
    private ImageView iv_collection;
    private ImageView iv_collection_more;
    private ImageView iv_push_or_pull;
    private ImageView iv_push_or_pull_more;
    private ImageView iv_contact_us;
    private ImageView iv_contact_us_more;
    private ImageView iv_setting;
    private RelativeLayout rl_collection;
    private RelativeLayout rl_push_or_pull;
    private RelativeLayout rl_contact_us;
    private LinearLayout ll_user_info;
    private TextView tv_nickname;
    private TextView tv_username;
    private TextView tv_collection;
    private TextView tv_push_or_pull;
    private TextView tv_contact_us;
    private RelativeLayout rl_user_head_large;
    private ImageView iv_user_head_large;
    private ImageView iv_close_user_head_large;
    private SharedPreferences userInfo;
    private String username;
    private String nickname;
    private String userHead;
    private TextView tv_my_look;
    private TextView tv_look_me;
    private ListView lv_myself;
    private MySelfManager mySelfManager;
    private int user_id;
    private int lookMeCount;
    private int myLookCount;
    private List<IssueInfo> myLookIssueInfoList;
    private List<IssueInfo> lookMeIssueInfoList;
    private LinearLayout ll_my_look;
    private LinearLayout ll_look_me;
    private List<List<IssueImageInfo>> lookMeIssueImageInfoList;
    private HotAdapter hotAdapter;
    private List<List<IssueImageInfo>> myLookIssueImageInfoList;
    private HotManager hotManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_myself = inflater.inflate(R.layout.fragment_myself, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_myself;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initView() {
        iv_user_head = (ImageView) fragment_myself.findViewById(R.id.iv_user_head);
        iv_setting = (ImageView) fragment_myself.findViewById(R.id.iv_setting);
        iv_setting.bringToFront();
        ll_user_info = (LinearLayout) fragment_myself.findViewById(R.id.ll_user_info);
        tv_nickname = (TextView) fragment_myself.findViewById(R.id.tv_nickname);
        tv_username = (TextView) fragment_myself.findViewById(R.id.tv_username);
        rl_user_head_large = (RelativeLayout) fragment_myself.findViewById(R.id.rl_user_head_large);
        iv_user_head_large = (ImageView) fragment_myself.findViewById(R.id.iv_user_head_large);
        iv_close_user_head_large = (ImageView) fragment_myself.findViewById(R.id.iv_close_user_head_large);
        tv_my_look = (TextView) fragment_myself.findViewById(R.id.tv_my_look);
        tv_look_me = (TextView) fragment_myself.findViewById(R.id.tv_look_me);
        lv_myself = (ListView) fragment_myself.findViewById(R.id.lv_myself);
        ll_my_look = (LinearLayout) fragment_myself.findViewById(R.id.ll_my_look);
        ll_look_me = (LinearLayout) fragment_myself.findViewById(R.id.ll_look_me);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        username = userInfo.getString("phone_number", "");
        nickname = userInfo.getString("nickname", "");
        userHead = userInfo.getString("userHead", "");
        user_id = userInfo.getInt("user_id", -1);
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(nickname)) {
            tv_username.setText(R.string.usernameDefault);
            tv_nickname.setText(R.string.nicknameDefault);
        } else {
            tv_username.setText("ID：" + username);
            tv_nickname.setText(nickname);
        }
        if (TextUtils.isEmpty(userHead)) {
            Glide.with(getActivity()).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        } else {
            Glide.with(getActivity()).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }
        mySelfManager = new MySelfManager(getActivity());
        lookMeCount = mySelfManager.getLookMeCount(user_id);
        myLookCount = mySelfManager.getMyLookCount(user_id);
        tv_look_me.setText(String.valueOf(lookMeCount));
        tv_my_look.setText(String.valueOf(myLookCount));
        hotAdapter = new HotAdapter(getActivity(), new ArrayList<IssueInfo>(), new ArrayList<List<IssueImageInfo>>());
        hotManager = new HotManager(getActivity());
        hotAdapter.setHotManager(hotManager);
        lv_myself.setAdapter(hotAdapter);
        myLookIssueInfoList = mySelfManager.getMyLookIssueInfoList(user_id);
        myLookIssueImageInfoList = mySelfManager.getMyLookIssueImageInfoList(myLookIssueInfoList);
        hotAdapter.setIssueInfoListAndIssueImageInfoList(myLookIssueInfoList,myLookIssueImageInfoList);
    }

    private void setOnClickListener() {
        iv_user_head.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        tv_nickname.setOnClickListener(this);
        tv_username.setOnClickListener(this);
        ll_user_info.setOnClickListener(this);
        iv_user_head.setOnLongClickListener(this);
        rl_user_head_large.setOnClickListener(this);
        iv_user_head_large.setOnLongClickListener(this);
        iv_close_user_head_large.setOnClickListener(this);
        ll_look_me.setOnClickListener(this);
        ll_my_look.setOnClickListener(this);
        tv_my_look.setOnClickListener(this);
        tv_look_me.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_head:
            case R.id.ll_user_info:
            case R.id.tv_username:
            case R.id.tv_nickname:
                String username = tv_username.getText().toString().trim();
                String nickname = tv_nickname.getText().toString().trim();
                if (username.equals("暂无登录信息") || nickname.equals("点击  登录/注册")) {
                    Intent login = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(login);
                } else {
                    Toast.makeText(getActivity(), "长按头像可查看大头像", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_setting:
                Intent setting = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(setting);
                break;
            case R.id.iv_close_user_head_large:
            case R.id.rl_user_head_large:
                rl_user_head_large.setVisibility(View.GONE);
                setClickableTrue();
                break;
            case R.id.ll_look_me:
            case R.id.tv_look_me:
                ll_look_me.setBackgroundResource(R.drawable.shape_user_button);
                ll_my_look.setBackgroundResource(R.drawable.shape_alpha_card);
                lookMeIssueInfoList = mySelfManager.getLookMeIssueInfoList(user_id);
                lookMeIssueImageInfoList = mySelfManager.getLookMeIssueImageInfoList(lookMeIssueInfoList);
                hotAdapter.setIssueInfoListAndIssueImageInfoList(lookMeIssueInfoList,lookMeIssueImageInfoList);
                break;
            case R.id.ll_my_look:
            case R.id.tv_my_look:
                ll_my_look.setBackgroundResource(R.drawable.shape_user_button);
                ll_look_me.setBackgroundResource(R.drawable.shape_alpha_card);
                myLookIssueInfoList = mySelfManager.getMyLookIssueInfoList(user_id);
                myLookIssueImageInfoList = mySelfManager.getMyLookIssueImageInfoList(myLookIssueInfoList);
                hotAdapter.setIssueInfoListAndIssueImageInfoList(myLookIssueInfoList,myLookIssueImageInfoList);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.iv_user_head:
                rl_user_head_large.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(userHead)) {
                    Glide.with(getActivity()).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                } else {
                    Glide.with(getActivity()).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                }
                setClickableFalse();
                break;
            case R.id.iv_user_head_large:
                long timeStamp = System.currentTimeMillis();
                SharedPreferences.Editor edit = userInfo.edit();
                edit.putLong("timeStamp", timeStamp);
                edit.commit();
                ChooseUserHeadDialogUtil.showDialog(this, username, timeStamp);
                break;
        }
        return true;
    }

    private void setClickableFalse() {
        iv_user_head.setClickable(false);
        iv_setting.setClickable(false);
        tv_nickname.setClickable(false);
        tv_username.setClickable(false);
        ll_user_info.setClickable(false);
        ll_look_me.setClickable(false);
        ll_my_look.setClickable(false);
        tv_my_look.setClickable(false);
        tv_look_me.setClickable(false);
    }

    private void setClickableTrue() {
        iv_user_head.setClickable(true);
        iv_setting.setClickable(true);
        tv_nickname.setClickable(true);
        tv_username.setClickable(true);
        ll_user_info.setClickable(true);
        ll_look_me.setClickable(true);
        ll_my_look.setClickable(true);
        tv_my_look.setClickable(true);
        tv_look_me.setClickable(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        SharedPreferences.Editor edit = userInfo.edit();
        MicroBlogDataBase myOpenHelper = new MicroBlogDataBase(getActivity());
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    userHeadFile = new File(RealPathFromUriUtils.getRealPathFromUri(getActivity(), data.getData()));
                    Glide.with(getActivity()).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                    contentValues.put("user_head", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    long timeStamp = userInfo.getLong("timeStamp", -1);
                    userHeadFile = new File(getActivity().getFilesDir() + File.separator + "images" + File.separator + username + timeStamp + ".jpg");
                    Glide.with(getActivity()).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                    contentValues.put("user_head", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            edit.commit();
            writableDatabase.update("userInfo", contentValues, "phone_number = ?", new String[]{username});
            writableDatabase.close();
        } else {
            Toast.makeText(getActivity(), "取消修改", Toast.LENGTH_SHORT).show();
        }
        initData();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
