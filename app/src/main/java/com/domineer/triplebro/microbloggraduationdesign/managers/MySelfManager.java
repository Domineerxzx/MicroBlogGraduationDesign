package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2020/2/13,11:19
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class MySelfManager {

    private Context context;

    public MySelfManager(Context context) {
        this.context = context;
    }


    public int getLookMeCount(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        return dataBaseProvider.getLookMeCount(user_id);
    }

    public int getMyLookCount(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        return dataBaseProvider.getMyLookCount(user_id);
    }

    public List<IssueInfo> getLookMeIssueInfoList(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        return dataBaseProvider.getLookMeIssueInfoList(user_id);
    }

    public List<IssueInfo> getMyLookIssueInfoList(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        return dataBaseProvider.getMyLookIssueInfoList(user_id);
    }

    public List<List<IssueImageInfo>> getLookMeIssueImageInfoList(List<IssueInfo> lookMeIssueInfoList) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        return dataBaseProvider.getLookMeIssueImageInfoList(lookMeIssueInfoList);
    }

    public List<List<IssueImageInfo>> getMyLookIssueImageInfoList(List<IssueInfo> myLookIssueInfoList) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        return dataBaseProvider.getMyLookIssueImageInfoList(myLookIssueInfoList);
    }
}
