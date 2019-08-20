package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.Context;

import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/18,3:29
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CareManager {

    private Context context;

    public CareManager(Context context) {
        this.context = context;
    }

    public void addCare(int id, int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.addCare(id,user_id);
    }

    public boolean queryIsCared(int id, int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        boolean isCared = dataBaseProvider.queryIsCared(id,user_id);
        return isCared;
    }

    public void deleteCare(int id, int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.deleteCare(id,user_id);
    }

    public List<UserInfo> queryAllUserInfoListByUserId(int user_id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<UserInfo> caredUserInfoList = dataBaseProvider.queryAllUserInfoListByUserId(user_id);
        return caredUserInfoList;
    }
}
