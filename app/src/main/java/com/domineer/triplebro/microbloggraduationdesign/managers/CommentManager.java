package com.domineer.triplebro.microbloggraduationdesign.managers;

import android.content.ContentValues;
import android.content.Context;

import com.domineer.triplebro.microbloggraduationdesign.activities.IssueContentActivity;
import com.domineer.triplebro.microbloggraduationdesign.models.CommentInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/26,2:40
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CommentManager {
    private Context context;
    public CommentManager(Context context) {
        this.context = context;
    }


    public List<CommentInfo> getCommentInfoList(int id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<CommentInfo> commentInfoList = dataBaseProvider.getCommentInfoList(id);
        return commentInfoList;
    }

    public List<CommentInfo> getCommentInCommentInfoList(int issueId, int id) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<CommentInfo> commentInCommentInfoList = dataBaseProvider.getCommentInCommentInfoList(issueId,id);
        return commentInCommentInfoList;
    }

    public void addCommentInfo(ContentValues contentValues) {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        dataBaseProvider.addCommentInfo(contentValues);
    }
}
