package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.Context;

import com.domineer.triplebro.microbloggraduationdesign.activities.ShutUpActivity;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/25,8:00
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AdminManager {

    private Context context;

    public AdminManager(Context context) {
        this.context = context;
    }


    public List<UserInfo> getAllUserInfoList() {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<UserInfo> userInfoList = dataBaseProvider.getAllUserInfoList();
        return userInfoList;
    }

    public void updateUserShutUpInfo(int id, int isShutUp) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.updateUserShutUpInfo(id,isShutUp);
    }
}
