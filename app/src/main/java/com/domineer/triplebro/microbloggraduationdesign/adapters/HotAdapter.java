package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.IssueContentActivity;
import com.domineer.triplebro.microbloggraduationdesign.handlers.OssHandler;
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.AddCareDialogUtil;
import com.domineer.triplebro.microbloggraduationdesign.utils.ossUtils.DownloadUtils;
import com.youth.banner.Banner;

import java.io.File;
import java.util.List;

public class HotAdapter extends BaseAdapter {

    private Context context;
    private List<IssueInfo> issueInfoList;
    private List<List<IssueImageInfo>> issueImageInfoList;

    private HotManager hotManager;
    private PhotoWallAdapter photoWallAdapter;

    public HotAdapter(Context context, List<IssueInfo> issueInfoList, List<List<IssueImageInfo>> issueImageInfoList) {
        this.context = context;
        this.issueInfoList = issueInfoList;
        this.issueImageInfoList = issueImageInfoList;
    }

    public List<IssueInfo> getIssueInfoList() {
        return issueInfoList;
    }

    public void setIssueInfoListAndIssueImageInfoList(List<IssueInfo> issueInfoList, List<List<IssueImageInfo>> issueImageInfoList) {
        this.issueInfoList = issueInfoList;
        this.issueImageInfoList = issueImageInfoList;
        notifyDataSetChanged();
    }

    public List<List<IssueImageInfo>> getIssueImageInfoList() {
        return issueImageInfoList;
    }


    public void setHotManager(HotManager hotManager) {
        this.hotManager = hotManager;
    }

    @Override
    public int getCount() {
        return issueInfoList.size();
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_hot, null);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_issue_content = convertView.findViewById(R.id.tv_issue_content);
            viewHolder.iv_talk = convertView.findViewById(R.id.iv_talk);
            viewHolder.rv_hot = convertView.findViewById(R.id.rv_hot);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.iv_share = convertView.findViewById(R.id.iv_share);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final UserInfo userInfo = hotManager.queryUserInfoById(issueInfoList.get(position).getUserId());
        viewHolder.tv_nickname.setText(userInfo.getNickname());
        if (userInfo.getUserHead() != null || userInfo.getUserHead().length() != 0) {
            Glide.with(context).load(userInfo.getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }
        viewHolder.tv_issue_content.setText(issueInfoList.get(position).getIssueContent());
        viewHolder.rv_hot.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        photoWallAdapter = new PhotoWallAdapter(context, issueImageInfoList.get(position));
        viewHolder.rv_hot.setAdapter(photoWallAdapter);
        viewHolder.tv_time.setText(issueInfoList.get(position).getIssueTime());
        viewHolder.iv_user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCareDialogUtil.showDialog(context, userInfo);
            }
        });
        viewHolder.iv_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent issue_content = new Intent(context, IssueContentActivity.class);
                issue_content.putExtra("issueInfo", issueInfoList.get(position));
                context.startActivity(issue_content);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_share;
        private ImageView iv_talk;
        private TextView tv_nickname;
        private RecyclerView rv_hot;
        private ImageView iv_user_head;
        private TextView tv_issue_content;
        private TextView tv_time;
    }
}
