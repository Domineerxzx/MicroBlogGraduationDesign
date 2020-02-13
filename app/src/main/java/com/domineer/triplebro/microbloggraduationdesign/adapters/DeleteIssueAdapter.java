package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.AddCareDialogUtil;
import com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils.TwoButtonDialog;

import java.util.List;

public class DeleteIssueAdapter extends BaseAdapter implements OnItemClickListener {

    private Context context;
    private List<IssueInfo> issueInfoList;
    private List<List<IssueImageInfo>> issueImageInfoList;

    private HotManager hotManager;
    private PhotoWallAdapter photoWallAdapter;

    public DeleteIssueAdapter(Context context, List<IssueInfo> issueInfoList, List<List<IssueImageInfo>> issueImageInfoList) {
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
            convertView = View.inflate(context, R.layout.item_delete_issue, null);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_issue_content = convertView.findViewById(R.id.tv_issue_content);
            viewHolder.iv_delete = convertView.findViewById(R.id.iv_delete);
            viewHolder.rv_hot = convertView.findViewById(R.id.rv_hot);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final UserInfo userInfo = hotManager.queryUserInfoById(issueInfoList.get(position).getUserId());
        viewHolder.tv_nickname.setText(userInfo.getNickname());
        if (userInfo.getUserHead() != null && userInfo.getUserHead().length() != 0) {
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
        photoWallAdapter.setOnItemClickListener(this);
        viewHolder.tv_time.setText(issueInfoList.get(position).getIssueTime());
        hotManager.addOrUpdateReadHistory(userInfo.get_id(),issueInfoList.get(position).get_id(),issueInfoList.get(position).getUserId());
        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
                twoButtonDialog.show("删除此发布信息", "是否删除此发布信息？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hotManager.deleteIssueInfo(issueInfoList.get(position));
                        issueInfoList.remove(position);
                        issueImageInfoList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },((Activity)context).getFragmentManager());
            }
        });
        return convertView;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view) {

    }

    private class ViewHolder {
        private ImageView iv_delete;
        private TextView tv_nickname;
        private RecyclerView rv_hot;
        private ImageView iv_user_head;
        private TextView tv_issue_content;
        private TextView tv_time;
    }
}
