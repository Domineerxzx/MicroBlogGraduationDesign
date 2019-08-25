package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.CommentInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.AddCommentDialogUtil;
import com.domineer.triplebro.microbloggraduationdesign.views.MyListView;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/26,2:19
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CommentInCommentAdapter extends BaseAdapter {

    private Context context;
    private List<CommentInfo> commentInfoList;
    private CommentInfo commentInfoFather;
    private MyListView listView;
    private RelativeLayout rl_comment;
    private RelativeLayout rl_comment_null;

    public CommentInCommentAdapter(Context context, List<CommentInfo> commentInfoList) {
        this.context = context;
        this.commentInfoList = commentInfoList;
    }

    public void setView(MyListView lv_comment, RelativeLayout rl_comment, RelativeLayout rl_comment_null) {
        this.listView = lv_comment;
        this.rl_comment = rl_comment;
        this.rl_comment_null = rl_comment_null;
    }

    public CommentInfo getCommentInfoFather() {
        return commentInfoFather;
    }

    public void setCommentInfoFather(CommentInfo commentInfoFather) {
        this.commentInfoFather = commentInfoFather;
    }

    @Override
    public int getCount() {
        return commentInfoList.size();
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
        final CommentInCommentAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new CommentInCommentAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_comment_comment, null);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_comment_content = convertView.findViewById(R.id.tv_comment_content);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CommentInCommentAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_time.setText(commentInfoList.get(position).getTime());
        viewHolder.tv_comment_content.setText(commentInfoList.get(position).getCommentContent());
        HotManager hotManager = new HotManager(context);
        UserInfo userInfo = hotManager.queryUserInfoById(commentInfoList.get(position).getUserId());
        viewHolder.tv_nickname.setText(userInfo.getNickname());
        if (userInfo.getUserHead() != null && userInfo.getUserHead().length() != 0) {
            Glide.with(context).load(userInfo.getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        } else {
            Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }
        viewHolder.iv_user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommentDialogUtil.showAddCommentDialog(context, ProjectProperties.ADD_COMMENT_IN_COMMENT,commentInfoFather,listView, rl_comment, rl_comment_null);
            }
        });
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_comment_content;
        private ImageView iv_user_head;
        private TextView tv_time;
        private TextView tv_nickname;
    }
}
