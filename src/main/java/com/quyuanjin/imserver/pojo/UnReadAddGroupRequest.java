package com.quyuanjin.imserver.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UnReadAddGroupRequest {
    @Id
    @GeneratedValue
    Long id;
    String receiverId;
    String senderId;
    String msg;
    String sendTime;
    String receiverTime;
    String groupId;

    public UnReadAddGroupRequest() {
    }

    public UnReadAddGroupRequest(String receiverId, String senderId, String msg, String sendTime, String receiverTime, String groupId) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.msg = msg;
        this.sendTime = sendTime;
        this.receiverTime = receiverTime;
        this.groupId = groupId;
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

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(String receiverTime) {
        this.receiverTime = receiverTime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
