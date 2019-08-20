package com.domineer.triplebro.microbloggraduationdesign.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/8/15,23:17
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ChatInfo implements Serializable {

    private int _id;
    private int userIdOne;
    private int userIdTwo;
    private String chatContent;
    private String time;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne(int userIdOne) {
        this.userIdOne = userIdOne;
    }

    public int getUserIdTwo() {
        return userIdTwo;
    }

    public void setUserIdTwo(int userIdTwo) {
        this.userIdTwo = userIdTwo;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ChatInfo{" +
                "_id=" + _id +
                ", userIdOne=" + userIdOne +
                ", userIdTwo=" + userIdTwo +
                ", chatContent='" + chatContent + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
