package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/26,2:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CommentInfo implements Serializable {

    private int _id;
    private int userId;
    private int issueId;
    private String commentContent;
    private String time;
    private int commentId;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", issueId=" + issueId +
                ", commentContent='" + commentContent + '\'' +
                ", time='" + time + '\'' +
                ", commentId=" + commentId +
                '}';
    }
}
