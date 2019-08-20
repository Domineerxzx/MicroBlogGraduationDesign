package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/15,23:14
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class IssueImageInfo implements Serializable {

    private int _id;
    private int issueId;
    private String issueImage;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getIssueImage() {
        return issueImage;
    }

    public void setIssueImage(String issueImage) {
        this.issueImage = issueImage;
    }

    @Override
    public String toString() {
        return "IssueImageInfo{" +
                "_id=" + _id +
                ", issueId=" + issueId +
                ", issueImage='" + issueImage + '\'' +
                '}';
    }
}
