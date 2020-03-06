package com.quyuanjin.imserver.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class RecementMsg {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    private String myUserId;//实际上是数字userid
    private String yourUserId;
    private String portrait;//头像
    private String unreadCount;//未读消息数
    private String name;//用户昵称（自己设定的优先）
    private String time;//时间

    private String status;//发送状态 /已发送，未发送
    private String contentText;//草稿内容|最近消息
    private String slient;//静默

    public RecementMsg() {
    }

    public RecementMsg(String myUserId, String yourUserId, String portrait, String unreadCount, String name, String time, String prompt, String status, String contentText, String slient) {
        this.myUserId = myUserId;
        this.yourUserId = yourUserId;
        this.portrait = portrait;
        this.unreadCount = unreadCount;
        this.name = name;
        this.time = time;
        this.status = status;
        this.contentText = contentText;
        this.slient = slient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getYourUserId() {
        return yourUserId;
    }

    public void setYourUserId(String yourUserId) {
        this.yourUserId = yourUserId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(String unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getSlient() {
        return slient;
    }

    public void setSlient(String slient) {
        this.slient = slient;
    }
}
