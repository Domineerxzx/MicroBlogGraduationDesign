package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.io.File;
import java.util.List;

public class CareAdapter extends RecyclerView.Adapter<CareAdapter.ViewHolder> {

    private Context context;
    private List<UserInfo> userInfoList;
    private OnItemClickListener onItemClickListener;

    public CareAdapter(Context context, List<UserInfo> userInfoList) {
        this.context = context;
        this.userInfoList = userInfoList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_care, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        File file = new File(userInfoList.get(i).getUserHead());
        if (file.length() > 0){
            Glide.with(context).load(userInfoList.get(i).getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }else{
            //OssHandler ossHandler = new OssHandler(context, viewHolder.iv_photo_wall);
            //DownloadUtils.downloadFileFromOss(file,ossHandler, ProjectProperties.BUCKET_NAME,"MicroBlog/"+ userInfoList.get(i).getIssueImage());
        }
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_user_head;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            initView(itemView);
        }

        private void initView(View itemView) {
            iv_user_head = itemView.findViewById(R.id.iv_user_head);
            iv_user_head.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getPosition());
            onItemClickListener.onItemLongClick(v,getPosition());
        }
    }
}
