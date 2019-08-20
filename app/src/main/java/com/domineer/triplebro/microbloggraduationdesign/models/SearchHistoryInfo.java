package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/15,23:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class SearchHistoryInfo implements Serializable {

    private int _id;
    private int userId;
    private String searchContent;
    private int searchCount;

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

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    @Override
    public String toString() {
        return "SearchHistoryInfo{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", searchContent='" + searchContent + '\'' +
                ", searchCount=" + searchCount +
                '}';
    }
}
