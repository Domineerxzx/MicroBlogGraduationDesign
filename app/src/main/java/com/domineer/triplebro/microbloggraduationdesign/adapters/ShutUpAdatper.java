package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.managers.AdminManager;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/25,7:55
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ShutUpAdatper extends BaseAdapter {

    private Context context;
    private List<UserInfo> userInfoList;
    private AdminManager adminManager;

    public ShutUpAdatper(Context context, List<UserInfo> userInfoList) {
        this.context = context;
        this.userInfoList = userInfoList;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return userInfoList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ShutUpAdatper.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ShutUpAdatper.ViewHolder();
            convertView = View.inflate(context, R.layout.item_shut_up, null);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.sw_shut_up = convertView.findViewById(R.id.sw_shut_up);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShutUpAdatper.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_nickname.setText(userInfoList.get(position).getNickname());
        if (userInfoList.get(position).getIsShutUp() == 0) {
            viewHolder.sw_shut_up.setChecked(false);
        } else {
            viewHolder.sw_shut_up.setChecked(true);
        }
        if (userInfoList.get(position).getUserHead() != null && userInfoList.get(position).getUserHead().length() != 0) {
            Glide.with(context).load(userInfoList.get(position).getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        } else {
            Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }
        viewHolder.sw_shut_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isShutUp = userInfoList.get(position).getIsShutUp();
                if(isShutUp == 0){
                    isShutUp = 1;
                }else{
                    isShutUp = 0;
                }
                adminManager = new AdminManager(context);
                adminManager.updateUserShutUpInfo(userInfoList.get(position).get_id(),isShutUp);
                userInfoList = adminManager.getAllUserInfoList();
                setUserInfoList(userInfoList);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_nickname;
        private ImageView iv_user_head;
        private Switch sw_shut_up;
    }
}
