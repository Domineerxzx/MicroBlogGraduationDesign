package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.ChatContentActivity;
import com.domineer.triplebro.microbloggraduationdesign.managers.ChatManager;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.ChatInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/25,5:39
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ChatContentAdapter extends BaseAdapter {

    private Context context;
    private List<ChatInfo> chatInfoList;
    private final UserInfo userInfoLogin;
    private UserInfo userInfoChat;

    public List<ChatInfo> getChatInfoList() {
        return chatInfoList;
    }

    public void setChatInfoList(List<ChatInfo> chatInfoList) {
        this.chatInfoList = chatInfoList;
        notifyDataSetChanged();
    }

    public ChatContentAdapter(Context context, List<ChatInfo> chatInfoList) {
        this.context = context;
        this.chatInfoList = chatInfoList;
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userInfoLogin = new UserInfo();
        userInfoLogin.set_id(userInfo.getInt("user_id", 0));
    }

    @Override
    public int getCount() {
        return chatInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChatContentAdapter.ViewHolder viewHolder;
        viewHolder = new ChatContentAdapter.ViewHolder();
        if (chatInfoList.get(position).getUserIdOne() == userInfoLogin.get_id()) {
            convertView = View.inflate(context, R.layout.item_chat_content_right, null);
            viewHolder.tv_chat_content = convertView.findViewById(R.id.tv_chat_content);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.tv_time.setText(chatInfoList.get(position).getTime());
            viewHolder.tv_chat_content.setText(chatInfoList.get(position).getChatContent());
        } else {
            convertView = View.inflate(context, R.layout.item_chat_content_left, null);
            viewHolder.tv_chat_content = convertView.findViewById(R.id.tv_chat_content);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.tv_time.setText(chatInfoList.get(position).getTime());
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_chat_content.setText(chatInfoList.get(position).getChatContent());
            if (userInfoChat == null) {
                HotManager hotManager = new HotManager(context);
                userInfoChat = hotManager.queryUserInfoById(chatInfoList.get(position).getUserIdOne());
            }
            if (userInfoChat.getUserHead() != null && userInfoChat.getUserHead().length() != 0) {
                Glide.with(context).load(userInfoChat.getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
            } else {
                Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
            }

        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_user_head;
        private TextView tv_chat_content;
        private TextView tv_time;
    }
}
