package com.domineer.triplebro.microbloggraduationdesign.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domineer.triplebro.microbloggraduationdesign.database.MicroBlogDataBase;
import com.domineer.triplebro.microbloggraduationdesign.managers.HotManager;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,23:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataBaseProvider implements DataProvider {

    private Context context;
    private final MicroBlogDataBase microBlogDataBase;

    public DataBaseProvider(Context context) {
        this.context = context;
        microBlogDataBase = new MicroBlogDataBase(context);
    }

    @Override
    public UserInfo queryUserInfoById(int userId) {
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        Cursor userInfoCursor = db.query("userInfo", null, "_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
        UserInfo userInfo = new UserInfo();
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            while (userInfoCursor.moveToNext()) {
                userInfo.set_id(userInfoCursor.getInt(0));
                userInfo.setPhoneNumber(userInfoCursor.getString(1));
                userInfo.setPassword(userInfoCursor.getString(2));
                userInfo.setNickname(userInfoCursor.getString(3));
                userInfo.setUserHead(userInfoCursor.getString(4));
                userInfo.setIsShutUp(userInfoCursor.getInt(5));
            }
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        db.close();
        return userInfo;
    }

    @Override
    public List<IssueInfo> getAllIssueInfoList() {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, null, null, null, null, "_id desc");
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }

    @Override
    public List<IssueInfo> getIssueInfoListByType(String type) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, "issue_content like ?", new String[]{"%" + type + "%"}, null, null, "_id desc");
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }

    @Override
    public List<List<IssueImageInfo>> getIssueImageInfoListByIssueInfoList(List<IssueInfo> issueInfoList) {
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        List<List<IssueImageInfo>> issueImageInfoList = new ArrayList<>();
        for (IssueInfo issueInfo : issueInfoList) {
            List<IssueImageInfo> issueImageInfos = new ArrayList<>();
            Cursor issueImageInfoCursor = db.query("issueImageInfo", null, "issue_id = ?", new String[]{String.valueOf(issueInfo.get_id())}, null, null, null);
            if (issueImageInfoCursor != null && issueImageInfoCursor.getCount() > 0) {
                while (issueImageInfoCursor.moveToNext()) {
                    IssueImageInfo issueImageInfo = new IssueImageInfo();
                    issueImageInfo.set_id(issueImageInfoCursor.getInt(0));
                    issueImageInfo.setIssueId(issueImageInfoCursor.getInt(1));
                    issueImageInfo.setIssueImage(issueImageInfoCursor.getString(2));
                    issueImageInfos.add(issueImageInfo);
                }
            }
            if (issueImageInfoCursor != null) {
                issueImageInfoCursor.close();
            }
            issueImageInfoList.add(issueImageInfos);
        }
        db.close();
        return issueImageInfoList;
    }

    public void addCare(int id, int user_id) {
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cared_user_id",id);
        contentValues.put("user_id",user_id);
        db.insert("careInfo",null,contentValues);
    }

    public boolean queryIsCared(int id, int user_id) {
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        Cursor careInfoCursor = db.query("careInfo", null, "cared_user_id = ? or cared_user_id = ? or user_id = ? or user_id = ?", new String[]{String.valueOf(id), String.valueOf(user_id), String.valueOf(id), String.valueOf(user_id)}, null, null, null);
        if(careInfoCursor != null && careInfoCursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public void deleteCare(int id, int user_id) {
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        db.delete("careInfo","user_id = ? and cared_user_id = ?",new String[]{String.valueOf(user_id), String.valueOf(id)});
    }

    public List<UserInfo> queryAllUserInfoListByUserId(int user_id) {
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        List<Integer> careUserIdList = new ArrayList<>();
        Cursor caredIdInfoCursor = db.query("careInfo", new String[]{"cared_user_id"}, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if(caredIdInfoCursor!= null && caredIdInfoCursor.getCount()>0){
            while (caredIdInfoCursor.moveToNext()){
                careUserIdList.add(caredIdInfoCursor.getInt(0));
            }
        }
        if (caredIdInfoCursor != null) {
            caredIdInfoCursor.close();
        }
        db.close();
        HotManager hotManager = new HotManager(context);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (Integer integer:careUserIdList) {
            UserInfo userInfo = hotManager.queryUserInfoById(integer);
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    public List<IssueInfo> getIssueInfoListByUserId(int userId) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, "_id desc");
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }
}
