package com.quyuanjin.imserver.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnReadAddFriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String receiverId;
    private String userid;
    private String msg;
    private String createTime;
    private String receiverTime;
    private String sendType;
    private String readType;
    private String nameTextView;//用户昵称
    private String contentTextView;//个性签名
    private String sex;
    private String portraitImageViewnetPath;//头像

    public UnReadAddFriendRequest() {
    }

    public UnReadAddFriendRequest(String receiverId, String userid, String msg, String createTime, String receiverTime, String sendType, String readType, String nameTextView, String contentTextView, String sex, String portraitImageViewnetPath) {
        this.receiverId = receiverId;
        this.userid = userid;
        this.msg = msg;
        this.createTime = createTime;
        this.receiverTime = receiverTime;
        this.sendType = sendType;
        this.readType = readType;
        this.nameTextView = nameTextView;
        this.contentTextView = contentTextView;
        this.sex = sex;
        this.portraitImageViewnetPath = portraitImageViewnetPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(String receiverTime) {
        this.receiverTime = receiverTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public String getNameTextView() {
        return nameTextView;
    }

    public void setNameTextView(String nameTextView) {
        this.nameTextView = nameTextView;
    }

    public String getContentTextView() {
        return contentTextView;
    }

    public void setContentTextView(String contentTextView) {
        this.contentTextView = contentTextView;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPortraitImageViewnetPath() {
        return portraitImageViewnetPath;
    }

    public void setPortraitImageViewnetPath(String portraitImageViewnetPath) {
        this.portraitImageViewnetPath = portraitImageViewnetPath;
    }


}
