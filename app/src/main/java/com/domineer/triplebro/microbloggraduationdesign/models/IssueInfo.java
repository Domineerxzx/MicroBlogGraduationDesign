package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/15,23:11
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class IssueInfo implements Serializable {

    private int _id;
    private int userId;
    private String issueContent;
    private String issueTime;

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

    public String getIssueContent() {
        return issueContent;
    }

    public void setIssueContent(String issueContent) {
        this.issueContent = issueContent;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    @Override
    public String toString() {
        return "IssueInfo{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", issueContent='" + issueContent + '\'' +
                ", issueTime='" + issueTime + '\'' +
                '}';
    }
}
