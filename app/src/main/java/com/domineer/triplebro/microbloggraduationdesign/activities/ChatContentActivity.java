package com.domineer.triplebro.microbloggraduationdesign.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.ChatContentAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.ChatManager;
import com.domineer.triplebro.microbloggraduationdesign.models.ChatInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.text.SimpleDateFormat;
import java.util.List;

public class ChatContentActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_chat_content;
    private UserInfo chatUserInfo;
    private TextView tv_title;
    private ListView lv_chat_content;
    private ChatManager chatManager;
    private SharedPreferences userInfo;
    private int user_id;
    private List<ChatInfo> chatInfoList;
    private ChatContentAdapter chatContentAdapter;
    private EditText et_chat_content;
    private TextView tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_content);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_chat_content = (ImageView) findViewById(R.id.iv_close_chat_content);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lv_chat_content = (ListView) findViewById(R.id.lv_chat_content);
        et_chat_content = (EditText) findViewById(R.id.et_chat_content);
        tv_send = (TextView) findViewById(R.id.tv_send);
    }

    private void initData() {
        Intent intent = getIntent();
        chatUserInfo = (UserInfo) intent.getSerializableExtra("chatUserInfo");
        tv_title.setText(chatUserInfo.getNickname());
        chatManager = new ChatManager(this);
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        chatInfoList = chatManager.getChatInfoList(chatUserInfo.get_id(),user_id);
        chatContentAdapter = new ChatContentAdapter(this, chatInfoList);
        lv_chat_content.setAdapter(chatContentAdapter);
        lv_chat_content.setSelection(chatInfoList.size()-1);
    }

    private void setOnClickListener() {
        iv_close_chat_content.setOnClickListener(this);
        tv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_chat_content:
                finish();
                break;
            case R.id.tv_send:
                String chat_content = et_chat_content.getText().toString().trim();
                if(chat_content.length() == 0){
                    Toast.makeText(this, "不能发送空的消息", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                long timeStamp = System.currentTimeMillis();
                String time = simpleDateFormat.format(timeStamp);
                chatManager.addChatInfo(chatUserInfo.get_id(),user_id,chat_content,time);
                chatInfoList = chatManager.getChatInfoList(chatUserInfo.get_id(),user_id);
                chatContentAdapter.setChatInfoList(chatInfoList);
                lv_chat_content.setSelection(chatInfoList.size()-1);
                et_chat_content.clearFocus();
                et_chat_content.setText("");
                break;
        }
    }
}
