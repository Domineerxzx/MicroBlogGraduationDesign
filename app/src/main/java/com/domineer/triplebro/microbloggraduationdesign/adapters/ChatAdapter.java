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
import com.domineer.triplebro.microbloggraduationdesign.managers.ChatManager;
import com.domineer.triplebro.microbloggraduationdesign.models.ChatInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/25,4:37
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ChatAdapter extends BaseAdapter {

    private Context context;
    private List<UserInfo> chatUserInfoList;

    public ChatAdapter(Context context, List<UserInfo> chatUserInfoList) {
        this.context = context;
        this.chatUserInfoList = chatUserInfoList;
    }

    @Override
    public int getCount() {
        return chatUserInfoList.size();
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
        final ChatAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ChatAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_chat, null);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_chat_content = convertView.findViewById(R.id.tv_chat_content);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChatAdapter.ViewHolder) convertView.getTag();
        }
        if (chatUserInfoList.get(position).getUserHead() != null && chatUserInfoList.get(position).getUserHead().length() != 0) {
            Glide.with(context).load(chatUserInfoList.get(position).getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        } else {
            Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }
        viewHolder.tv_nickname.setText(chatUserInfoList.get(position).getNickname());
        ChatManager chatManager = new ChatManager(context);
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int user_id = userInfo.getInt("user_id", 0);
        ChatInfo chatInfo = chatManager.getChatInfo(chatUserInfoList.get(position).get_id(),user_id);
        viewHolder.tv_chat_content.setText(chatInfo.getChatContent());
        viewHolder.tv_time.setText(chatInfo.getTime());
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_nickname;
        private ImageView iv_user_head;
        private TextView tv_chat_content;
        private TextView tv_time;
    }
}
