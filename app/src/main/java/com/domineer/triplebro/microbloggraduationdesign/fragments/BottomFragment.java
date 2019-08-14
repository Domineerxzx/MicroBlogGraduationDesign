package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;

public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_bottom;
    private LinearLayout ll_hot;
    private LinearLayout ll_care;
    private LinearLayout ll_issue;
    private LinearLayout ll_chat;
    private LinearLayout ll_myself;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Button bt_hot;
    private Button bt_care;
    private Button bt_issue;
    private Button bt_chat;
    private Button bt_myself;
    private TextView tv_hot;
    private TextView tv_care;
    private TextView tv_issue;
    private TextView tv_chat;
    private TextView tv_myself;

    private Button lastFunctionButton;
    private TextView lastFunctionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_bottom = inflater.inflate(R.layout.fragment_bottom, null);
        initView();
        setOnClick();
        fragmentManager = getActivity().getSupportFragmentManager();
        return fragment_bottom;
    }

    private void initView() {
        ll_hot = fragment_bottom.findViewById(R.id.ll_hot);
        ll_care = fragment_bottom.findViewById(R.id.ll_care);
        ll_issue = fragment_bottom.findViewById(R.id.ll_issue);
        ll_chat = fragment_bottom.findViewById(R.id.ll_chat);
        ll_myself = fragment_bottom.findViewById(R.id.ll_myself);
        bt_hot = fragment_bottom.findViewById(R.id.bt_hot);
        bt_care = fragment_bottom.findViewById(R.id.bt_care);
        bt_issue = fragment_bottom.findViewById(R.id.bt_issue);
        bt_chat = fragment_bottom.findViewById(R.id.bt_chat);
        bt_myself = fragment_bottom.findViewById(R.id.bt_myself);
        tv_hot = fragment_bottom.findViewById(R.id.tv_hot);
        tv_care = fragment_bottom.findViewById(R.id.tv_care);
        tv_issue = fragment_bottom.findViewById(R.id.tv_issue);
        tv_chat = fragment_bottom.findViewById(R.id.tv_chat);
        tv_myself = fragment_bottom.findViewById(R.id.tv_myself);

        if (lastFunctionTextView == null) {
            lastFunctionTextView = tv_hot;
        }
        if (lastFunctionButton == null) {
            lastFunctionButton = bt_hot;
        }

    }

    public void setOnClick() {

        ll_hot.setOnClickListener(this);
        ll_care.setOnClickListener(this);
        ll_issue.setOnClickListener(this);
        ll_chat.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        bt_hot.setOnClickListener(this);
        bt_care.setOnClickListener(this);
        bt_issue.setOnClickListener(this);
        bt_chat.setOnClickListener(this);
        bt_myself.setOnClickListener(this);
        tv_hot.setOnClickListener(this);
        tv_care.setOnClickListener(this);
        tv_issue.setOnClickListener(this);
        tv_chat.setOnClickListener(this);
        tv_myself.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_hot:
            case R.id.bt_hot:
            case R.id.tv_hot:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new HotFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_hot);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_hot.setTextColor(0xFFFFBB00);
                lastFunctionTextView = tv_hot;
                break;
            case R.id.ll_care:
            case R.id.bt_care:
            case R.id.tv_care:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new CareFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_care);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_care.setTextColor(0xFFFFBB00);
                lastFunctionTextView = tv_care;
                break;
            case R.id.ll_issue:
            case R.id.bt_issue:
            case R.id.tv_issue:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new IssueFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_issue);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_issue.setTextColor(0xFFFFBB00);
                lastFunctionTextView = tv_issue;
                break;
            case R.id.ll_chat:
            case R.id.bt_chat:
            case R.id.tv_chat:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new ChatFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_chat);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_chat.setTextColor(0xFFFFBB00);
                lastFunctionTextView = tv_chat;
                break;
            case R.id.ll_myself:
            case R.id.bt_myself:
            case R.id.tv_myself:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new MyselfFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_myself);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_myself.setTextColor(0xFFFFBB00);
                lastFunctionTextView = tv_myself;
                break;
        }
    }

    private void changeImageForButton(Button lastFunctionButton, Button onClickButton) {
        switch (lastFunctionButton.getId()) {
            case R.id.bt_hot:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_care:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_issue:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_chat:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_myself:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
        }
        switch (onClickButton.getId()) {
            case R.id.bt_hot:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_care:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_issue:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_chat:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_myself:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
        }
    this.lastFunctionButton =onClickButton;
    }

}
