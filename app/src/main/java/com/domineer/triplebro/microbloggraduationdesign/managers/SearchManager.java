package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.Context;

import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.SearchHistoryInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.List;


public class SearchManager {

    private Context context;

    public SearchManager(Context context) {
        this.context = context;
    }

    public List<SearchHistoryInfo> getSearchHistoryById(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<SearchHistoryInfo> searchHistoryInfoList = dataBaseProvider.getSearchHistoryById(user_id);
        return searchHistoryInfoList;
    }

    public void deleteSearchInfoById(int id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.deleteSearchInfoById(id);
    }

    public void deleteAllSearchInfo(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.deleteAllSearchInfo(user_id);
    }

    public List<IssueInfo> searchIssueInfoList(String search) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<IssueInfo> issueInfoList = dataBaseProvider.searchIssueInfoList(search);
        return issueInfoList;
    }

    public List<List<IssueImageInfo>> searchIssueImageInfoList(List<IssueInfo> searchIssueInfoList) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<List<IssueImageInfo>> issueImageInfoList = dataBaseProvider.searchIssueImageInfoList(searchIssueInfoList);
        return issueImageInfoList;
    }

    public void addSearchHistory(int user_id, String search) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.addSearchHistory(user_id,search);
    }
}
