package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.CommentAdapter;
import com.domineer.triplebro.microbloggraduationdesign.adapters.PhotoWallAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.CommentManager;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.CommentInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.AddCommentDialogUtil;
import com.domineer.triplebro.microbloggraduationdesign.views.MyListView;

import java.util.List;

public class IssueContentActivity extends Activity implements View.OnClickListener {

    private IssueInfo issueInfo;
    private List<IssueImageInfo> issueImageInfoList;
    private HotManager hotManager;
    private TextView tv_nickname;
    private TextView tv_issue_content;
    private ImageView iv_user_head;
    private ImageView iv_close_issue_content;
    private TextView tv_time;
    private RecyclerView rv_hot;
    private UserInfo userInfo;
    private PhotoWallAdapter photoWallAdapter;
    private TextView tv_add_comment;
    private MyListView lv_comment;
    private CommentManager commentManager;
    private List<CommentInfo> commentInfoList;
    private CommentAdapter commentAdapter;
    private int user_id;
    private SharedPreferences loginUserInfo;
    private RelativeLayout rl_comment;
    private RelativeLayout rl_comment_null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_content);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_issue_content = (TextView) findViewById(R.id.tv_issue_content);
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);
        iv_close_issue_content = (ImageView) findViewById(R.id.iv_close_issue_content);
        tv_time = (TextView) findViewById(R.id.tv_time);
        rv_hot = (RecyclerView) findViewById(R.id.rv_hot);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_hot.setLayoutManager(gridLayoutManager);
        tv_add_comment = (TextView) findViewById(R.id.tv_add_comment);
        lv_comment = (MyListView) findViewById(R.id.lv_comment);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);
        rl_comment_null = (RelativeLayout) findViewById(R.id.rl_comment_null);
    }

    private void initData() {
        loginUserInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = loginUserInfo.getInt("user_id", 0);
        Intent intent = getIntent();
        issueInfo = (IssueInfo) intent.getSerializableExtra("issueInfo");
        hotManager = new HotManager(this);
        issueImageInfoList = hotManager.getIssueImageInfoListByIssueInfo(issueInfo);
        this.userInfo = hotManager.queryUserInfoById(issueInfo.getUserId());
        tv_nickname.setText(this.userInfo.getNickname());
        tv_time.setText(issueInfo.getIssueTime());
        tv_issue_content.setText(issueInfo.getIssueContent());
        photoWallAdapter = new PhotoWallAdapter(this, issueImageInfoList);
        rv_hot.setAdapter(photoWallAdapter);
        if(this.userInfo.getUserHead() != null && this.userInfo.getUserHead().length()>0){
            Glide.with(this).load(this.userInfo.getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }else{
            Glide.with(this).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }
        commentManager = new CommentManager(this);
        commentInfoList = commentManager.getCommentInfoList(issueInfo.get_id());
        commentAdapter = new CommentAdapter(this, commentInfoList);
        commentAdapter.setView(lv_comment,rl_comment,rl_comment_null);
        lv_comment.setAdapter(commentAdapter);
        if(commentInfoList.size() == 0){
            rl_comment_null.setVisibility(View.VISIBLE);
            rl_comment.setVisibility(View.GONE);
        }else{
            rl_comment_null.setVisibility(View.GONE);
            rl_comment.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClickListener() {
        iv_close_issue_content.setOnClickListener(this);
        tv_add_comment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_issue_content:
                finish();
                break;
            case R.id.tv_add_comment:
                AddCommentDialogUtil.showAddCommentDialog(this, ProjectProperties.ADD_COMMENT, issueInfo,lv_comment,rl_comment,rl_comment_null);
                break;
        }
    }
}
