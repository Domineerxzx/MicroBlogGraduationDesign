package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.handlers.OssHandler;
import com.domineer.triplebro.microbloggraduationdesign.interfaces.OnItemClickListener;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.ossUtils.DownloadUtils;

import java.io.File;
import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    private Context context;
    private List<String> data;
    private OnItemClickListener onItemClickListener;

    public IssueAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public IssueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_issue, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (data.size() > 0 && data.get(i).length() > 0) {
            File file = new File(data.get(i));
            if (file.length() > 0) {
                Glide.with(context).load(data.get(i)).into(viewHolder.iv_issue);
            }else{
                //OssHandler ossHandler = new OssHandler(context, viewHolder.iv_issue);
                //DownloadUtils.downloadFileFromOss(file,ossHandler, ProjectProperties.BUCKET_NAME,"MicroBlog/"+data.get(i));
            }
            viewHolder.iv_delete_issue.setVisibility(View.VISIBLE);
        } else {
            Glide.with(context).load(R.drawable.submit).into(viewHolder.iv_issue);
            viewHolder.iv_delete_issue.setVisibility(View.GONE);
        }
        viewHolder.iv_delete_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(i);
                notifyDataSetChanged();
            }
        });
        viewHolder.ll_delete_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 1 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_issue;
        private ImageView iv_delete_issue;
        private LinearLayout ll_delete_issue;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            initView(itemView);
        }

        private void initView(View itemView) {
            iv_issue = itemView.findViewById(R.id.iv_issue);
            iv_delete_issue = itemView.findViewById(R.id.iv_delete_issue);
            ll_delete_issue = itemView.findViewById(R.id.ll_delete_issue);
            iv_issue.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getPosition());
        }
    }
}
