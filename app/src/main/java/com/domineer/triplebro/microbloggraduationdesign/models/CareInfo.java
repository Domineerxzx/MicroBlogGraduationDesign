package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/15,23:16
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CareInfo implements Serializable {

    private int _id;
    private int careUserId;
    private int userId;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCareUserId() {
        return careUserId;
    }

    public void setCareUserId(int careUserId) {
        this.careUserId = careUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CareInfo{" +
                "_id=" + _id +
                ", careUserId=" + careUserId +
                ", userId=" + userId +
                '}';
    }
}
