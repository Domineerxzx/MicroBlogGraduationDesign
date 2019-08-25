package com.domineer.triplebro.microbloggraduationdesign.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.managers.SearchManager;
import com.domineer.triplebro.microbloggraduationdesign.models.SearchHistoryInfo;

import java.util.List;

public class SearchHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<SearchHistoryInfo> searchHistoryInfoList;
    private TextView tv_no_history;
    private ListView lv_history;

    public SearchHistoryAdapter(Context context, List<SearchHistoryInfo> searchHistoryInfoList, TextView tv_no_history, ListView lv_history) {
        this.context = context;
        this.searchHistoryInfoList = searchHistoryInfoList;
        this.tv_no_history = tv_no_history;
        this.lv_history = lv_history;
    }

    public void setSearchHistoryInfoList(List<SearchHistoryInfo> searchHistoryInfoList) {
        this.searchHistoryInfoList = searchHistoryInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return searchHistoryInfoList.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search_history, null);
            viewHolder.tv_history = convertView.findViewById(R.id.tv_history);
            viewHolder.iv_delete_history = convertView.findViewById(R.id.iv_delete_history);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_history.setText(searchHistoryInfoList.get(position).getSearchContent());
        viewHolder.iv_delete_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchManager searchManager = new SearchManager(context);
                searchManager.deleteSearchInfoById(searchHistoryInfoList.get(position).get_id());
                searchHistoryInfoList.remove(position);
                setSearchHistoryInfoList(searchHistoryInfoList);
                if(searchHistoryInfoList.size() == 0){
                    lv_history.setVisibility(View.GONE);
                    tv_no_history.setVisibility(View.VISIBLE);
                }else{
                    lv_history.setVisibility(View.VISIBLE);
                    tv_no_history.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_history;
        private ImageView iv_delete_history;
    }
}
