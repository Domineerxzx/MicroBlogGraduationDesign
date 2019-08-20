package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/15,23:06
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class UserInfo implements Serializable {

    private int _id;
    private String phoneNumber;
    private String nickname;
    private String password;
    private String userHead;
    private int isShutUp;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public int getIsShutUp() {
        return isShutUp;
    }

    public void setIsShutUp(int isShutUp) {
        this.isShutUp = isShutUp;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", userHead='" + userHead + '\'' +
                ", isShutUp=" + isShutUp +
                '}';
    }
}
