package com.domineer.triplebro.microbloggraduationdesign.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.ChatContentActivity;
import com.domineer.triplebro.microbloggraduationdesign.adapters.ChatAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.ChatManager;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

public class ChatFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_chat;
    private ListView lv_chat;
    private ChatManager chatManager;
    private SharedPreferences userInfo;
    private int user_id;
    private List<UserInfo> chatUserInfoList;
    private ChatAdapter chatAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_chat = inflater.inflate(R.layout.fragment_chat, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_chat;
    }

    private void initView() {
        lv_chat = (ListView) fragment_chat.findViewById(R.id.lv_chat);
    }

    private void initData() {
        chatManager = new ChatManager(getActivity());
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        chatUserInfoList = chatManager.getChatUserInfoList(user_id);
        chatAdapter = new ChatAdapter(getActivity(), chatUserInfoList);
        lv_chat.setAdapter(chatAdapter);
    }

    private void setOnClickListener() {
        lv_chat.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent chatContent = new Intent(getActivity(), ChatContentActivity.class);
        chatContent.putExtra("chatUserInfo",chatUserInfoList.get(position));
        getActivity().startActivity(chatContent);
    }
}
