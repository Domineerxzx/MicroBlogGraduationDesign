package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.Context;

import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,23:36
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HotManager {

    private Context context;

    public HotManager(Context context) {
        this.context = context;
    }

    public UserInfo queryUserInfoById(int userId) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        UserInfo userInfo = dataBaseProvider.queryUserInfoById(userId);
        return userInfo;
    }

    public List<IssueInfo> getIssueInfoListByType(String type) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        if (type.equals("全部")) {
            issueInfoList = dataBaseProvider.getAllIssueInfoList();
        } else {
            issueInfoList = dataBaseProvider.getIssueInfoListByType(type);
        }
        return issueInfoList;
    }

    public List<IssueInfo> getIssueInfoListByUserId(int userId) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        issueInfoList = dataBaseProvider.getIssueInfoListByUserId(userId);

        return issueInfoList;
    }

    public List<List<IssueImageInfo>> getIssueImageInfoListByIssueInfoList(List<IssueInfo> issueInfoList) {
        List<List<IssueImageInfo>> issueImageInfoList = new ArrayList<List<IssueImageInfo>>();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        issueImageInfoList = dataBaseProvider.getIssueImageInfoListByIssueInfoList(issueInfoList);
        return issueImageInfoList;
    }

    public List<IssueImageInfo> getIssueImageInfoListByIssueInfo(IssueInfo issueInfo) {
        List<IssueImageInfo> issueImageInfoList = new ArrayList<>();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        issueImageInfoList = dataBaseProvider.getIssueImageInfoListByIssueInfo(issueInfo);
        return issueImageInfoList;
    }

    public void addOrUpdateReadHistory(int user_id, int issue_id, int author_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.addOrUpdateReadHistory(user_id,issue_id,author_id);
    }

    public void deleteIssueInfo(IssueInfo issueInfo) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.deleteIssueInfo(issueInfo);
    }
}
