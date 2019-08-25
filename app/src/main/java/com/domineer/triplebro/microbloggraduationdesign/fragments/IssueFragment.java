package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.IssueAdapter;
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.managers.IssueManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.microbloggraduationdesign.utils.imageUtils.RealPathFromUriUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class IssueFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    private View fragment_issue;
    private IssueAdapter issueAdapter;
    private ImageView iv_close_submit;
    private RecyclerView rv_issue;
    private Button bt_issue;
    private EditText et_issue_content;
    private SharedPreferences userInfo;
    private String phone_number;
    private long timeStamp;
    private IssueManager issueManager;
    private int user_id;
    private int is_shut_up;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_issue = inflater.inflate(R.layout.fragment_issue, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_issue;
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        is_shut_up = userInfo.getInt("isShutUp", -1);
        issueAdapter = new IssueAdapter(getActivity(), new ArrayList<String>());
        rv_issue.setAdapter(issueAdapter);
        issueAdapter.setOnItemClickListener(this);
        issueManager = new IssueManager(getActivity());
    }

    private void setOnClickListener() {
        bt_issue.setOnClickListener(this);
    }

    private void initView() {
        rv_issue = (RecyclerView) fragment_issue.findViewById(R.id.rv_issue);
        rv_issue.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        bt_issue = (Button) fragment_issue.findViewById(R.id.bt_issue);
        et_issue_content = (EditText) fragment_issue.findViewById(R.id.et_issue_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_issue:
                if(is_shut_up == 1){
                    Toast.makeText(getActivity(), "您已被禁言，请联系管理员解禁", Toast.LENGTH_SHORT).show();
                    return;
                }
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.setUserId(user_id);
                issueInfo.setIssueContent(et_issue_content.getText().toString().trim());
                //获取当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                timeStamp = System.currentTimeMillis();
                issueInfo.setIssueTime(simpleDateFormat.format(timeStamp));
                int issue_id = issueManager.UploadIssueInfo(issueInfo);
                List<String> data = issueAdapter.getData();
                if (data.size() > 0 && data.size() < 9) {
                    data.remove(data.size()-1);
                }
                for (String s :data) {
                    IssueImageInfo issueImageInfo = new IssueImageInfo();
                    issueImageInfo.setIssueId(issue_id);
                    issueImageInfo.setIssueImage(s);
                    issueManager.UploadIssueImageInfo(issueImageInfo);
                }
                Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_SHORT).show();
                initData();
                et_issue_content.setText("");
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        userInfo = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        phone_number = userInfo.getString("phone_number", "");
        timeStamp = System.currentTimeMillis();
        ChooseUserHeadDialogUtil.showSelectSubmitDialog(this, phone_number, timeStamp);
    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        String s = "";
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    s = RealPathFromUriUtils.getRealPathFromUri(getActivity(),data.getData());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    s = getActivity().getFilesDir() + File.separator + "images" + File.separator + phone_number + timeStamp + ".jpg";
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            List<String> strings = issueAdapter.getData();
            if (strings.size() == 0) {
                strings.add(s);
            } else {
                strings.remove(strings.size() - 1);
                strings.add(s);
            }
            if (strings.size() != 9) {
                strings.add("");
            }
            issueAdapter.setData(strings);
        } else {
            Toast.makeText(getActivity(), "取消选择", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
