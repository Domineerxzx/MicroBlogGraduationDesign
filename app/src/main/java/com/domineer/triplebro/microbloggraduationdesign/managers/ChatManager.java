package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.Context;

import com.domineer.triplebro.microbloggraduationdesign.models.ChatInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/25,3:56
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ChatManager {

    private Context context;

    public ChatManager(Context context) {
        this.context = context;
    }

    public List<UserInfo> getChatUserInfoList(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<UserInfo> chatUserInfoList = dataBaseProvider.getChatUserInfoList(user_id);
        return chatUserInfoList;
    }

    public ChatInfo getChatInfo(int id, int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        ChatInfo chatInfo = dataBaseProvider.getChatInfo(id,user_id);
        return chatInfo;
    }

    public List<ChatInfo> getChatInfoList(int id, int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<ChatInfo> chatInfoListInfo = dataBaseProvider.getChatInfoList(id,user_id);
        return chatInfoListInfo;
    }

    public void addChatInfo(int id, int user_id, String chat_content, String time) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.addChatInfo(id,user_id,chat_content,time);
    }
}
