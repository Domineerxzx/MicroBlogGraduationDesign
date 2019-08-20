package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domineer.triplebro.microbloggraduationdesign.database.MicroBlogDataBase;
import com.domineer.triplebro.microbloggraduationdesign.handlers.OssHandler;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.ossUtils.UploadUtils;

public class IssueManager {

    private Context context;

    public IssueManager(Context context) {
        this.context = context;
    }

    public int UploadIssueInfo(IssueInfo issueInfo) {
        int submit_id = -1;
        MicroBlogDataBase microBlogDataBase = new MicroBlogDataBase(context);
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", issueInfo.getUserId());
        contentValues.put("issue_content", issueInfo.getIssueContent());
        contentValues.put("issue_time", issueInfo.getIssueTime());
        db.insert("issueInfo", null, contentValues);
        Cursor query = db.query("issueInfo", new String[]{"_id"}, "user_id = ?", new String[]{String.valueOf(issueInfo.getUserId())}, null, null, "_id desc");
        if (query != null && query.getCount() > 0) {
            query.moveToNext();
            submit_id = query.getInt(0);
        }
        if (query != null) {
            query.close();
        }
        db.close();
        return submit_id;
    }


    public void UploadIssueImageInfo(IssueImageInfo issueImageInfo) {

        MicroBlogDataBase microBlogDataBase = new MicroBlogDataBase(context);
        SQLiteDatabase db = microBlogDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("issue_id", issueImageInfo.getIssueId());
        contentValues.put("issue_image", issueImageInfo.getIssueImage());
        /*OssHandler ossHandler = new OssHandler(context);
        UploadUtils.uploadFileToOss(ossHandler, ProjectProperties.BUCKET_NAME,"MicroBlog/"+issueImageInfo.getIssueImage(),issueImageInfo.getIssueImage());
        */db.insert("issueImageInfo", null, contentValues);
        db.close();
    }
}
