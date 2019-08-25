package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.handlers.OssHandler;
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.ossUtils.DownloadUtils;

import java.io.File;
import java.util.List;

public class PhotoWallAdapter extends RecyclerView.Adapter<PhotoWallAdapter.ViewHolder> {

    private Context context;
    private List<IssueImageInfo> issueImageInfoList;
    private OnItemClickListener onItemClickListener;

    public PhotoWallAdapter(Context context, List<IssueImageInfo> issueImageInfoList) {
        this.context = context;
        this.issueImageInfoList = issueImageInfoList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PhotoWallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_photo_wall, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(context).load(issueImageInfoList.get(i).getIssueImage()).into(viewHolder.iv_photo_wall);
    }

    @Override
    public int getItemCount() {
        return issueImageInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_photo_wall;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            initView(itemView);
        }

        private void initView(View itemView) {
            iv_photo_wall = itemView.findViewById(R.id.iv_photo_wall);
            iv_photo_wall.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
